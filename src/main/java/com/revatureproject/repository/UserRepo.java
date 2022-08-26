package com.revatureproject.repository;

import com.revatureproject.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Integer>
{


    Optional<Users> findByUsernameAndPassword(String username, String password);

    Optional<Users> findByUsername(String username);


}

