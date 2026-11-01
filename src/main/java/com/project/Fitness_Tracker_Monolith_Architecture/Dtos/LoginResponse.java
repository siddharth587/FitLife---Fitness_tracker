package com.project.Fitness_Tracker_Monolith_Architecture.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String Token;
    private UserResponse userResponse;
}
