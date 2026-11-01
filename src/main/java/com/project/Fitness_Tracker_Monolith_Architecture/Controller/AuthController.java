package com.project.Fitness_Tracker_Monolith_Architecture.Controller;

import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.LoginRequest;
import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.LoginResponse;
import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.RegisterRequest;
import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.UserResponse;
import com.project.Fitness_Tracker_Monolith_Architecture.Entity.User;
import com.project.Fitness_Tracker_Monolith_Architecture.Repository.UserRepository;
import com.project.Fitness_Tracker_Monolith_Architecture.Security.JwtUtils;
import com.project.Fitness_Tracker_Monolith_Architecture.Service.UserService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    //    public AuthController(UserService userService) {
//        this.userService = userService; //if we are using required args constructors then we don't need this constructor because it automatically creates constructor for fields marked with final
//    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = null;
        try {
            User user = userRepository.findByEmail(loginRequest.getEmail());
            if (user == null) {
                return ResponseEntity.status(401).build();
            }
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.status(401).build();
            }
            String token = jwtUtils.generateToken(user.getId(), user.getRole().name());
            return ResponseEntity.ok(new LoginResponse(token, userService.mapToReponse(user)));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }
    }
}
