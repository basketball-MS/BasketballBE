package com.revatureproject.controllers;

import com.revatureproject.models.Teams;
import com.revatureproject.exception.UserNotFoundException;
import com.revatureproject.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/teams")

public class TeamsController
{
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private TeamService service;

    public TeamsController(TeamService service)
    {
        this.service = service;
    }

    @GetMapping
    public Set<Teams> getAllTeams()
    {
        return service.getAllTeams();
    }

    @PostMapping
    public Teams addTeam(@RequestBody Teams Team) {

        return service.addTeam(Team);
    }

    @PatchMapping("/{id}/{location}")
    public ResponseEntity<Teams> updateTeamLocation(@PathVariable("id") int id, @RequestBody String location) {
        Teams team = service.getById(id);
        team.setLocation(location);
        return new ResponseEntity<Teams>(service.addTeam(team), HttpStatus.OK);
    }

    @PatchMapping("/change/{id}/{teamName}")
    public ResponseEntity<Teams> updateTeamName(@PathVariable("id") int id, @RequestBody String teamName) {
        Teams team = service.getById(id);
        team.setTeamName(teamName);
        return new ResponseEntity<Teams>(service.addTeam(team), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teams> findByTeamId(@PathVariable("id") int id){
        if (id > 0)
        {
            return ResponseEntity.ok(service.getById(id));
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public void removeTeam(@PathVariable("id") int id){

        service.remove(id);
    }



}
