package com.authentication.services;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.authentication.models.User;
import com.authentication.dto.LoginResponse;
import com.authentication.dto.SignUpRequest;

@Service
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private EmailService emailService;

    public AuthService(UserService userService, JwtService jwtService, EmailService emailService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.emailService = emailService;
    }

    public LoginResponse Login(String email, String password) throws NoSuchAlgorithmException {

        String hashedPassword = EncryptionService.toHexString(EncryptionService.getSHA(password));

        Optional<User> optionalUser = userService.findByEmailAndPassword(email, hashedPassword);

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
        user.setPassword(EncryptionService.toHexString(EncryptionService.getSHA(request.getPassword())));

        User createdUser = userService.CreateUser(user);

        if (createdUser == null) {
            throw new Exception("User signup error");
        }

        this.emailService.sendMessage("email-notifications", createdUser.getId().toString(), createdUser.getEmail());

        return createdUser.getId().toString();
    }

}
