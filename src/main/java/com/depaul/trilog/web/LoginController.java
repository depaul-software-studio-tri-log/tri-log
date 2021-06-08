package com.depaul.trilog.web;

import com.depaul.trilog.dao.UserRepository;
import com.depaul.trilog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.depaul.trilog.entities.User;

import java.util.Date;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

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

    @GetMapping("/forgot-password")
    public String forgotPassword(@RequestParam(value = "confirmation", required = false) String confirmation,
                                 Model model) {
        String confirmationMessage = null;
        if(confirmation != null && confirmation.equals("true")) {
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

    @GetMapping("/change-forgotten-password")
    public String changeForgottenPassword(@RequestParam(value = "token", required = true) String token) {
        Optional<User> user = userRepo.findByPasswordresettoken(token);
        if (!user.isPresent()) {
            return "redirect:/invalid-reset-token";
        }
        User targetUser = user.get();
        if (targetUser.getPasswordresetexpires().before(new Date())) {
            return "redirect:/invalid-reset-token";
        }
        return "redirect:/reset-password?token=" + token;
    }

    @GetMapping("/reset-password")
    public String getResetPasswordPage(@RequestParam(value = "token", required = true) String token,
                                Model model) {
        if (!validateToken(token)) {
            return "redirect:/invalid-reset-token";
        }
        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam(value = "token", required = true) String token,
                                @RequestParam(value = "newPassword", required = true) String newPassword) {
        if (!validateToken(token)) {
            return "redirect:/invalid-reset-token";
        }
        User user = userRepo.findByPasswordresettoken(token).get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        user.setPassword(encoder.encode(newPassword));
        user.setPasswordresettoken("");
        userRepo.save(user);
        return "redirect:/";
    }

    @GetMapping("/invalid-reset-token")
    public String invalidResetToken() {
        return "invalid-reset-token";
    }

    private boolean validateToken(String token) {
        if (token.isEmpty()) {
            return false;
        }
        Optional<User> user = userRepo.findByPasswordresettoken(token);
        if (!user.isPresent()) {
            return false;
        }
        User targetUser = user.get();
        if (targetUser.getPasswordresetexpires().before(new Date())) {
            return false;
        }
        return true;
    }
}
