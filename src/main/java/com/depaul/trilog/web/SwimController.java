package com.depaul.trilog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.depaul.trilog.dao.SwimRepository;
import com.depaul.trilog.entities.Swim;
import com.depaul.trilog.services.SwimService;




@Controller 
@RequestMapping ("/trilog/swim")
public class SwimController {
	
	@Autowired
	private SwimRepository swimRepo;
	
	@Autowired
	private SwimService swimService;
	
	
	@GetMapping 
	public String viewSwims(Model model) {
		model.addAttribute("swims",swimService.getAllSwims());
		
		return "swims/viewSwims";
	}
	
	@RequestMapping(params ="addSwim")
	public String addSwim(Model model) {
		model.addAttribute("newSwim", new Swim());
		return "swims/input_swim";
		
		
	}
	
	@PostMapping
	public String createSwim(@ModelAttribute("newSwim")Swim swim, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "swims/input_swim";
		}
		
		//swimService.addSwim(swim);
		swimRepo.save(swim);
		return "redirect:/trilog/swim";
		
	}
	
	

}
