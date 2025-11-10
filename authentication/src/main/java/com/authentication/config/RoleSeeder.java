package com.authentication.config;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import com.authentication.models.Role;
import com.authentication.repositories.RoleRepository;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... arStrings) {

        if (roleRepository.count() == 0) {
            Role role1 = new Role("admin");
            Role role2 = new Role("customer");
            Role role3 = new Role("vendor");

            roleRepository.save(role1);
            roleRepository.save(role2);
            roleRepository.save(role3);
            System.out.println("Roles initilization!");
        }else{
            System.out.println("Roles already exist!");
        }

    }

}
