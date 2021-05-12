package com.depaul.trilog.web;

import com.depaul.trilog.dao.SportTypeRepository;
import com.depaul.trilog.dao.UserRepository;
import com.depaul.trilog.entities.User;
import com.depaul.trilog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    @Autowired
    private SportTypeRepository sportTypeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public String profile(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        model.addAttribute("sportTypes", sportTypeRepository.findAllByOrderById());
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(User user, BindingResult bindingResults, Model model) {
        User currentUser = userService.getCurrentUser();
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        user.setPassword(currentUser.getPassword());
        userRepository.save(user);
        return "redirect:/profile";
    }
}
