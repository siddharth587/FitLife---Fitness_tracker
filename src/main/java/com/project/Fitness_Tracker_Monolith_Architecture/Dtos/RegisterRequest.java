package com.project.Fitness_Tracker_Monolith_Architecture.Dtos;

import com.project.Fitness_Tracker_Monolith_Architecture.Entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Password should not be blank")
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;
}
