package com.depaul.trilog.web;

import com.depaul.trilog.entities.Plan;
import com.depaul.trilog.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Controller
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping("/plan")
    public String plan(Model model) {
        model.addAttribute("plans", planService.getAllPlansByUser());
        return "plans/plan-overview";
    }

    @GetMapping("/plan/{date}")
    public String planDay(@PathVariable(value="date") String date, Model model) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Plan plan = planService.findByDate(dateFormat.parse(date));
        if (plan == null) {
            plan = new Plan();
            plan.setDate(dateFormat.parse(date));
        }

        model.addAttribute("plan", plan);
        return "plans/plan-edit";
    }

    @PostMapping("/plan/add")
    public String save(@ModelAttribute("plan") Plan plan, BindingResult bindingResult, Model model) {
        planService.savePlan(plan);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String date = dateFormat.format(plan.getDate());
        return "redirect:/plan/" + date;
    }
}
