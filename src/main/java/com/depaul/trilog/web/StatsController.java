package com.depaul.trilog.web;

import com.depaul.trilog.dao.CyclingRepository;

import com.depaul.trilog.dao.RunRepository;

import com.depaul.trilog.entities.Cycling;
import com.depaul.trilog.entities.Goals;
import com.depaul.trilog.entities.Run;
import com.depaul.trilog.entities.User;
import com.depaul.trilog.services.CyclingService;
import com.depaul.trilog.services.RunService;
import com.depaul.trilog.services.UserService;
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
    private RunService runServ;


    public StatsController() {
    }

    @GetMapping("/stats/overall")
    public String statsOverall() {
        return "stats/overall";
    }

    @GetMapping("/stats/swimming")
    public String statsSwimming() {
        return "stats/swimming";
    }

    @Autowired
    private UserService userService;
    @GetMapping("/stats/running")

    public String viewRunning(Model model) {
        List<Run> runs = runServ.getRunsByUser(userService.getCurrentUser());
        List<String> datesdata = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        List<Integer> time = new ArrayList<>();
        runs.forEach(run -> {
            datesdata.add(run.getRunDate().toString());
            distances.add(run.getDistance());
            time.add(run.getTime());
        });
        model.addAttribute("runnings", runServ.getRunsByUser(userService.getCurrentUser()));
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
