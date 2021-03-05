package com.example.eservices.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Customer {

	private long id;
	@NonNull
	private String username;
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	@NonNull
	private String password;

	private Boolean enabled;



}
