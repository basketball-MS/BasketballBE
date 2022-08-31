package com.revatureproject.controllers;

import com.revatureproject.models.Players;
import com.revatureproject.exception.UserNotFoundException;
import com.revatureproject.models.Teams;
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

    @PatchMapping("/team/{id}/{team_id}")
    public ResponseEntity<Players> updatePlayerTeam(@PathVariable("id") int id, @RequestBody int team_id) {
        Players player = service.getById(id);
        player.setTeam_id(team_id);
        return new ResponseEntity<Players>(service.addPlayer(player), HttpStatus.OK);
    }

    @PatchMapping("/weight/{id}/{weight}")
    public ResponseEntity<Players> updatePlayerWeight(@PathVariable("id") int id, @RequestBody int weight) {
        Players player = service.getById(id);
        player.setWeight(weight);
        return new ResponseEntity<Players>(service.addPlayer(player), HttpStatus.OK);
    }

    @PatchMapping("/heightft/{id}/{height_feets}")
    public ResponseEntity<Players> updatePlayerHeightFt(@PathVariable("id") int id, @RequestBody int height_feets) {
        Players player = service.getById(id);
        player.setHeight_feets(height_feets);
        return new ResponseEntity<Players>(service.addPlayer(player), HttpStatus.OK);
    }

    @PatchMapping("/heightin/{id}/{height_inches}")
    public ResponseEntity<Players> updatePlayerHeightIn(@PathVariable("id") int id, @RequestBody int height_inches) {
        Players player = service.getById(id);
        player.setHeight_inches(height_inches);
        return new ResponseEntity<Players>(service.addPlayer(player), HttpStatus.OK);
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

    @DeleteMapping("/{id}")
    public void removePlayer(@PathVariable("id") int id){

            service.remove(id);
    }



}