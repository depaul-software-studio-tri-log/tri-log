package com.depaul.trilog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.depaul.trilog.entities.Shoe;
import com.depaul.trilog.services.ShoeService;

@Controller
@RequestMapping("trilog/shoe")
public class ShoeController {

	@Autowired
	private ShoeService shoeServ;
	
	
	
	@GetMapping
	public String viewShoes(Model model) {
		model.addAttribute("shoes", shoeServ.getShoes());
		
		return "trilog/shoe";
		
		
	}
	
	@RequestMapping(params ="addshoe")
	public String addShoe(Model model) {
		model.addAttribute("newShoe", new Shoe());
		
		return "input_new_shoe";
		
	}
	
	
	@PostMapping
	public String createShoe(@ModelAttribute("newShoe") Shoe shoe, BindingResult binding ) {
		if (binding.hasErrors()) {
			return "input_new_shoe";
		}
		
		shoeServ.addShoe(shoe);
		
		return "redirect:trilog/shoe ";
		
		
	}
	
	
	
	
	
}
