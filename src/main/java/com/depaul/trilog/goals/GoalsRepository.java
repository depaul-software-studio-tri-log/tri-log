package com.depaul.trilog.goals;

import com.depaul.trilog.entities.Goal;
import com.depaul.trilog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoalsRepository extends JpaRepository<Goal, Integer> {
//    List<Goal> findByUser_id(int User_id);
}
