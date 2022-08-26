package com.revatureproject.service;

import com.revatureproject.dto.Credentials;
import com.revatureproject.exception.UserNotFoundException;
import com.revatureproject.models.Users;
import com.revatureproject.repository.UserRepo;
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
public class UserService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private UserRepo userRepo;


    @Autowired
    public UserService(UserRepo userRepo) {
        super();
        this.userRepo = userRepo;

    }




    // login method
    public Users getByCredentials(Credentials creds) {

        Optional<Users> userInDb = userRepo.findByUsernameAndPassword(creds.getUsername(), creds.getPassword());

        if (userInDb.isPresent()) {

            log.info("Found user with username {}", creds.getUsername());

            // assign a JWT token to the response
            // so now the client can re-access the server after having logged in

            return userInDb.get(); // .get() method returns the VALUE of the User object inside the Optional object
        } else {
            log.warn("Username & password combination did not match for user {}", creds.getUsername());
            return null;
        }
    }


    // return a set of all users in the DB

    @Transactional(readOnly = true)
    public Set<Users> getAll() {

        return userRepo.findAll().stream().collect(Collectors.toSet());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Users add(Users u) {

        return userRepo.save(u);
    }

    @Transactional(propagation = Propagation.REQUIRED) // default setting of transactions in Spring
    public void remove(int id) {
        userRepo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Users getByUsername(String username) throws UserNotFoundException
    {

        return userRepo.findByUsername(username) // in the case that no User object can be returned, throw an exception
                .orElseThrow(() -> new UserNotFoundException("No user found with username " + username));
    }

    @Transactional(readOnly = true)
    public Users getById(int id) {

        if (id <= 0) {
            log.warn("Id cannot be <= 0. Id passed was: {}", id);
            return null;
        } else {
            return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("No user found with id " + id));
        }

    }


}

