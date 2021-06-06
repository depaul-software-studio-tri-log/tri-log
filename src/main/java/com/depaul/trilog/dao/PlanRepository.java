package com.depaul.trilog.dao;

import com.depaul.trilog.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
    List<Plan> findPlansByDate(Date date);
    List<Plan> findPlansByUserOrderByDateDesc(int id);
}
