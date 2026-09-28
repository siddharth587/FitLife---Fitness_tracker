package com.project.Fitness_Tracker_Monolith_Architecture.Dtos;

import com.project.Fitness_Tracker_Monolith_Architecture.Entity.Activity_type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {
    private String id;
    private String user_id;
    private Activity_type type;
    private Map<String,Object> additonal_metrics; // isme data json format mai store hoga
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
