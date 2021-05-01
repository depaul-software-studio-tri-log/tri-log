package com.depaul.trilog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoalsController {

    @GetMapping("/goals")
    public String goals() {
        return "goals";
    }
}
