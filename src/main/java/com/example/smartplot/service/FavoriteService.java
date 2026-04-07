package com.example.smartplot.service;

import com.example.smartplot.dto.MessageResponse;
import com.example.smartplot.dto.PlotResponse;
import com.example.smartplot.model.Favorite;
import com.example.smartplot.model.Plot;
import com.example.smartplot.model.User;
import com.example.smartplot.repository.FavoriteRepository;
import com.example.smartplot.repository.PlotRepository;
import com.example.smartplot.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final PlotRepository plotRepository;
    private final UserRepository userRepository;

    public FavoriteService(
            FavoriteRepository favoriteRepository,
            PlotRepository plotRepository,
            UserRepository userRepository
    ) {
        this.favoriteRepository = favoriteRepository;
        this.plotRepository = plotRepository;
        this.userRepository = userRepository;
    }

    public MessageResponse addFavorite(Integer plotId, String userEmail) {
        User user = getUser(userEmail);
        ensureBuyerRole(user);

        Plot plot = plotRepository.findById(plotId)
                .orElseThrow(() -> new IllegalArgumentException("Plot not found"));

        if (!"APPROVED".equalsIgnoreCase(defaultApproval(plot.getApprovalStatus()))) {
            throw new IllegalArgumentException("Only approved plots can be saved");
        }

        if (favoriteRepository.existsByPlotIdAndUserEmailIgnoreCase(plotId, userEmail)) {
            return new MessageResponse("Plot is already saved in your favorites");
        }

        Favorite favorite = new Favorite();
        favorite.setPlotId(plotId);
        favorite.setUserEmail(userEmail);
        favorite.setCreatedAt(LocalDateTime.now());
        favoriteRepository.save(favorite);
        return new MessageResponse("Plot saved to favorites");
    }

    public MessageResponse removeFavorite(Integer plotId, String userEmail) {
        Favorite favorite = favoriteRepository.findByPlotIdAndUserEmailIgnoreCase(plotId, userEmail)
                .orElseThrow(() -> new IllegalArgumentException("Favorite not found"));
        favoriteRepository.delete(favorite);
        return new MessageResponse("Plot removed from favorites");
    }

    public List<PlotResponse> getMyFavorites(String userEmail) {
        getUser(userEmail);
        Set<Integer> favoriteIds = favoriteRepository.findByUserEmailIgnoreCaseOrderByFavoriteIdDesc(userEmail)
                .stream()
                .map(Favorite::getPlotId)
                .collect(Collectors.toSet());

        return plotRepository.findAll(Sort.by(Sort.Direction.DESC, "plotId"))
                .stream()
                .filter(plot -> favoriteIds.contains(plot.getPlotId()))
                .map(this::mapToResponse)
                .toList();
    }

    public Set<Integer> getMyFavoritePlotIds(String userEmail) {
        getUser(userEmail);
        return favoriteRepository.findByUserEmailIgnoreCaseOrderByFavoriteIdDesc(userEmail)
                .stream()
                .map(Favorite::getPlotId)
                .collect(Collectors.toSet());
    }

    private User getUser(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    private void ensureBuyerRole(User user) {
        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            throw new IllegalArgumentException("ADMIN accounts cannot save favorites");
        }
    }

    private String defaultApproval(String approvalStatus) {
        if (approvalStatus == null || approvalStatus.isBlank()) {
            return "PENDING";
        }
        return approvalStatus;
    }

    private PlotResponse mapToResponse(Plot plot) {
        return new PlotResponse(
                plot.getPlotId(),
                plot.getPlotNumber(),
                plot.getOwnerName(),
                plot.getLocation(),
                plot.getAreaSqft(),
                plot.getPrice(),
                plot.getStatus(),
                defaultApproval(plot.getApprovalStatus()),
                plot.getImageUrl(),
                plot.getBookedByName(),
                plot.getBookedByEmail()
        );
    }
}
