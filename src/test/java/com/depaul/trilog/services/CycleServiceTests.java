package com.depaul.trilog.services;

import com.depaul.trilog.entities.Cycling;
import com.depaul.trilog.entities.User;
import org.mockito.Mockito;

import com.depaul.trilog.dao.CyclingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.depaul.trilog.configuration.TestUserConfiguration;
import com.depaul.trilog.entities.Plan;
import com.depaul.trilog.services.PlanService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(TestUserConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CycleServiceTests {

    @Autowired
    private CyclingService cycleServices;

    @MockBean
    private CyclingRepository cycleRepo;

    @MockBean
    private UserService userService;


    @Test
    @WithUserDetails("steve")
    public void getAllRunsTest() throws ParseException{
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

        for(int i = 0; i < 5; i++){
            Cycling cycle = new Cycling();
            cycle.setTime(10 + i);
            cycle.setDistance(10 + i);
            cycle.setUser(user);
            cycle.setCyclingDate(sqlDate);
            cycleList.add(cycle);
        }

        when(cycleRepo.findAll()).thenReturn(cycleList);
        assertEquals(5, cycleServices.getAllRuns().size());
    }

    @Test
    @WithUserDetails("steve")
    public void addCyclingTest() throws ParseException{
        String date = "2021-05-24";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dateObject = dateFormat.parse(date);

        java.sql.Date sqlDate = new java.sql.Date(dateObject.getTime());

        User user = new User();
        user.setUsername("steve");
        user.setFirstname("Steve");
        user.setLastname("Smith");
        user.setId(1);

        Cycling cycle = new Cycling();
        cycle.setTime(10);
        cycle.setDistance(10);
        cycle.setUser(user);
        cycle.setCyclingDate(sqlDate);

        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        Mockito.when(cycleRepo.save(cycle)).thenReturn(cycle);

        assertEquals(cycle, cycleServices.addCycling(cycle));
    }

    @Test
    @WithUserDetails("steve")
    public void getCyclingByUserTest() throws ParseException{
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

        when(cycleRepo.findAllByUserOrderByCyclingDateDesc(user)).thenReturn(cycleList);
        Mockito.when(userService.getCurrentUser()).thenReturn(user);
        assertEquals(1, cycleServices.getCyclingByUser().size());
    }
}
