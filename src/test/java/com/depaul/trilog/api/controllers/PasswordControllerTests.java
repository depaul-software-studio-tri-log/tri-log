package com.depaul.trilog.api.controllers;

import com.depaul.trilog.authentication.PasswordChange;
import com.depaul.trilog.configuration.TestUserConfiguration;
import com.depaul.trilog.dao.SportTypeRepository;
import com.depaul.trilog.dao.UserRepository;
import com.depaul.trilog.entities.User;
import com.depaul.trilog.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Import(TestUserConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PasswordControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private SportTypeRepository sportTypeRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    @WithUserDetails("steve")
    public void changePasswordSucceedsWithCorrectPassword() {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String correctPassword = "password";

        User savedUser = new User();
        savedUser.setUsername("steve");
        savedUser.setFirstname("Steve");
        savedUser.setLastname("Smith");
        savedUser.setPassword(encoder.encode(correctPassword));

        Mockito.when(userService.getCurrentUser()).thenReturn(savedUser);

        PasswordChange passwords = new PasswordChange();
        passwords.oldPassword = correctPassword;
        passwords.newPassword = "password123";

        try {
            ObjectMapper mapper = new ObjectMapper();
            ResultActions result = mockMvc.perform(post("/api/change-password")
                    .content(mapper.writeValueAsString(passwords))
                    .contentType("application/json"));
            result.andExpect(status().isOk());
            assertEquals("true", result.andReturn().getResponse().getContentAsString());
            verify(userRepository).save(any());
        }
        catch(Exception e) {
            fail("Post to /api/change-password failed: " + e.getCause());
        }
    }

    @Test
    @WithUserDetails("steve")
    public void changePasswordFailsWithIncorrectPassword() {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String correctPassword = "password";
        String incorrectPassword = "passwordzzz";

        User savedUser = new User();
        savedUser.setUsername("steve");
        savedUser.setFirstname("Steve");
        savedUser.setLastname("Smith");
        savedUser.setPassword(encoder.encode(correctPassword));

        Mockito.when(userService.getCurrentUser()).thenReturn(savedUser);

        PasswordChange passwords = new PasswordChange();
        passwords.oldPassword = incorrectPassword;
        passwords.newPassword = "password123";

        try {
            ObjectMapper mapper = new ObjectMapper();
            ResultActions result = mockMvc.perform(post("/api/change-password")
                    .content(mapper.writeValueAsString(passwords))
                    .contentType("application/json"));
            result.andExpect(status().isOk());
            assertEquals("false", result.andReturn().getResponse().getContentAsString());
            verify(userRepository, never()).save(any());
        }
        catch(Exception e) {
            fail("Post to /api/change-password failed: " + e.getCause());
        }
    }

}
