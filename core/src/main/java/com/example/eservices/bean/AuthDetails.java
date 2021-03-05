package com.example.eservices.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class AuthDetails {
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    private String role;


}
