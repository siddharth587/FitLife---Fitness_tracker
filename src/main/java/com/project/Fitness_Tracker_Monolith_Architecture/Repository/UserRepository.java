package com.project.Fitness_Tracker_Monolith_Architecture.Repository;

import com.project.Fitness_Tracker_Monolith_Architecture.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
