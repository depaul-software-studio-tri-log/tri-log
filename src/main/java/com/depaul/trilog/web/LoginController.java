package com.depaul.trilog.web;

import com.depaul.trilog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        Model model) {
        String errorMessage = null;
        if(error != null) {
            errorMessage = "Incorrect user name or password";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    @GetMapping("/create")
    public String createAccount() {
        return "create-account";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(@RequestParam(value = "confirmation", required = false) String error,
                                 Model model) {
        String confirmationMessage = null;
        if(error != null) {
            confirmationMessage = "Submission successful";
        }
        model.addAttribute("confirmationMessage", confirmationMessage);
        return "forgot-password";
    }

    @PostMapping("/request-password-reset")
    public String requestPasswordReset(String email) {
        userService.sendPasswordResetEmailIfUserExists(email);
        return "redirect:/forgot-password?confirmation=true";
    }
}
