package com.depaul.trilog.goals;

import com.depaul.trilog.dao.UserRepository;
import com.depaul.trilog.entities.Goal;
import com.depaul.trilog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalsService {

    private final GoalsRepository goalsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public GoalsService(GoalsRepository goalsRepository) {
        this.goalsRepository = goalsRepository;
    }

}
