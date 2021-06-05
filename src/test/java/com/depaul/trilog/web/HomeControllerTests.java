package com.depaul.trilog.web;

import com.depaul.trilog.configuration.TestUserConfiguration;
import com.depaul.trilog.dao.RaceRepository;
import com.depaul.trilog.entities.Race;
import com.depaul.trilog.entities.User;
import com.depaul.trilog.services.PlanService;
import com.depaul.trilog.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(TestUserConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanService planService;

    @MockBean
    private UserService userService;

    @MockBean
    private RaceRepository raceRepository;

    @Test
    @WithUserDetails("steve")
    void testShowHomePage() {
        User user = new User();
        user.setId(1);
        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        Race race = new Race();
        race.setUserid(1);
        Mockito.when(raceRepository.getNextRaceForUser(1)).thenReturn(race);

        try {
            mockMvc.perform(get("/")).andExpect(status().isOk())
                    .andExpect(model().attributeExists("user"))
                    .andExpect(model().attribute("nextRace", hasProperty("userid", is(race.getUserid()))))
                    .andExpect(model().attributeExists("swimDistanceProgress"))
                    .andExpect(model().attributeExists("swimTimeProgress"))
                    .andExpect(model().attributeExists("cycleDistanceProgress"))
                    .andExpect(model().attributeExists("cycleTimeProgress"))
                    .andExpect(model().attributeExists("runDistanceProgress"))
                    .andExpect(model().attributeExists("runTimeProgress"))
                    .andExpect(view().name("home"));
        }
        catch (Exception e) {
            fail("Get request to / failed: " + e.getCause());
        }

        verify(planService, times(2)).findByDate(any());
    }
}
