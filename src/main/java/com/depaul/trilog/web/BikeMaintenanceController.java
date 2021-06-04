package com.depaul.trilog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.depaul.trilog.entities.BikeMaintenance;
import com.depaul.trilog.services.BikeMaintenanceService;

@Controller
@RequestMapping("/trilog/bikeMaintenanceNotes")
public class BikeMaintenanceController {
	
	@Autowired
	BikeMaintenanceService bikeMainServ;
	
	
	@GetMapping
	public String getbikeMainNotes(Model model) {
		
		model.addAttribute("bikeMainNotes", bikeMainServ.getMainNotes());
		return("bikeMaintenance/allMaintenanceNotes");
		
		
	}
	
	
	@RequestMapping(params = "addMaintenanceNote")
	public String addMainNote(Model model) {
		model.addAttribute("bikeMainNote", new BikeMaintenance());
		
		return ("input_bike_maintenance_notes");
	}
	
	
	@PostMapping
	public String createMaintenanceNote(@ModelAttribute("bikeMainNote")BikeMaintenance bikeMainNote, BindingResult binding) {
		if(binding.hasErrors()) {
			return "bikeMaintenance/input_maintenanceNote";
		}
		
		bikeMainServ.addBikeMainNote(bikeMainNote);
		
		return "redirect:/trilog/bikeMaintenanceNotes";
		
	}
	
	
	
	

}
