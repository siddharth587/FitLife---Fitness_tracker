package com.project.Fitness_Tracker_Monolith_Architecture.Service;

import com.project.Fitness_Tracker_Monolith_Architecture.Entity.User;
import com.project.Fitness_Tracker_Monolith_Architecture.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public User register(User user) {
        return userRepository.save(user);
    }
}
