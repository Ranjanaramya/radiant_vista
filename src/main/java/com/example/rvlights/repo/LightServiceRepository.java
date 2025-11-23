package com.example.rvlights.repo;

import com.example.rvlights.model.LightService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LightServiceRepository extends JpaRepository<LightService, Long> {
}
