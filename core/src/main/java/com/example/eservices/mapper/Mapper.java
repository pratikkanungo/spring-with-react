package com.example.eservices.mapper;


import com.example.eservices.bean.AuthDetails;
import com.example.eservices.bean.Customer;
import com.example.eservices.entity.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
	
	public AuthDetails mapFromEntity(User entity) {
		AuthDetails authDetails = new AuthDetails();
		authDetails.setId(entity.getId());
		authDetails.setUsername(entity.getUsername());
		authDetails.setRole(entity.getRole().getName());

		return authDetails;
	}

	public User mapToEntity(Customer customer, User user){
		user.setPassword(customer.getPassword());
		user.setEnabled(customer.getEnabled());
		user.setLastName(customer.getLastName());
		user.setFirstName(customer.getFirstName());
		user.setUsername(customer.getUsername());

		return user;
	}

	public Customer mapCustomerFromEntity(User entity) {
		Customer customer = new Customer();
		customer.setEnabled(entity.isEnabled());
		customer.setFirstName(entity.getFirstName());
		customer.setLastName(entity.getLastName());
		customer.setUsername(entity.getUsername());
		customer.setId(entity.getId());

		return customer;

	}



}
