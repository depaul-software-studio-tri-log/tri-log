package com.depaul.trilog.web;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.depaul.trilog.configuration.TestUserConfiguration;

import com.depaul.trilog.dao.CyclingRepository;
import com.depaul.trilog.dao.RunRepository;
import com.depaul.trilog.dao.SwimRepository;
import com.depaul.trilog.entities.Cycling;
import com.depaul.trilog.entities.Run;
import com.depaul.trilog.entities.Swim;
import com.depaul.trilog.entities.User;
import com.depaul.trilog.services.CyclingService;
import com.depaul.trilog.services.RunService;
import com.depaul.trilog.services.UserService;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Import(TestUserConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatsControllerTests  {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CyclingRepository cycleRepo;

    @MockBean
    private SwimRepository swimRepo;

    @Autowired
    private CyclingService cyclingServ;

    @MockBean
    private RunRepository runRepo;

    @Autowired
    private RunService runServ;

    @MockBean
    private UserService userService;

    void setup() throws Exception{
        String date = "2021-05-24";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dateObject = dateFormat.parse(date);

        java.sql.Date sqlDate = new java.sql.Date(dateObject.getTime());

        User user = new User();
        user.setUsername("steve");
        user.setFirstname("Steve");
        user.setLastname("Smith");
        user.setId(1);

        List<Cycling> cycleList = new ArrayList<>();
        Cycling cycle = new Cycling();
        cycle.setTime(10);
        cycle.setDistance(10);
        cycle.setUser(user);
        cycle.setCyclingDate(sqlDate);
        cycleList.add(cycle);

        List<Run> runList = new ArrayList<>();
        Run run = new Run();
        run.setUser(user);
        run.setId(1);
        run.setTime(10);
        run.setDistance(10);
        run.setRunDate(sqlDate);
        runList.add(run);

        List<Swim> swimList = new ArrayList<>();
        Swim swim = new Swim();
        swim.setId(1L);
        swim.setTime(10);
        swim.setDistance(10);
        swim.setSwimDate(sqlDate);
        swimList.add(swim);

        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        Mockito.when(cycleRepo.findAllByUserOrderByCyclingDateDesc(user)).thenReturn(cycleList);
        Mockito.when(swimRepo.findAllByUserOrderBySwimDateDesc(user)).thenReturn(swimList);
        Mockito.when(runRepo.findAllByUserOrderByRunDateDesc(user)).thenReturn(runList);
    }

    @Test
    @WithUserDetails("steve")
    void testShowOverAll() throws Exception {
        setup();
        mockMvc.perform(get("/stats/overall"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("runs"))
                .andExpect(model().attributeExists("runDates"))
                .andExpect(model().attributeExists("runDistances"))
                .andExpect(model().attributeExists("runTime"))
                .andExpect(model().attributeExists("cyclings"))
                .andExpect(model().attributeExists("cyclingDates"))
                .andExpect(model().attributeExists("cyclingDistances"))
                .andExpect(model().attributeExists("cyclingTime"))
                .andExpect(model().attributeExists("swims"))
                .andExpect(model().attributeExists("swimDates"))
                .andExpect(model().attributeExists("swimDistances"))
                .andExpect(model().attributeExists("swimTime"));
    }


    @Test
    @WithUserDetails("steve")
    void testShowRunning() throws Exception {
        setup();
        mockMvc.perform(get("/stats/running"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("runs"))
                .andExpect(model().attributeExists("dates"))
                .andExpect(model().attributeExists("distances"))
                .andExpect(model().attributeExists("time"));

    }

    @Test
    @WithUserDetails("steve")
    void testShowCycling() throws Exception {
        setup();
        mockMvc.perform(get("/stats/cycling"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cyclings"))
                .andExpect(model().attributeExists("dates"))
                .andExpect(model().attributeExists("distances"))
                .andExpect(model().attributeExists("time"));
    }

    @Test
    @WithUserDetails("steve")
    void testShowSwimming() throws Exception {
        setup();
        mockMvc.perform(get("/stats/swimming"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("swims"))
                .andExpect(model().attributeExists("swimDates"))
                .andExpect(model().attributeExists("swimDistances"))
                .andExpect(model().attributeExists("swimTime"));;
    }
}
