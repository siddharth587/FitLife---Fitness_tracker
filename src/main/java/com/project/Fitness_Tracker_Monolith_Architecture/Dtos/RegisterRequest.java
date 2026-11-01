package com.project.Fitness_Tracker_Monolith_Architecture.Dtos;

import com.project.Fitness_Tracker_Monolith_Architecture.Entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;
}
