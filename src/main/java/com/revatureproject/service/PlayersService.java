package com.revatureproject.service;

import com.revatureproject.exception.UserNotFoundException;
import com.revatureproject.models.Players;
import com.revatureproject.repository.PlayerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayersService
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PlayerRepo repo;


    public PlayersService(PlayerRepo repo)
    {
        this.repo = repo;
    }

    public Set<Players> getAllPlayers()
    {

        return repo.findAll().stream().collect(Collectors.toSet());
    }


    public Players addPlayer(Players p) {
        System.out.println(p);
        Players players= repo.save(p);
        return players;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(int id)
    {
        repo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Players getById(int id)
    {

        if (id <= 0)
        {
            logger.warn("Id cannot be <= 0. Id passed was: {}", id);
            return null;
        } else
        {
            return repo.findById(id).orElseThrow(() -> new UserNotFoundException("No player found with id " + id));
        }

    }
}
