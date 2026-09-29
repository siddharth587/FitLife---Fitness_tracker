package com.project.Fitness_Tracker_Monolith_Architecture.Dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.Fitness_Tracker_Monolith_Architecture.Entity.Activity;
import com.project.Fitness_Tracker_Monolith_Architecture.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationRequest {
    private String userId;
    private String activityId;
    private List<String> improvements;
    private List<String> suggestions;
    private List<String> safety;
}
