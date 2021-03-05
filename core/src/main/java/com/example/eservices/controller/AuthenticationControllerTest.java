package com.example.eservices.controller;

import com.example.eservices.bean.AuthDetails;
import com.example.eservices.entity.User;
import com.example.eservices.exceptions.UserNotFoundException;
import com.example.eservices.impl.AuthenticationServiceImpl;
import com.example.eservices.interfaces.IAuthenticationService;
import com.example.eservices.mapper.Mapper;
import com.example.eservices.repositories.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {
    @MockBean
    private UserRepo userRepository;

    @MockBean
    private IAuthenticationService iAuthenticationService;

    @MockBean
    Mapper mapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    private MockMvc mockMvc;

    private User user;
    private AuthDetails authDetails;


    @Before
    public void setup(){

        user = new User();
        user.setFirstName("abc");
        user.setLastName("");
        user.setId(1L);
        user.setPassword("!*!Y#(*)j0fi");

        authDetails = new AuthDetails();
        authDetails.setUsername("user");
        authDetails.setPassword("pwd");
    }

    @Test
    public void whenRequestToAuthWithValidDetails_thenCorrectResponse() throws Exception {
        MediaType textPlainUtf8 = new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8"));
        String user = "{\"username\": \"foo\", \"password\" : \"bar@domain.com\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users/authenticate")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void whenRequestToAuthWithInValidDetails_thenErrorResponse() throws Exception {
        String user = "{\"username\": null, \"password\" : \"avc\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users/authenticate")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void whenRequestToAuthWithInValidPwd_thenErrorResponse() throws Exception {
        String user = "{\"username\": \"foo\", \"password\" : null}";
        mockMvc.perform(MockMvcRequestBuilders.post("/users/authenticate")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());


    }

    @Test
    public void whenRequestToAuthWithValidAndWrongInput_thenErrorResponse() throws Exception{
        String user = "{\"username\": \"foo\", \"password\" : \"bar\"}";

        when(iAuthenticationService.authenticate(Mockito.anyString(), Mockito.anyString()))
                .thenThrow(UserNotFoundException.class);
        when(mapper.mapFromEntity(Mockito.any())).thenReturn(Mockito.mock(AuthDetails.class));
        mockMvc.perform(MockMvcRequestBuilders.post("/users/authenticate")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verifyNoInteractions(mapper);
    }

    @Test
    public void whenRequestToAuthWithValidInput_thenCorrectResponse() throws Exception{


        when(iAuthenticationService.authenticate(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(user);


        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users/authenticate")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(objectMapper.writeValueAsBytes(authDetails));

        mockMvc.perform(builder)
                .andExpect(status().isOk());


    }
    }

