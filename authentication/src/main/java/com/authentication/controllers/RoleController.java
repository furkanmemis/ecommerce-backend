package com.authentication.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.services.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.UUID;
import java.util.Optional;
import com.authentication.models.Role;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService service){
        this.roleService = service;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get roles with UUID")
    public Optional<Role> GetById(@RequestParam UUID id) {
        return this.roleService.GetRoleById(id);
    }

    @GetMapping
    @Operation(summary = "Get all roles")
    public List<Role> GetAll() {
        return this.roleService.GetAll();
    }
      
}
