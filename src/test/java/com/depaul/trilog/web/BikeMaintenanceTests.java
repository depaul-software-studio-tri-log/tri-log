package com.depaul.trilog.web;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;



import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.depaul.trilog.configuration.TestUserConfiguration;
import com.depaul.trilog.entities.BikeMaintenance;
import com.depaul.trilog.entities.User;
import com.depaul.trilog.services.BikeMaintenanceService;
import com.depaul.trilog.services.UserService;
//import com.sun.tools.javac.util.List;


@Import(TestUserConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BikeMaintenanceTests {
	
	@Autowired
	private MockMvc mockMvc;
	
		
	@MockBean
	private UserService userService;
	
	@MockBean
	private BikeMaintenanceService bikeMainServ;
	
	
	
	
	@Test
	@WithUserDetails("steve")
	public void showInputNoteTest() {
		
		
		try {
			mockMvc.perform(get("/trilog/bikeMaintenanceNotes?addMaintenanceNote")).andExpect(status().isOk())
				.andExpect(model().attributeExists("bikeMainNote"))
				.andExpect(view().name("input_bike_maintenance_notes"));
		}
		
		catch (Exception e) {
			fail("Get request to /trilog/bikeMaintenanceNotes failed: " + e.getCause());
		}
		
	}
	
	

}
