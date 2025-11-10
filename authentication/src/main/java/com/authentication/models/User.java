package com.authentication.models;

import java.util.UUID;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique user UUID", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Column(nullable = false)
    @Schema(description = "User name", example = "Zeynep")
    private String name;

    @Column(nullable = false)
    @Schema(description = "User surname", example = "Memiş")
    private String surname;

    @Column(nullable = false)
    @Schema(description = "User password", example = "123456")
    private String password;

    @Column(nullable = false, unique = true)
    @Schema(description = "User email", example = "zeynep@example.com")
    private String email;

    @Schema(description = "Is user verify own email", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "IsEmailVerify")
    private boolean isEmailVerify = false;

    public User() {
    }

    public User(String name, String surname, String password, String email) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.isEmailVerify = false;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsEmailVerify(){
        return this.isEmailVerify;
    }

    public void setIsEmailVerify(boolean value){
        this.isEmailVerify = value;
    }
}
