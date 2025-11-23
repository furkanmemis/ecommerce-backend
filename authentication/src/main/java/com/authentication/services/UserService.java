package com.authentication.services;

import org.springframework.stereotype.Service;
import com.authentication.repositories.UserRepository;
import com.authentication.models.User;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public User CreateUser(User user) {
        return this.userRepository.save(user);
    }

    public Optional<User> GetUserWithId(UUID id) {
        return this.userRepository.findById(id);
    }

    public List<User> GetAll() {
        return this.userRepository.findAll();
    }

    public Optional<User> findByEmailAndPassword(String email, String password){
        return userRepository.findByEmailAndPassword(email, password);
    }

}
