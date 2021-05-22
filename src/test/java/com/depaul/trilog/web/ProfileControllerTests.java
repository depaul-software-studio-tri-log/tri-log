package com.depaul.trilog.web;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.depaul.trilog.configuration.TestUserConfiguration;
import com.depaul.trilog.dao.SportTypeRepository;
import com.depaul.trilog.dao.UserRepository;
import com.depaul.trilog.entities.User;
import com.depaul.trilog.services.UserService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.Mockito;

@Import(TestUserConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerTests {

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
    public void updateProfileSavesInformation() {

        User paramUser = new User();
        paramUser.setUsername("steve");
        paramUser.setFirstname("Steven");
        paramUser.setLastname("Smythe");

        User savedUser = new User();
        savedUser.setUsername("steve");
        savedUser.setFirstname("Steve");
        savedUser.setLastname("Smith");

        Mockito.when(userService.getCurrentUser()).thenReturn(savedUser);

        try {
            mockMvc.perform(post("/profile/update").flashAttr("user", paramUser));
        }
        catch(Exception e) {
            fail("Post to /profile/update failed: " + e.getCause());
        }
        verify(userRepository).save(any());
    }
}
