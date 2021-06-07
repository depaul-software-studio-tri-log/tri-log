package com.depaul.trilog.web;

import com.depaul.trilog.configuration.TestUserConfiguration;
import com.depaul.trilog.dao.GoalsRepository;
import com.depaul.trilog.entities.Goals;
import com.depaul.trilog.services.GoalsService;
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
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(TestUserConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
class GoalsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoalsService goalsService;

    @MockBean
    private GoalsRepository goalsRepository;

    @Test
    @WithUserDetails("steve")
    void testShowGoalsPage() {
        try {
            mockMvc.perform(get("/goals")).andExpect(status().isOk())
                    .andExpect(model().attributeExists("goals"))
                    .andExpect(model().attributeExists("allGoals"))
                    .andExpect(view().name("goals/goals"));
        }
        catch (Exception e) {
            fail("Get request to /goals failed: " + e.getCause());
        }
    }

    @Test
    @WithUserDetails("steve")
    void testShowEditGoalsPage() {
        int id = 5;
        Goals goal = new Goals();
        goal.setId(id);
        goal.setUserid(10);
        Mockito.when(goalsRepository.findFirstById(id)).thenReturn(goal);

        try {
            mockMvc.perform(get("/goals/{id}", id)).andExpect(status().isOk())
                    .andExpect(model().attribute("goals", hasProperty("id", is(id))))
                    .andExpect(model().attribute("goals", hasProperty("userid", is(goal.getUserid()))))
                    .andExpect(view().name("goals/goals-edit"));
        }
        catch (Exception e) {
            fail("Get request to /goals/" + id +  " failed: " + e.getCause());
        }
    }

    @Test
    @WithUserDetails("steve")
    void testSaveGoalSuccess() {
        Goals goal = new Goals();
        goal.setId(1);
        goal.setUserid(5);
        goal.setDistance(50);
        goal.setDistanceprogress(25);
        goal.setMinutes(100);
        goal.setMinutesprogress(50);
        goal.setActivity(3);
        goal.setGoalname("New Goal");
        goal.setNote("Goal Notes");

        try {
            mockMvc.perform(post("/addGoal").flashAttr("goals", goal))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/goals"));

            verify(goalsService).addNewGoal(any());
        }
        catch (Exception e) {
            fail("Post request to /addGoal failed: " + e.getCause());
        }
    }
}
