package com.example.eservices.interfaces;

import com.example.eservices.bean.Customer;

import java.util.List;

public interface ICustomerService {
	public Long addCustomer(Customer p);
	public Customer getCustomer(Long id);
	public Long updateCustomer(Customer p);
	public List<Customer> getAllCustomers();
	public Long deleteCustomer(Long id);

}
