package com.depaul.trilog.dao;

import com.depaul.trilog.entities.Goals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalsRepository extends JpaRepository<Goals, Integer> {
    List<Goals> findGoalsByUseridOrderByIdDesc(int userid);
    List<Goals> findGoalsByActivity(int activity);
    Goals findFirstById(int id);
}
