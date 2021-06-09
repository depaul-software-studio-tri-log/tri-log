package com.depaul.trilog.web;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.depaul.trilog.configuration.TestUserConfiguration;
import com.depaul.trilog.dao.SportTypeRepository;
import com.depaul.trilog.dao.UserRepository;
import com.depaul.trilog.entities.Cycling;
import com.depaul.trilog.entities.Plan;
import com.depaul.trilog.entities.User;
import com.depaul.trilog.services.CyclingService;
import com.depaul.trilog.services.PlanService;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Import(TestUserConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CycleControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CyclingService cycleService;

    @Test
    @WithUserDetails("steve")
    void testShowInputCycling() {
        try {
            mockMvc.perform(get("/trilog/cycling?addCycling")).andExpect(status().isOk())
                    .andExpect(model().attributeExists("newCycling"))
                    .andExpect(view().name("cyclings/input_cycling"));
        }
        catch (Exception e) {
            fail("Get request to /trilog/cycling failed: " + e.getCause());
        }
    }

    @Test
    @WithUserDetails("steve")
    void testSaveCycleSuccess() throws ParseException {
        String date = "2021-05-24";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dateObject = dateFormat.parse(date);

        java.sql.Date sqlDate = new java.sql.Date(dateObject.getTime());

        Cycling cycle = new Cycling();
        cycle.setCyclingDate(sqlDate);
        cycle.setId(1);
        cycle.setDistance(10);
        cycle.setTime(11);

        try {
            mockMvc.perform(post("/trilog/cycling")
                    .flashAttr("newCycling", cycle))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/stats/cycling"));;
        }
        catch (Exception e) {
            fail("Post request to /cycling/addCycling failed: " + e.getCause());
        }

        verify(cycleService).addCycling(any());
    }
}
