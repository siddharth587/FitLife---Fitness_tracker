package com.project.Fitness_Tracker_Monolith_Architecture.Service;

import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.RecommendationRequest;
import com.project.Fitness_Tracker_Monolith_Architecture.Entity.Activity;
import com.project.Fitness_Tracker_Monolith_Architecture.Entity.Recommendation;
import com.project.Fitness_Tracker_Monolith_Architecture.Entity.User;
import com.project.Fitness_Tracker_Monolith_Architecture.Repository.ActivityRepository;
import com.project.Fitness_Tracker_Monolith_Architecture.Repository.RecommendationRepository;
import com.project.Fitness_Tracker_Monolith_Architecture.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    public Recommendation generaterecommendation(RecommendationRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(()->new RuntimeException("Invalid User:"+request.getUserId()));
        Activity activity = activityRepository.findById(request.getActivityId()).orElseThrow(()->new RuntimeException("Invalid Activity:"+request.getActivityId()));
        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .activity(activity)
                .suggestions(request.getSuggestions())
                .improvements(request.getImprovements())
                .safety(request.getSafety())
                .build();
      return recommendationRepository.save(recommendation);

    }

    public List<Recommendation> getUserrecommendation(String userId) {
        return recommendationRepository.findByUserId(userId);

    }

    public List<Recommendation> getactvityrecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId);
    }
}
