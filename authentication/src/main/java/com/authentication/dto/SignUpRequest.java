package com.authentication.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class SignUpRequest {

    @Schema(description = "User email")
    private String email;

    @Schema(description = "User password")
    private String password;

    @Schema(description = "User name")
    private String name;

    @Schema(description = "User surname")
    private String surname;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

}
