package com.revatureproject.controllers;

import com.revatureproject.models.Players;
import com.revatureproject.exception.UserNotFoundException;
import com.revatureproject.service.PlayersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/players")

public class PlayerController
{
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private PlayersService service;

    public PlayerController(PlayersService service)
    {
        this.service = service;
    }

    @GetMapping
    public Set<Players> getAllPlayers()
    {
        return service.getAllPlayers();
    }

    @PostMapping
    public Players addPlayer(@RequestBody Players player) {

        return service.addPlayer(player);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Players> findByPlayerId(@PathVariable("id") int id){
        if (id > 0)
        {
            return ResponseEntity.ok(service.getById(id));
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public void removePlayer(@PathVariable("id") int id){

            service.remove(id);
    }



}