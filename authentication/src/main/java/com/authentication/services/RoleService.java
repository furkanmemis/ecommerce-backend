package com.authentication.services;

import com.authentication.repositories.RoleRepository;
import com.authentication.models.Role;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    
    public RoleService(RoleRepository repository){
        this.roleRepository = repository;
    }

    public List<Role> GetAll(){
        return roleRepository.findAll();
    }

    public Optional<Role> GetRoleById (UUID id){
        return this.roleRepository.findById(id);
    }

}
