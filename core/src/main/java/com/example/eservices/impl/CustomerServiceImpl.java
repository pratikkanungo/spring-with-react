package com.example.eservices.impl;


import com.example.eservices.bean.Customer;
import com.example.eservices.entity.User;
import com.example.eservices.exceptions.NoDataFoundException;
import com.example.eservices.exceptions.UserNotFoundException;
import com.example.eservices.interfaces.ICustomerService;
import com.example.eservices.mapper.Mapper;
import com.example.eservices.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	Mapper customerMapper;

	@Override
	public Long addCustomer(Customer c) {
		User user = new User();
		user = customerMapper.mapToEntity(c, user);
		userRepo.save(user);
		return user.getId();
	}

	@Override
	public Customer getCustomer(Long id) {
		User user = userRepo.findById(id).get();
		return customerMapper.mapCustomerFromEntity(user);

	}


	@Override
	public Long updateCustomer(Customer customer) {
		User user = userRepo.findById(customer.getId())
				.orElseThrow(() ->new UserNotFoundException(String.valueOf(customer.getId())));
		user = customerMapper.mapToEntity(customer, user);
		userRepo.save(user);
		return user.getId();
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		List<User> entities= (List<User>)userRepo.findAll();
		if(entities.isEmpty()){
			throw new NoDataFoundException();
		}
		entities.forEach(entity -> {
			customers.add(customerMapper.mapCustomerFromEntity(entity));
		});
		return customers;
	}

	@Override
	public Long deleteCustomer(Long id) {
		User entity = Optional.ofNullable(userRepo.findById(id)).get()
				.orElseThrow(() ->new NoDataFoundException());
		userRepo.delete(entity);
		return entity.getId();
	}

}
