package com.depaul.trilog.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.depaul.trilog.entities.Cycling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depaul.trilog.dao.RunRepository;
import com.depaul.trilog.entities.Run;
import com.depaul.trilog.entities.User;

@Service
public class RunService {

	@Autowired
	private RunRepository runRepo;



	public List<Run>getAllRuns(){
		List <Run> runs = new ArrayList<>();
		runRepo.findAll().forEach(run-> runs.add(run));

		return runs;


	}

	public Run addRun(Run run) {
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


	public List<Run> getRunsByUser(User user){
		List <Run> runs = new ArrayList<>();
		runRepo.findByUser(user).forEach(runs::add);
		return runs;

	}




}