package com.example.smartplot.repository;

import com.example.smartplot.model.PendingSignup;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingSignupRepository extends JpaRepository<PendingSignup, Integer> {

    Optional<PendingSignup> findByEmail(String email);

    void deleteByEmail(String email);
}
