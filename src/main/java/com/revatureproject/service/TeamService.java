package com.revatureproject.service;

import com.revatureproject.exception.UserNotFoundException;
import com.revatureproject.models.Teams;
import com.revatureproject.repository.TeamsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeamService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TeamsRepo repo;


    public TeamService(TeamsRepo repo)
    {
        this.repo = repo;
    }

    public Set<Teams> getAllTeams()
    {

        return repo.findAll().stream().collect(Collectors.toSet());
    }


    public Teams addTeam(Teams t) {
        System.out.println(t);
        Teams nt = repo.save(t);
        return nt;
    }

    public Teams updateTeam(Teams t, int id) {
        Optional<Teams> team = repo.findById(id);
        return repo.save(t);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(int id)
    {
        repo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Teams getById(int id)
    {

        if (id <= 0)
        {
            logger.warn("Id cannot be <= 0. Id passed was: {}", id);
            return null;
        } else
        {
            return repo.findById(id).orElseThrow(() -> new UserNotFoundException("No Team found with id " + id));
        }

    }
}
