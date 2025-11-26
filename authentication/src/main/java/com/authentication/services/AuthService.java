package com.authentication.services;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.authentication.models.User;
import com.authentication.dto.LoginResponse;
import com.authentication.dto.SignUpRequest;
import com.authentication.models.Role;
import com.authentication.models.Log;

@Service
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final RoleService roleService;
    private final LogService logService;

    public AuthService(UserService userService, JwtService jwtService, EmailService emailService,
            RoleService roleService, LogService logService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.roleService = roleService;
        this.logService = logService;
    }

    public LoginResponse Login(String email, String password) throws NoSuchAlgorithmException {

        String hashedPassword = EncryptionService.toHexString(EncryptionService.getSHA(password));

        Optional<User> optionalUser = userService.findByEmailAndPassword(email, hashedPassword);

        if (optionalUser.isEmpty()) {
            Log log = new Log("login-fail", "auth-service", email + " login error");
            logService.saveLog(log);
            throw new RuntimeException("Any user not found");
        } else {
            User user = optionalUser.get();
            String token = jwtService.CreateToken(user);
            Log log = new Log("login", "auth-service", user.getEmail() + " login");
            logService.saveLog(log);
            return new LoginResponse(token, user.getName(), user.getSurname(), user.getEmail());
        }

    }

    public String SignUp(SignUpRequest request) throws Exception {

        Role userRole = roleService.GetRoleByName("customer");

        if (userRole == null) {
            throw new Error("Sign up error, role not found");
        }

        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(EncryptionService.toHexString(EncryptionService.getSHA(request.getPassword())));
        user.setRoleUuid(userRole.getId());

        User createdUser = userService.CreateUser(user);

        if (createdUser == null) {
            throw new Exception("User signup error");
        }

        Log log = new Log("create-user", "auth-service", user.getEmail() + " created");
        logService.saveLog(log);

        this.emailService.sendMessage("email-notifications", createdUser.getId().toString(), createdUser.getEmail());

        return createdUser.getId().toString();
    }

}
