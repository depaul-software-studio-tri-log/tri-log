package com.depaul.trilog.web;

import com.depaul.trilog.dao.RaceRepository;
import com.depaul.trilog.entities.Goals;
import com.depaul.trilog.entities.Plan;
import com.depaul.trilog.entities.User;
import com.depaul.trilog.services.GoalsService;
import com.depaul.trilog.services.PlanService;
import com.depaul.trilog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlanService planService;

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private GoalsService goalsService;

    @GetMapping("/")
    public String home(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        model.addAttribute("nextRace", raceRepository.getNextRaceForUser(user.getId()));

        Calendar calendar = Calendar.getInstance();
        Date dateToday = calendar.getTime();
        Plan planToday = planService.findByDate(dateToday);
        model.addAttribute("planToday", planToday);

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date dateTomorrow = calendar.getTime();
        Plan planTomorrow = planService.findByDate(dateTomorrow);
        model.addAttribute("planTomorrow", planTomorrow);

        List<Goals> swimGoals = goalsService.getGoalsByActivity(1);
        int totalSwimDistance = swimGoals.stream().mapToInt(Goals::getDistance).sum();
        int totalSwimTime = swimGoals.stream().mapToInt(Goals::getMinutes).sum();
        int currentSwimDistance = swimGoals.stream().mapToInt(Goals::getDistanceprogress).sum();
        int currentSwimTime = swimGoals.stream().mapToInt(Goals::getMinutesprogress).sum();
        int swimDistanceProgress = (int) (((double) currentSwimDistance / (double) totalSwimDistance) * 100.0);
        int swimTimeProgress = (int) (((double) currentSwimTime / (double) totalSwimTime) * 100.0);
        model.addAttribute("swimDistanceProgress", swimDistanceProgress);
        model.addAttribute("swimTimeProgress", swimTimeProgress);

        List<Goals> cycleGoals = goalsService.getGoalsByActivity(2);
        int totalCycleDistance = cycleGoals.stream().mapToInt(Goals::getDistance).sum();
        int totalCycleTime = cycleGoals.stream().mapToInt(Goals::getMinutes).sum();
        int currentCycleDistance = cycleGoals.stream().mapToInt(Goals::getDistanceprogress).sum();
        int currentCycleTime = cycleGoals.stream().mapToInt(Goals::getMinutesprogress).sum();
        int cycleDistanceProgress = (int) (((double) currentCycleDistance / (double) totalCycleDistance) * 100.0);
        int cycleTimeProgress = (int) (((double) currentCycleTime / (double) totalCycleTime) * 100.0);
        model.addAttribute("cycleDistanceProgress", cycleDistanceProgress);
        model.addAttribute("cycleTimeProgress", cycleTimeProgress);

        List<Goals> runGoals = goalsService.getGoalsByActivity(3);
        int totalRunDistance = runGoals.stream().mapToInt(Goals::getDistance).sum();
        int totalRunTime = runGoals.stream().mapToInt(Goals::getMinutes).sum();
        int currentRunDistance = runGoals.stream().mapToInt(Goals::getDistanceprogress).sum();
        int currentRunTime = runGoals.stream().mapToInt(Goals::getMinutesprogress).sum();
        int runDistanceProgress = (int) (((double) currentRunDistance / (double) totalRunDistance) * 100.0);
        int runTimeProgress = (int) (((double) currentRunTime / (double) totalRunTime) * 100.0);
        model.addAttribute("runDistanceProgress", runDistanceProgress);
        model.addAttribute("runTimeProgress", runTimeProgress);

        return "home";
    }
}
