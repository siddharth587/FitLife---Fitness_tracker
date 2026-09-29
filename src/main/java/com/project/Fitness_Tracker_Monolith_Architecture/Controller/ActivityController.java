package com.project.Fitness_Tracker_Monolith_Architecture.Controller;

import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.ActivityRequest;
import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.ActivityResponse;
import com.project.Fitness_Tracker_Monolith_Architecture.Entity.Activity;
import com.project.Fitness_Tracker_Monolith_Architecture.Service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actvities")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activtiyService;
    @PostMapping
    private ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request){
        return ResponseEntity.ok(activtiyService.trackActivity(request));
    }
    @GetMapping
    private ResponseEntity<List<ActivityResponse>> getActivity(@RequestHeader(value = "X-User-ID") String userId){
        return ResponseEntity.ok(activtiyService.getUserActivites(userId));
    }
}
