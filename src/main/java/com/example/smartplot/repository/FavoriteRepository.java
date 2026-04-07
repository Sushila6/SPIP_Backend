package com.example.smartplot.repository;

import com.example.smartplot.model.Favorite;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    List<Favorite> findByUserEmailIgnoreCaseOrderByFavoriteIdDesc(String userEmail);

    Optional<Favorite> findByPlotIdAndUserEmailIgnoreCase(Integer plotId, String userEmail);

    boolean existsByPlotIdAndUserEmailIgnoreCase(Integer plotId, String userEmail);
}
