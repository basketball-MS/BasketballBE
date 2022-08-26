package com.revatureproject.repository;

import com.revatureproject.models.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepo extends JpaRepository<Coach, Integer>
{
}
