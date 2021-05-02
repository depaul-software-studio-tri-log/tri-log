package com.depaul.trilog.web;

import com.depaul.trilog.dao.UserRepository;
import com.depaul.trilog.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/")
    public String home(Model model) {
        // demo for getting the logged in user info
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Optional<User> user = userRepo.findByUsername(userName);
        if (!user.isPresent()) {
            // figure out what to do here
        }
        else {
            User currentUser = user.get();
            model.addAttribute("user", currentUser);
        }
        return "home";
    }
}
