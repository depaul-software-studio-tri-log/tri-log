package com.depaul.trilog.web;

import com.depaul.trilog.dao.CyclingRepository;
import com.depaul.trilog.entities.Cycling;
import com.depaul.trilog.services.CyclingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public class CyclingController {

    @Autowired
    private CyclingRepository cyclingRepo;

    @Autowired
    private CyclingService cyclingServ;

    @RequestMapping
    public String viewCyclings(Model model) {
        model.addAttribute("cycling",cyclingServ.getAllRuns());
        return "/trilog/cycling";
    }

    @RequestMapping(params = "addCycling")
    public String addCycling(Model model) {
        model.addAttribute("newCycling", new Cycling());
        return "runs/input_cycling";
    }


    @PostMapping
    public String createCycling(@ModelAttribute("newCycling") Cycling cycling, BindingResult binding) {
        if(binding.hasErrors()) {
            return "runs/input_cycling";
        }
        cyclingRepo.save(cycling);
        return "redirect:/trilog/cycling";
    }
}
