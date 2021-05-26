package com.depaul.trilog.web;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.depaul.trilog.configuration.TestUserConfiguration;
import com.depaul.trilog.dao.RaceRepository;
import com.depaul.trilog.dao.SportTypeRepository;
import com.depaul.trilog.dao.UserRepository;
import com.depaul.trilog.entities.Race;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Import(TestUserConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RaceControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private RaceRepository raceRepository;

    @Test
    @WithUserDetails("steve")
    public void testShowRacesView() {

        User user = new User();
        user.setUsername("steve");
        user.setFirstname("Steve");
        user.setLastname("Smith");
        user.setId(1);

        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        Mockito.when(raceRepository.getAllFutureRacesForUser(anyInt())).thenReturn(new LinkedList<Race>());
        try {
            mockMvc.perform(get("/races")).andExpect(status().isOk())
                    .andExpect(model().attributeExists("races"))
                    .andExpect(view().name("races/races"));
        }
        catch (Exception e) {
            fail("Get request to /races failed: " + e.getCause());
        }
    }

    @Test
    @WithUserDetails("steve")
    public void testShowAddRacesView() {

        try {
            mockMvc.perform(get("/add-race")).andExpect(status().isOk())
                    .andExpect(view().name("races/add_race"));
        }
        catch (Exception e) {
            fail("Get request to /add-race failed: " + e.getCause());
        }
    }

    @Test
    @WithUserDetails("steve")
    public void testCreateRace() {

        User user = new User();
        user.setUsername("steve");
        user.setFirstname("Steve");
        user.setLastname("Smith");
        user.setId(1);

        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        Race race = new Race();

        try {
            mockMvc.perform(post("/create-race").flashAttr("race", race))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/races"));
            verify(raceRepository).save(any());
        }
        catch (Exception e) {
            fail("Get request to /create-race failed: " + e.getCause());
        }
    }

    @Test
    @WithUserDetails("steve")
    public void testDeleteRace() {

        User user = new User();
        user.setUsername("steve");
        user.setFirstname("Steve");
        user.setLastname("Smith");
        user.setId(1);

        Mockito.when(userService.getCurrentUser()).thenReturn(user);

        Race race = new Race();
        race.setId(1);
        race.setUserid(1);
        Optional<Race> raceOptional = Optional.of(race);

        Mockito.when(raceRepository.findById(anyInt())).thenReturn(raceOptional);

        try {
            mockMvc.perform(get("/delete-race/1"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/races"));
            verify(raceRepository).delete(any());
        }
        catch (Exception e) {
            fail("Get request to /delete-race failed: " + e.getCause());
        }
    }
}

