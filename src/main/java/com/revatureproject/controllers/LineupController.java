package com.revatureproject.controllers;

import com.revatureproject.models.Lineup;
import com.revatureproject.exception.UserNotFoundException;
import com.revatureproject.service.LineupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/lineups")

public class LineupController
{
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private LineupService service;

    public LineupController(LineupService service)
    {
        this.service = service;
    }

    @GetMapping
    public Set<Lineup> getAllLineups()
    {
        return service.getAllLineups();
    }

    @PostMapping
    public Lineup addLineup(@RequestBody Lineup Lineup) {

        return service.addLineup(Lineup);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lineup> findByLineupId(@PathVariable("id") int id){
        if (id > 0)
        {
            return ResponseEntity.ok(service.getById(id));
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public void removeLineup(@PathVariable("id") int id){

        service.remove(id);
    }



}
