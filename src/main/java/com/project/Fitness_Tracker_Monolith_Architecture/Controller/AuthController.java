package com.project.Fitness_Tracker_Monolith_Architecture.Controller;

import com.project.Fitness_Tracker_Monolith_Architecture.Entity.User;
import com.project.Fitness_Tracker_Monolith_Architecture.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

//    public AuthController(UserService userService) {
//        this.userService = userService; //if we are using required args constructors then we don't need this constructor because it automatically creates constructor for fields marked with final
//    }
    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }

}
