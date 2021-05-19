package com.depaul.trilog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.depaul.trilog.dao.RunRepository;
import com.depaul.trilog.entities.Run;
import com.depaul.trilog.services.RunService;

@Controller
@RequestMapping("/trilog/run")
public class RunController {
	
	@Autowired
	private RunRepository runRepo;
	
	@Autowired
	private RunService runServ;
	
	@RequestMapping
	public String viewRuns(Model model) {
		model.addAttribute("runs", runServ.getAllRuns());
		
		
		return "/trilog/run";
		
	}
	
	@RequestMapping(params = "addRun")
	public String addRun(Model model) {
		model.addAttribute("newRun", new Run());
		
		return "runs/input_runs";
		
		
	}
	
	
	@PostMapping
	public String createRun(@ModelAttribute("newRun") Run run, BindingResult binding) {
		if(binding.hasErrors()) {
			return "runs/input_runs";
		}
		
		runRepo.save(run);
		
		return "redirect:/trilog/run";
	}
	
	
	
	
	
	
	

}
