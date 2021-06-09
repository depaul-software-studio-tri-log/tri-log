package com.depaul.trilog.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depaul.trilog.dao.RunRepository;
import com.depaul.trilog.entities.Run;
import com.depaul.trilog.entities.Shoe;
import com.depaul.trilog.entities.User;

@Service
public class RunService {

	@Autowired
	private RunRepository runRepo;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ShoeService shoeService;


	public List<Run>getAllRuns(){
		List <Run> runs = new ArrayList<>();
		runRepo.findAll().forEach(run-> runs.add(run));
		return runs;


	}

	public Run addRun (Run run) {
		run.setUser(userService.getCurrentUser());
		Shoe shoe = new Shoe();
		shoe.setMileage(run.getDistance());
		shoe.setShoeBrand(shoe.getShoeBrand());
		shoe.setShoeName(shoe.getShoeName());
		shoe.setUser(userService.getCurrentUser());
		shoe.setShoeid(1);
		
		run.setShoeid(shoe); 
		
		runRepo.save(run);
		return run;
 
	}

	public void deleteRun(Run run) {
		runRepo.delete(run);
	}

	public List<Run> getRunByDate (Date runDate){
		List <Run> runs = new ArrayList<Run>();
		runRepo.findAll().forEach(date-> {
			if(date.getRunDate().equals(runDate))
				runs.add(date);
		});

		return runs;

	}



	public Run getRun(Long runId) {

		return runRepo.findById(runId).get();
	}


/*	public List<Run> getRunsByUser(User user) {
		List<Run> runs = new ArrayList<>();
		runRepo.findByUser(user).forEach(runs::add);
<<<<<<< Updated upstream
		return runs;
	}
=======
		return runs;*/



		public List<Run> getRunsByUser(User user){
			List <Run> RunsByUserID = new ArrayList<>();
			runRepo.findByUser (userService.getCurrentUser()).forEach(runs-> RunsByUserID.add(runs));
			return RunsByUserID;
		
	}
}


