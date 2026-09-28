package com.project.Fitness_Tracker_Monolith_Architecture.Service;

import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.RegisterRequest;
import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.UserResponse;
import com.project.Fitness_Tracker_Monolith_Architecture.Entity.User;
import com.project.Fitness_Tracker_Monolith_Architecture.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public UserResponse register(RegisterRequest request) {
        User user = new User(
                null,
                request.getEmail(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName(),
                Instant.parse("2026-09-27T21:30:00Z")
                        .atZone(ZoneOffset.UTC)
                        .toLocalDateTime(),
                Instant.parse("2026-09-27T21:30:00Z")
                        .atZone(ZoneOffset.UTC)
                        .toLocalDateTime(),
                List.of(),
                List.of()
        );
        User saveduser = userRepository.save(user);
        return mapToReponse(saveduser);
    }

    private UserResponse mapToReponse(User saveduser) {
        UserResponse response = new UserResponse();
        response.setId(saveduser.getId());
        response.setEmail(saveduser.getEmail());
        response.setPassword(saveduser.getPassword());
        response.setFirstName(saveduser.getFirstName());
        response.setLastName(saveduser.getLastName());
        response.setCreatedAt(saveduser.getCreatedAt());
        response.setUpdatedAt(saveduser.getUpdatedAt());
        return response;
    }
}
