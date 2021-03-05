package com.example.eservices.controller;

import com.example.eservices.bean.Customer;
import com.example.eservices.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class CustomerController {

	@Autowired
	CustomerServiceImpl customerService;

	@GetMapping("/getAllCustomers")
	public List<Customer> fetchAllCustomerDetails(){
		return customerService.getAllCustomers();
		
	}

	@GetMapping("/getCustomerById/{id}")
	public Customer fetchLoginCustomerDetails(@PathVariable Long id){
		return customerService.getCustomer(id);

	}
	
	@PostMapping("/addCustomer")
	public ResponseEntity<Long> addCustomer(@RequestBody Customer customer){
		return new ResponseEntity(customerService.addCustomer(customer), HttpStatus.CREATED);
		
	}
	
	@PutMapping("/updateCustomer")
	public ResponseEntity<Long> updateCustomer(@RequestBody Customer customer){

		return new ResponseEntity(customerService.updateCustomer(customer), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/removeCustomer/{id}")
	public ResponseEntity<Long> removeCustomer(@PathVariable Long id) {

		return new ResponseEntity(customerService.deleteCustomer(id), HttpStatus.OK);
		
	}
}
