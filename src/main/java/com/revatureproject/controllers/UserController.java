package com.revatureproject.controllers;

import com.revatureproject.dto.UserDTO;
import com.revatureproject.models.Users;
import com.revatureproject.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/users")
public class UserController
{

    private ModelMapper modelMapper = new ModelMapper();
    private UserService userv;

    @Autowired
    public UserController(UserService userv)
    {
        super();
        this.userv = userv;
    }


    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam(value = "username", required = false) final String username)
    {


        if (username == null || username.isEmpty())
        {
            return ResponseEntity.ok(userv.getAll());
        } else
        {
            return ResponseEntity.ok(userv.getByUsername(username));
        }
    }

    @PostMapping
    public ResponseEntity<Users> addUser(@Valid @RequestBody UserDTO userDto)
    {

        Users user = modelMapper.map(userDto, Users.class);

        return ResponseEntity.ok(userv.add(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findUserById(@PathVariable("id") int id)
    {
        return ResponseEntity.ok(userv.getById(id));
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable("id") int id)
    {
        userv.remove(id);
    }
}
