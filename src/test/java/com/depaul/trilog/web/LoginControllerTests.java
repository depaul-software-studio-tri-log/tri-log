package com.depaul.trilog.web;

import com.depaul.trilog.dao.UserRepository;
import com.depaul.trilog.entities.User;
import com.depaul.trilog.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepo;

    @Test
    public void testGetForgotPasswordPage() {
        try {
            mockMvc.perform(get("/forgot-password"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("forgot-password"));
        }
        catch (Exception e) {
            fail("Get request to /forgot-password failed: " + e.getCause());
        }
    }

    @Test
    public void testGetForgotPasswordPageWithConfirmation() {
        try {
            mockMvc.perform(get("/forgot-password?confirmation=true"))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("confirmationMessage"))
                    .andExpect(view().name("forgot-password"));
        }
        catch (Exception e) {
            fail("Get request to /forgot-password failed: " + e.getCause());
        }
    }

    @Test
    public void testRequestPasswordReset() {
        try {
            mockMvc.perform(post("/request-password-reset").flashAttr("email", "test@none.local"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/forgot-password?confirmation=true"));
            verify(userService).sendPasswordResetEmailIfUserExists(any());
        }
        catch (Exception e) {
            fail("Get request to /request-password-reset failed: " + e.getCause());
        }
    }

    @Test
    public void testChangeForgottenPasswordWithValidToken() {

        String token = UUID.randomUUID().toString();

        User savedUser = new User();
        savedUser.setUsername("steve");
        savedUser.setFirstname("Steve");
        savedUser.setLastname("Smith");
        savedUser.setPasswordresettoken(token);
        savedUser.setPasswordresetexpires(getTokenExpirationDate());

        Mockito.when(userService.getCurrentUser()).thenReturn(savedUser);
        Mockito.when(userRepo.findByPasswordresettoken(any())).thenReturn(Optional.of(savedUser));

        try {
            mockMvc.perform(get("/change-forgotten-password?token=" + token))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/reset-password?token=" + token));
        }
        catch (Exception e) {
            fail("Post request to /change-forgotten-password failed: " + e.getCause());
        }
    }

    @Test
    public void testChangeForgottenPasswordWithInvalidToken() {

        String token = UUID.randomUUID().toString();

        User savedUser = new User();
        savedUser.setUsername("steve");
        savedUser.setFirstname("Steve");
        savedUser.setLastname("Smith");
        savedUser.setPasswordresettoken(token);
        savedUser.setPasswordresetexpires(getTokenExpirationDate());

        Mockito.when(userService.getCurrentUser()).thenReturn(savedUser);
        Mockito.when(userRepo.findByPasswordresettoken(any())).thenReturn(Optional.empty());

        try {
            mockMvc.perform(get("/change-forgotten-password?token=123456"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/invalid-reset-token"));
        }
        catch (Exception e) {
            fail("Post request to /change-forgotten-password failed: " + e.getCause());
        }
    }

    @Test
    public void testGetResetPasswordPageWithValidToken() {
        String token = UUID.randomUUID().toString();

        User savedUser = new User();
        savedUser.setUsername("steve");
        savedUser.setFirstname("Steve");
        savedUser.setLastname("Smith");
        savedUser.setPasswordresettoken(token);
        savedUser.setPasswordresetexpires(getTokenExpirationDate());

        Mockito.when(userService.getCurrentUser()).thenReturn(savedUser);
        Mockito.when(userRepo.findByPasswordresettoken(any())).thenReturn(Optional.of(savedUser));

        try {
            mockMvc.perform(get("/reset-password?token=" + token))
                    .andExpect(status().isOk())
                    .andExpect(view().name("reset-password"));
        }
        catch (Exception e) {
            fail("Post request to /reset-password failed: " + e.getCause());
        }
    }

    @Test
    public void testGetResetPasswordPageWithInvalidToken() {
        String token = UUID.randomUUID().toString();

        User savedUser = new User();
        savedUser.setUsername("steve");
        savedUser.setFirstname("Steve");
        savedUser.setLastname("Smith");
        savedUser.setPasswordresettoken(token);
        savedUser.setPasswordresetexpires(getTokenExpirationDate());

        Mockito.when(userService.getCurrentUser()).thenReturn(savedUser);
        Mockito.when(userRepo.findByPasswordresettoken(any())).thenReturn(Optional.empty());

        try {
            mockMvc.perform(get("/reset-password?token=" + token))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/invalid-reset-token"));
        }
        catch (Exception e) {
            fail("Post request to /reset-password failed: " + e.getCause());
        }
    }

    @Test
    public void testPostResetPasswordPageWithValidToken() {
        String token = UUID.randomUUID().toString();

        User savedUser = new User();
        savedUser.setUsername("steve");
        savedUser.setFirstname("Steve");
        savedUser.setLastname("Smith");
        savedUser.setPasswordresettoken(token);
        savedUser.setPasswordresetexpires(getTokenExpirationDate());

        Mockito.when(userService.getCurrentUser()).thenReturn(savedUser);
        Mockito.when(userRepo.findByPasswordresettoken(any())).thenReturn(Optional.of(savedUser));

        try {
            Map<String, Object> attributes = new HashMap<String, Object>();
            attributes.put("token", token);
            attributes.put("newPassword", "test");
            mockMvc.perform(post("/reset-password").param("token", token).param("newPassword", "test"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/"));
            assertNotNull(savedUser.getPassword());
            assertEquals("", savedUser.getPasswordresettoken());
        }
        catch (Exception e) {
            fail("Post request to /reset-password failed: " + e.getCause());
        }
    }

    @Test
    public void testPostResetPasswordPageWithExpiredToken() {
        String token = UUID.randomUUID().toString();

        String originalPassword = "password123";
        User savedUser = new User();
        savedUser.setUsername("steve");
        savedUser.setFirstname("Steve");
        savedUser.setLastname("Smith");
        savedUser.setPassword(originalPassword);
        savedUser.setPasswordresettoken(token);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.HOUR_OF_DAY, -4);
        savedUser.setPasswordresetexpires(calendar.getTime());

        Mockito.when(userService.getCurrentUser()).thenReturn(savedUser);
        Mockito.when(userRepo.findByPasswordresettoken(any())).thenReturn(Optional.of(savedUser));

        try {
            Map<String, Object> attributes = new HashMap<String, Object>();
            attributes.put("token", token);
            attributes.put("newPassword", "test");
            mockMvc.perform(post("/reset-password").param("token", token).param("newPassword", "test"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/invalid-reset-token"));
            assertEquals(originalPassword, savedUser.getPassword());
            assertNotEquals("", savedUser.getPasswordresettoken());
        }
        catch (Exception e) {
            fail("Post request to /reset-password failed: " + e.getCause());
        }
    }

    private Date getTokenExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.HOUR_OF_DAY, 4);
        return calendar.getTime();
    }
}
