package com.depaul.trilog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlanController {

    @GetMapping("/plan")
    public String plan() {
        return "plan";
    }
}
