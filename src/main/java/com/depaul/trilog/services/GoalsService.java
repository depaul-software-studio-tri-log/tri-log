package com.depaul.trilog.services;

import com.depaul.trilog.dao.GoalsRepository;
import com.depaul.trilog.entities.Goals;
import com.depaul.trilog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalsService {

    @Autowired
    private GoalsRepository goalsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public GoalsService(GoalsRepository goalsRepository) {
        this.goalsRepository = goalsRepository;
    }

    public void addNewGoal(Goals goals) {
        goals.setUserid(userService.getCurrentUser().getId());
        goalsRepository.save(goals);
    }

    public List<Goals> getGoalsByActivity(int activity) {
        List<Goals> goalsByActivity = this.goalsRepository.findGoalsByActivity(activity);
        List<Goals> goals = goalsByActivity.stream().filter(g -> g.getUserid() == userService.getCurrentUser().getId())
                .collect(Collectors.toList());
        return goals;
    }

    public List<Goals> getAllGoalsByUser() {
        return this.goalsRepository.findGoalsByUseridOrderByIdDesc(userService.getCurrentUser().getId());
    }
}
