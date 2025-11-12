package com.authentication.services;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.authentication.models.User;
import com.authentication.dto.LoginResponse;
import com.authentication.dto.SignUpRequest;

@Service
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthService(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    public LoginResponse Login(String email, String password) {

        Optional<User> optionalUser = userService.findByEmailAndPassword(email, password);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Any user not found");
        } else {
            User user = optionalUser.get();
            String token = jwtService.CreateToken(user);
            return new LoginResponse(token, user.getName(), user.getSurname(), user.getEmail());
        }

    }

    public String SignUp(SignUpRequest request) throws Exception {
        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User createdUser = userService.CreateUser(user);

        if (createdUser == null) {
            throw new Exception("User signup error");
        }

        return createdUser.getId().toString();
    }

}
