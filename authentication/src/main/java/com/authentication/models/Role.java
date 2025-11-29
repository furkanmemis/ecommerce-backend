package com.authentication.models;

import java.util.UUID;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique role UUID")
    private UUID id;

    @Column(nullable = false, unique = true)
    @Schema(description = "Unique role name", example = "customer")
    private String name;


    public Role(){}

    public Role(String name){
        this.name = name;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setname(String name) { this.name = name; }

}
