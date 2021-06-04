package com.depaul.trilog.web;

import com.depaul.trilog.dao.RaceRepository;
import com.depaul.trilog.entities.Plan;
import com.depaul.trilog.entities.User;
import com.depaul.trilog.services.PlanService;
import com.depaul.trilog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlanService planService;

    @Autowired
    private RaceRepository raceRepository;

    @GetMapping("/")
    public String home(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        model.addAttribute("nextRace", raceRepository.getNextRaceForUser(user.getId()));

        Date dateToday = new Date();
        Plan planToday = planService.findByDate(dateToday);
        model.addAttribute("planToday", planToday);
        return "home";
    }
}
