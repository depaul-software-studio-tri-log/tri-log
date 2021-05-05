package com.depaul.trilog.swim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller 
@RequestMapping ("/trilog/swim")
public class SwimController {
	
	//@Autowired
	//private SwimRepository swimRepo;
	
	@Autowired
	private SwimService swimService;
	
	
	@GetMapping 
	public String viewSwims(Model model) {
		model.addAttribute("swim",swimService.getAllSwims());
		
		return "swims/viewSwims";
	}
	
	@RequestMapping(params ="addSwim")
	public String addSwim(Model model) {
		model.addAttribute("swim", new Swim());
		return "swims/ swim";
		
		
	}
	
	@PostMapping
	public String createSwim(@ModelAttribute("swims")Swim swim, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "swims/swim";
		}
		
		swimService.addSwim(swim);
		return "redirect: /trilog/swim";
		
	}
	
	

}
