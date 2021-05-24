package com.depaul.trilog.web;

import com.depaul.trilog.dao.RaceRepository;
import com.depaul.trilog.dao.UserRepository;
import com.depaul.trilog.entities.Race;
import com.depaul.trilog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RaceController {

    @Autowired
    RaceRepository raceRepository;

    @Autowired
    UserService userService;

    @GetMapping("/races")
    public String races(Model model) {
        model.addAttribute("races", raceRepository.getAllFutureRacesForUser(userService.getCurrentUser().getId()));
        return "races/races";
    }

    @GetMapping("/add-race")
    public String addRace() {
        return "races/add_race";
    }

    @PostMapping("/create-race")
    public String createRace(Race race, BindingResult bindingResult, Model model) {
        race.setUserid(userService.getCurrentUser().getId());
        raceRepository.save(race);
        return "redirect:/races";
    }

}
