package com.authentication.repositories;

import org.springframework.stereotype.Repository;
import com.authentication.models.*;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByRoleUuid(UUID roleUuid);

}