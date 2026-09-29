package com.project.Fitness_Tracker_Monolith_Architecture.Service;

import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.ActivityRequest;
import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.ActivityResponse;
import com.project.Fitness_Tracker_Monolith_Architecture.Entity.Activity;
import com.project.Fitness_Tracker_Monolith_Architecture.Entity.User;
import com.project.Fitness_Tracker_Monolith_Architecture.Repository.ActivityRepository;
import com.project.Fitness_Tracker_Monolith_Architecture.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;



    public ActivityResponse trackActivity(ActivityRequest request) {
        User user = userRepository.findById(request.getUser_id())
                .orElseThrow(()->new RuntimeException("Invalid User:"+request.getUser_id()));
        Activity activity = Activity.builder()
                .user(user)
                .additonal_metrics(request.getAdditional_metrics())
                .caloriesBurned(request.getCaloriesBurned())
                .duration(request.getDuration())
                .startTime(request.getStartTime())
                .type(request.getType())
                .build();
        Activity savedActivity =  activityRepository.save(activity);
        return mapToResponse(savedActivity);
    }

    private ActivityResponse mapToResponse(Activity savedActivity) {
        ActivityResponse response = new ActivityResponse();
        response.setId(savedActivity.getId());
        response.setUser_id(savedActivity.getUser().getId()); //Method Chaining
        response.setAdditonal_metrics(savedActivity.getAdditonal_metrics());
        response.setCaloriesBurned(savedActivity.getCaloriesBurned());
        response.setStartTime(savedActivity.getStartTime());
        response.setDuration(savedActivity.getDuration());
        response.setType(savedActivity.getType());
        response.setCreatedAt(savedActivity.getCreatedAt());
        response.setUpdatedAt(savedActivity.getUpdatedAt());
        return response;
    }
    public List<ActivityResponse> getUserActivites(String userId) {
        List<Activity> activityList = activityRepository.findByUserId(userId);
        //1)Activity->ActivityResponse
        //2)Collect in list and return
        return activityList.stream().map(this::mapToResponse).collect(Collectors.toList());
    }
}
