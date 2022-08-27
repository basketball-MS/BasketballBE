package com.revatureproject.repository;

import com.revatureproject.models.Lineup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineupRepo extends JpaRepository<Lineup, Integer> {

}
