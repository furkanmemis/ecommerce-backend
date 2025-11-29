package com.authentication.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.authentication.services.EncryptionService;
import com.authentication.services.RoleService;
import com.authentication.services.UserService;
import com.authentication.models.Role;
import com.authentication.models.User;

import java.util.Optional;
import java.util.UUID;


@Component
public class AdminSeeder implements CommandLineRunner {


    private final RoleService roleService;
    private final UserService userService;
    
    @Override
    public void run(String... arStrings) throws Exception {

        Role existRole = roleService.GetRoleByName("admin");

        UUID role_id = existRole.getId();

        Optional<User> user = userService.getByRoleId(role_id);

        if(user.isEmpty()){
            String password = "123456";
            String hashed = EncryptionService.toHexString(EncryptionService.getSHA(password));
            User admin = new User("furkan","memiş", hashed, "fmemis0160@gmail.com");
            admin.setRoleUuid(role_id);
            userService.createAdmin(admin);
            System.out.println("Admin initilization");
        }else{
            System.out.println("Admin already exist");
        }

    }

    public AdminSeeder(RoleService roleService, UserService userService){
        this.roleService = roleService;
        this.userService = userService;
    }
}
