package com.revatureproject.repository;

import com.revatureproject.models.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends JpaRepository<Players, Integer>
{

}
