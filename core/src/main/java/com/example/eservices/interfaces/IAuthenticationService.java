package com.example.eservices.interfaces;


import com.example.eservices.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface IAuthenticationService {
	public User authenticate(String userName, String pwd);

}
