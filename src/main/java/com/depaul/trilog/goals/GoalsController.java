package com.depaul.trilog.goals;

import com.depaul.trilog.entities.Goal;
import com.depaul.trilog.swim.Swim;
import com.depaul.trilog.swim.SwimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GoalsController {
    @Autowired
    private GoalsRepository GoalRepo;

    @GetMapping("/goals")
    public String goals() {
        return "goals";
    }

    @PostMapping
    public String createGoal(@ModelAttribute("newGoal") Goal goal) {
        GoalRepo.save(goal);
        return "goals";

    }
}
