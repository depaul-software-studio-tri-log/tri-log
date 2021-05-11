package com.depaul.trilog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogActivityController {

   // @GetMapping("/log")
    public String log() {
        return "log-activity";
    }
}
