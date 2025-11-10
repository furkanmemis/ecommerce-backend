package com.authentication.dto;

import io.swagger.v3.oas.annotations.media.Schema;


public class LoginRequest {
    
    @Schema(description = "User email")
    private String email;
    
    @Schema(description = "User password")
    private String password;



    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

}
