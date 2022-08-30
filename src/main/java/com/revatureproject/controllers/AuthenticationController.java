package com.revatureproject.controllers;


import javax.servlet.http.HttpServletResponse;

import com.revatureproject.dto.Credentials;
import com.revatureproject.models.Users;
import com.revatureproject.service.UserService;
import com.revatureproject.util.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/login")
public class AuthenticationController {

    UserService userv;
    JwtTokenManager tokenManager;

    @Autowired
    public AuthenticationController(UserService userv, JwtTokenManager tokenManager) {
        this.userv = userv;
        this.tokenManager = tokenManager;
    }

    @PostMapping
    public Users login(@RequestBody Credentials creds, HttpServletResponse response) {

        Users user = userv.getByCredentials(creds);

        if (user != null) {


            String token = tokenManager.issueToken(user);

            // append the token to the header of the RESPONSE
            response.addHeader("btms-token", token); // entire token issuer.payload.signature
            response.addHeader("Access-Control-Expose-Headers", "btms-token");
            response.setStatus(200);


            return user;

        } else {

            response.setStatus(401);
            return null;
        }
    }

}

