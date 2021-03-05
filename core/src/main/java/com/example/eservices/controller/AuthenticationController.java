package com.example.eservices.controller;

import com.example.eservices.bean.AuthDetails;
import com.example.eservices.interfaces.IAuthenticationService;
import com.example.eservices.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {


    @Autowired
    IAuthenticationService iAuthenticationService;

    @Autowired
    Mapper mapper;

    @PostMapping("/users/authenticate")
    public AuthDetails authenticate(@RequestBody AuthDetails user)  {

        return mapper.mapFromEntity(iAuthenticationService.authenticate(user.getUsername(), user.getPassword()));

    }


}
