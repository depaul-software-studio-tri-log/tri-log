package com.depaul.trilog.web;

import com.depaul.trilog.dao.GoalsRepository;
import com.depaul.trilog.entities.Goals;
import com.depaul.trilog.services.GoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class GoalsController {
    @Autowired
    private GoalsRepository goalRepo;

    @Autowired
    private GoalsService goalsService;

    @GetMapping("/goals")
    public String goals(Model model) {
        model.addAttribute("goals", new Goals());
        model.addAttribute("allGoals", goalsService.getAllGoalsByUser());
        return "goals/goals";
    }

    @GetMapping("/goals/{id}")
    public String editGoal(@PathVariable(value="id") int id, Model model) {
        model.addAttribute("goals", goalRepo.findFirstById(id));
        return "goals/goals-edit";
    }

    @PostMapping("/addGoal")
    public String createNewGoalSubmit(Goals goals) {
        goalsService.addNewGoal(goals);
        return "redirect:/goals";
    }
}
