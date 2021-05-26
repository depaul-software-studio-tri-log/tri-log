package com.depaul.trilog.web;

import com.depaul.trilog.configuration.TestUserConfiguration;
import com.depaul.trilog.entities.Plan;
import com.depaul.trilog.services.PlanService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
class PlanControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanService planService;

    @Test
    @WithUserDetails("steve")
    void testShowPlanOverview() {
        try {
            mockMvc.perform(get("/plan")).andExpect(status().isOk())
                    .andExpect(model().attributeExists("plans"))
                    .andExpect(view().name("plans/plan-overview"));
        }
        catch (Exception e) {
            fail("Get request to /plan failed: " + e.getCause());
        }
    }

    @Test
    @WithUserDetails("steve")
    void testShowNewPlanEdit() throws ParseException {
        String date = "2021-05-20";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dateObject = dateFormat.parse(date);

        Mockito.when(planService.findByDate(dateObject)).thenReturn(null);

        try {
            mockMvc.perform(get("/plan/{date}", date)).andExpect(status().isOk())
                    .andExpect(model().attributeExists("plan"))
                    .andExpect(model().attribute("plan", hasProperty("date", is(dateObject))))
                    .andExpect(view().name("plans/plan-edit"));
        }
        catch (Exception e) {
            fail("Get request to /plan/" + date + " failed: " + e.getCause());
        }
    }

    @Test
    @WithUserDetails("steve")
    void testShowExistingPlanEdit() throws ParseException {
        String date = "2021-12-01";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dateObject = dateFormat.parse(date);

        Plan plan = new Plan();
        plan.setDate(dateObject);
        Mockito.when(planService.findByDate(dateObject)).thenReturn(plan);

        try {
            mockMvc.perform(get("/plan/{date}", date)).andExpect(status().isOk())
                    .andExpect(model().attributeExists("plan"))
                    .andExpect(model().attribute("plan", hasProperty("date", is(dateObject))))
                    .andExpect(view().name("plans/plan-edit"));
        }
        catch (Exception e) {
            fail("Get request to /plan/" + date + " failed: " + e.getCause());
        }
    }

    @Test
    @WithUserDetails("steve")
    void testSavePlanSuccess() throws ParseException {
        String date = "2021-05-24";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dateObject = dateFormat.parse(date);

        Plan plan = new Plan();
        plan.setDate(dateObject);

        try {
            mockMvc.perform(post("/plan/add").flashAttr("plan", plan))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/plan/" + date));
        }
        catch (Exception e) {
            fail("Post request to /plan/add failed: " + e.getCause());
        }

        verify(planService).savePlan(any());
    }
}