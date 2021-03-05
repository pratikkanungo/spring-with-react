package com.example.eservices.impl;

import com.example.eservices.entity.User;
import com.example.eservices.exceptions.PasswordNotMatchException;
import com.example.eservices.exceptions.UserNotFoundException;
import com.example.eservices.interfaces.IAuthenticationService;
import com.example.eservices.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User authenticate(String userName, String pwd) {
        User user = Optional.ofNullable(userRepo.getUserByUsername(userName))
                .orElseThrow(() ->new UserNotFoundException(userName));
        if(passwordEncoder.matches(pwd, user.getPassword())){
            return user;
        }else {
            throw new  PasswordNotMatchException();
        }

    }
}
