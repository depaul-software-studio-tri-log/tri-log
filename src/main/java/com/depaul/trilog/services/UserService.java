package com.depaul.trilog.services;

import com.depaul.trilog.dao.UserRepository;
import com.depaul.trilog.entities.User;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;

import java.io.File;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.sendgrid.*;
import java.io.IOException;

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
            sendPasswordResetEmail(targetUser);
        }
        else {
            logger.info("No user found for email " + email);
        }
    }

    private void sendPasswordResetEmail(User user) {

        Email from = new Email(env.getProperty("sendGridFromAddress"));
        String subject = "Password Reset Link for Trilog";
        Email to = new Email(user.getEmail());
        String body;
        try {
            File resource = new ClassPathResource("static/email/password-reset.html").getFile();
            body = new String(Files.readAllBytes(resource.toPath()))
                        .replace("{password-reset-url}", env.getProperty("password-reset-url"))
                        .replace("{password-reset-token}", user.getPasswordresettoken());
        }
        catch (Exception ex) {
            logger.info("Exception sending password reset email to user " + user.getId() + ": " + ex.getStackTrace());
            return;
        }
        Content content = new Content("text/html", body);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("sendgridAPIKey"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        } catch (IOException ex) {
            logger.info("Exception sending password reset email to user " + user.getId() + ": " + ex.getStackTrace());
            return;
        }

        logger.info("Sent password reset email to user " + user.getId());
    }

    private Date getTokenExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(env.getProperty("passwordExpirationLifetimeInHours")));
        return calendar.getTime();
    }
}
