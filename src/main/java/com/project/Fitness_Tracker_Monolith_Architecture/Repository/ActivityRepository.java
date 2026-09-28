package com.project.Fitness_Tracker_Monolith_Architecture.Repository;

import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.ActivityRequest;
import com.project.Fitness_Tracker_Monolith_Architecture.Dtos.ActivityResponse;
import com.project.Fitness_Tracker_Monolith_Architecture.Entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,String> {
    List<ActivityResponse> findAll(ActivityRequest request);
}
