package com.depaul.trilog.web;

import com.depaul.trilog.entities.Plan;
import com.depaul.trilog.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping("/plan")
    public String plan(Model model) {
        model.addAttribute("plan", new Plan());
        model.addAttribute("plans", planService.getAllPlans());
        return "plan";
    }

    @PostMapping("/plan/add")
    public String save(@ModelAttribute("plan") Plan plan, BindingResult bindingResult, Model model) {
        planService.savePlan(plan);
        return "redirect:/plan";
    }
}
