package com.authentication.dto;

import io.swagger.v3.oas.annotations.media.Schema;


public class LoginResponse {


    @Schema(description = "Authentication token")
    private String token;

    @Schema(description = "User name")
    private String name;

    @Schema(description = "User surname")
    private String surname;

    @Schema(description = "User email")
    private String email;


    public LoginResponse(String token, String name, String surname, String email){
        this.token = token;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getToken(){
        return this.token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSurname(){
        return this.surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    
}
