package com.depaul.trilog.services;

import com.depaul.trilog.dao.GoalsRepository;
import com.depaul.trilog.entities.Goals;
import com.depaul.trilog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalsService {

    private final GoalsRepository goalsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public GoalsService(GoalsRepository goalsRepository) {
        this.goalsRepository = goalsRepository;
    }

    public void addNewGoal(Goals goals) {
        goals.setUser_id(userService.getCurrentUser().getId());
        goalsRepository.save(goals);
    }

//    public List<Goal> getGoals(){
//        return goalsRepository.findByUser_id(userService.getCurrentUser().getId());
//    }
}
