package com.depaul.trilog.services;

import com.depaul.trilog.dao.UserRepository;
import com.depaul.trilog.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private Environment env;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Optional<User> user = userRepo.findByUsername(userName);
        return user.get();
    }

    public void sendPasswordResetEmailIfUserExists(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            String token = UUID.randomUUID().toString();
            User targetUser = user.get();
            targetUser.setPasswordresettoken(token);
            Date expirationDate = getTokenExpirationDate();
            targetUser.setPasswordresetexpires(expirationDate);
            userRepo.save(targetUser);
            sendPasswordResetEmail(user);
        }
        else {
            logger.info("No user found for email " + email);
        }
    }

    private void sendPasswordResetEmail(User user) {

        logger.info("Sent password reset email to user " + user.getId());
    }

    private Date getTokenExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(env.getProperty("passwordExpirationLifetimeInHours")));
        return calendar.getTime();
    }
}
