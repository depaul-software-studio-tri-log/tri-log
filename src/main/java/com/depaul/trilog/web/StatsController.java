package com.depaul.trilog.web;

import com.depaul.trilog.dao.CyclingRepository;
import com.depaul.trilog.dao.RunRepository;
import com.depaul.trilog.entities.Cycling;
import com.depaul.trilog.entities.Goals;
import com.depaul.trilog.services.CyclingService;
import com.depaul.trilog.services.RunService;
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

    @GetMapping("/stats/overall")
    public String statsOverall() {
        return "stats/overall";
    }

    @GetMapping("/stats/swimming")
    public String statsSwimming() {
        return "stats/swimming";
    }

    @GetMapping("/stats/running")
    public String statsRunning() {
        return "stats/running";
    }

    @GetMapping("/stats/cycling")
    public String viewCycling(Model model) {
        List<Cycling> cyclings = cyclingServ.getCyclingByUser();
        List<String> datesdata = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        cyclings.forEach(cycling-> {
            datesdata.add(cycling.getCyclingDate().toString());
            distances.add(cycling.getDistance());
        });

        model.addAttribute("cyclings", cyclingServ.getCyclingByUser());
        model.addAttribute("dates", datesdata);
        model.addAttribute("distances", distances);
        return "stats/cycling";
    }
}
