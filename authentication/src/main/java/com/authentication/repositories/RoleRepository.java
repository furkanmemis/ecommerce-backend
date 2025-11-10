package com.authentication.repositories;

import org.springframework.stereotype.Repository;
import com.authentication.models.Role;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID>{
    
}
