package com.project.Fitness_Tracker_Monolith_Architecture.Controller;

import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.RecommendationRequest;
import com.project.Fitness_Tracker_Monolith_Architecture.Entity.Recommendation;
import com.project.Fitness_Tracker_Monolith_Architecture.Service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;
    @PostMapping("/generate")
    private ResponseEntity<Recommendation> generateRecommendation(@RequestBody RecommendationRequest request){
        Recommendation recommendation = recommendationService.generaterecommendation(request);
        return ResponseEntity.ok(recommendation);
    }
    @GetMapping("/user/{userId}")
    private ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable String userId){
        return ResponseEntity.ok(recommendationService.getUserrecommendation(userId));
    }
    @GetMapping("/activity/{activityId}")
    private ResponseEntity<List<Recommendation>> getActivityRecommendation(@PathVariable String activityId){
        return ResponseEntity.ok(recommendationService.getactvityrecommendation(activityId));
    }

}  
