package com.example.smartplot.controller;

import com.example.smartplot.dto.MessageResponse;
import com.example.smartplot.dto.PlotResponse;
import com.example.smartplot.service.FavoriteService;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/plots/{plotId}/favorite")
    public MessageResponse addFavorite(@PathVariable Integer plotId, Principal principal) {
        return favoriteService.addFavorite(plotId, principal.getName());
    }

    @DeleteMapping("/plots/{plotId}/favorite")
    public MessageResponse removeFavorite(@PathVariable Integer plotId, Principal principal) {
        return favoriteService.removeFavorite(plotId, principal.getName());
    }

    @GetMapping("/plots/favorites")
    public List<PlotResponse> getMyFavorites(Principal principal) {
        return favoriteService.getMyFavorites(principal.getName());
    }

    @GetMapping("/plots/favorite-ids")
    public Set<Integer> getMyFavoriteIds(Principal principal) {
        return favoriteService.getMyFavoritePlotIds(principal.getName());
    }
}
