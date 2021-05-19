package com.depaul.trilog.services;

import com.depaul.trilog.dao.PlanRepository;
import com.depaul.trilog.entities.Plan;
import com.depaul.trilog.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepo;

    @Autowired
    private UserService userService;

    public List<Plan> getAllPlans() {
        return this.planRepo.findAll();
    }

    public void savePlan(Plan plan) {
        User currentUser = userService.getCurrentUser();
        plan.setUser(currentUser.getId());
        this.planRepo.save(plan);
    }
}
