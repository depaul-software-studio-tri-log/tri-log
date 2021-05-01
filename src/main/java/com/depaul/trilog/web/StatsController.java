package com.depaul.trilog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatsController {

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
    public String statsCycling() {
        return "stats/cycling";
    }
}
