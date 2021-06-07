package com.depaul.trilog.web;

import com.depaul.trilog.dao.CyclingRepository;

import com.depaul.trilog.dao.RunRepository;

import com.depaul.trilog.entities.Cycling;
import com.depaul.trilog.entities.Goals;
import com.depaul.trilog.entities.Run;
import com.depaul.trilog.entities.Swim;
import com.depaul.trilog.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class StatsController {

    @Autowired
    private CyclingRepository cyclingRepo;

    @Autowired
    private CyclingService cyclingServ;

    @Autowired
    private RunRepository runRepo;

    @Autowired
    private RunService runServ;

    @Autowired
    private SwimService swimServ;

    @GetMapping("/stats/overall")
    public String statsOverall(Model model) {
        List<Cycling> cyclings = cyclingServ.getCyclingByUser();
        List<String> cyclingDatesData = new ArrayList<>();
        List<Integer> cyclingDistances = new ArrayList<>();
        List<Integer> cyclingTime = new ArrayList<>();
        cyclings.forEach(cycling-> {
            cyclingDatesData.add(cycling.getCyclingDate().toString());
            cyclingDistances.add(cycling.getDistance());
            cyclingTime.add(cycling.getTime());
        });

        if (cyclings.size() > 5){cyclings.subList(5, cyclings.size()).clear();}

        List<Run> runs = runServ.getRunsByUser(userService.getCurrentUser());
        List<String> runDatesData = new ArrayList<>();
        List<Integer> runDistances = new ArrayList<>();
        List<Integer> runTime = new ArrayList<>();
        runs.forEach(run -> {
            runDatesData.add(run.getRunDate().toString());
            runDistances.add(run.getDistance());
            runTime.add(run.getTime());
        });

        if (runs.size() > 5){ runs.subList(5, runs.size()).clear();}

        List<Swim> swims = swimServ.getSwimsByUser();
        List<String> swimDatesData = new ArrayList<>();
        List<Integer> swimDistances = new ArrayList<>();
        List<Integer> swimTime = new ArrayList<>();
        swims.forEach(swim -> {
            swimDatesData.add(swim.getSwimDate().toString());
            swimDistances.add(swim.getDistance());
            swimTime.add(swim.getTime());
        });

        if (swims.size() > 5){ swims.subList(5, swims.size()).clear();}

        model.addAttribute("runs", runs);
        model.addAttribute("runDates", runDatesData);
        model.addAttribute("runDistances", runDistances);
        model.addAttribute("runTime", runTime);

        model.addAttribute("cyclings", cyclings);
        model.addAttribute("cyclingDates", cyclingDatesData);
        model.addAttribute("cyclingDistances", cyclingDistances);
        model.addAttribute("cyclingTime", cyclingTime);

        model.addAttribute("swims", swims);
        model.addAttribute("swimDates", swimDatesData);
        model.addAttribute("swimDistances", swimDistances);
        model.addAttribute("swimTime", swimTime);

        return "stats/overall";
    }

    @GetMapping("/stats/swimming")
    public String statsSwimming(Model model) {

        List<Swim> swims = swimServ.getSwimsByUser();
        List<String> swimDatesData = new ArrayList<>();
        List<Integer> swimDistances = new ArrayList<>();
        List<Integer> swimTime = new ArrayList<>();
        swims.forEach(swim -> {
            swimDatesData.add(swim.getSwimDate().toString());
            swimDistances.add(swim.getDistance());
            swimTime.add(swim.getTime());
        });

        model.addAttribute("swims", swims);
        model.addAttribute("swimDates", swimDatesData);
        model.addAttribute("swimDistances", swimDistances);
        model.addAttribute("swimTime", swimTime);

        return "stats/swimming";
    }

    @Autowired
    private UserService userService;

    @GetMapping("/stats/running")
    public String viewRun(Model model) {
        List<Run> runs = runServ.getRunsByUser(userService.getCurrentUser());
        List<String> datesdata = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        List<Integer> time = new ArrayList<>();
      runs.forEach(run -> {
            datesdata.add(run.getRunDate().toString());
            distances.add(run.getDistance());
            time.add(run.getTime());
        });


        model.addAttribute("runs", runServ.getRunsByUser(userService.getCurrentUser()));
        model.addAttribute("dates", datesdata);
        model.addAttribute("distances", distances);
        model.addAttribute("time", time);
        return "stats/running";

    }


    @GetMapping("/stats/cycling")
    public String viewCycling(Model model) {
        List<Cycling> cyclings = cyclingServ.getCyclingByUser();
        List<String> datesdata = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        List<Integer> time = new ArrayList<>();
        cyclings.forEach(cycling-> {
            datesdata.add(cycling.getCyclingDate().toString());
            distances.add(cycling.getDistance());
            time.add(cycling.getTime());
        });

        model.addAttribute("cyclings", cyclingServ.getCyclingByUser());
        model.addAttribute("dates", datesdata);
        model.addAttribute("distances", distances);
        model.addAttribute("time", time);
        return "stats/cycling";
    }
}
