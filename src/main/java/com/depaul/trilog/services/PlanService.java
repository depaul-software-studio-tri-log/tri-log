package com.depaul.trilog.services;

import com.depaul.trilog.dao.PlanRepository;
import com.depaul.trilog.entities.Plan;
import com.depaul.trilog.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepo;

    @Autowired
    private UserService userService;

    public List<Plan> getAllPlansByUser() {
        return this.planRepo.findPlansByUser(getCurrentUserId());
    }

    public void savePlan(Plan plan) {
        Plan existingPlan = findByDate(plan.getDate());

        if (existingPlan != null) {
            plan.setId(existingPlan.getId());
            plan.setUser(existingPlan.getUser());
        }
        else {
            plan.setUser(getCurrentUserId());
        }

        this.planRepo.save(plan);
    }

    public Plan findByDate(Date date) {
        Plan plan = null;

        List<Plan> plans = this.planRepo.findPlansByDate(date);
        if (!plans.isEmpty()) {
            plan = plans.stream().filter(p -> p.getUser() == getCurrentUserId()).findAny().orElse(null);
        }

        return plan;
    }

    private int getCurrentUserId() {
        User currentUser = userService.getCurrentUser();
        return currentUser.getId();
    }
}
