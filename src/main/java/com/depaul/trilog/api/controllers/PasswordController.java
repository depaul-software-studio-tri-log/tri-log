package com.depaul.trilog.api.controllers;

import com.depaul.trilog.authentication.PasswordChange;
import com.depaul.trilog.dao.UserRepository;
import com.depaul.trilog.entities.User;
import com.depaul.trilog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PasswordController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping("/change-password")
    public boolean changePassword(@RequestBody PasswordChange passwords) {

        User currentUser = userService.getCurrentUser();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        if (!encoder.matches(passwords.oldPassword, currentUser.getPassword())) {
            return false;
        }
        currentUser.setPassword(encoder.encode(passwords.newPassword));
        userRepository.save(currentUser);
        return true;

    }

}
