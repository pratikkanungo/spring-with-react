package com.example.eservices.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller 
public class FrameworkController {

	@RequestMapping(value = "/")
	public String index() {
		return "index"; 
	}

	@RequestMapping(value = "/admin")
	public String admin() {
		return "index";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "index";
	}




}
