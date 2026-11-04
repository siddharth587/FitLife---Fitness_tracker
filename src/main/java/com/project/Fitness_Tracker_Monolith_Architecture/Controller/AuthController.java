package com.project.Fitness_Tracker_Monolith_Architecture.Controller;

import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.LoginRequest;
import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.LoginResponse;
import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.RegisterRequest;
import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.UserResponse;
import com.project.Fitness_Tracker_Monolith_Architecture.Entity.User;
import com.project.Fitness_Tracker_Monolith_Architecture.Security.JwtUtils;
import com.project.Fitness_Tracker_Monolith_Architecture.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtUtils jwtUtils;

    //    public AuthController(UserService userService) {
//        this.userService = userService; //if we are using required args constructors then we don't need this constructor because it automatically creates constructor for fields marked with final
//    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        // validation code
        return ResponseEntity.ok(userService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = null;
        try {
            User user = userService.authenticate(loginRequest);
            String token = jwtUtils.generateToken(user.getId(), user.getRole().name());
            return ResponseEntity.ok(new LoginResponse(token, userService.mapToReponse(user)));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }
    }
}
