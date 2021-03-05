package com.example.eservices.controller;


import com.example.eservices.AbstractTest;
import com.example.eservices.bean.Customer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

public class CustomerControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testGetAllPersons() throws Exception {
        String uri = "/v1/getAllCustomers/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Customer[] customers = super.mapFromJson(content, Customer[].class);
        assertTrue(customers.length > 0);
    }
    @Test
    public void addPerson() throws Exception {
        String uri = "/v1/addCustomer";
        Customer customer = new Customer();
        customer.setUsername("123");
        customer.setFirstName("abc");
        customer.setLastName("efg");
        customer.setPassword("test");
        customer.setEnabled(true);
        String inputJson = super.mapToJson(customer);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertNotNull(content);
    }

    @Test
    public void deleteProduct() throws Exception {
        String uri = "/v1/removeCustomer/1";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "1");
    }
}

