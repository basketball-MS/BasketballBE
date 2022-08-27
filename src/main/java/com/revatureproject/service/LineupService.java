package com.revatureproject.service;

import com.revatureproject.exception.UserNotFoundException;
import com.revatureproject.models.Lineup;
import com.revatureproject.repository.LineupRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LineupService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LineupRepo repo;


    public LineupService(LineupRepo repo)
    {
        this.repo = repo;
    }

    public Set<Lineup> getAllLineups()
    {

        return repo.findAll().stream().collect(Collectors.toSet());
    }


    public Lineup addLineup(Lineup l) {
        System.out.println(l);
        Lineup nl = repo.save(l);
        return nl;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(int id)
    {
        repo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Lineup getById(int id)
    {

        if (id <= 0)
        {
            logger.warn("Id cannot be <= 0. Id passed was: {}", id);
            return null;
        } else
        {
            return repo.findById(id).orElseThrow(() -> new UserNotFoundException("No Lineup found with id " + id));
        }

    }
}
