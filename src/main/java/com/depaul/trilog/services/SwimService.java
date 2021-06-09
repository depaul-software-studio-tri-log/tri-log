package com.depaul.trilog.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.depaul.trilog.entities.Cycling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depaul.trilog.dao.SwimRepository;
import com.depaul.trilog.entities.Swim;

@Service
public class SwimService {
	
	@Autowired
	private SwimRepository swimRepo;

	@Autowired
	private UserService userService;
	
	
	public List<Swim> getAllSwims(){
		List<Swim> swims = new ArrayList <Swim>();
		swimRepo.findAll().forEach( swim-> swims.add(swim));
		
		return swims;
	}

	public Swim addSwim(Swim swim) {
		swim.setUser(userService.getCurrentUser());
		swimRepo.save(swim);
		return swim;
	}
	
	public void deleteSwim (Swim swim) {
		swimRepo.delete(swim);
	}
	
	//return swims for a specific date
	public List<Swim> getSwimByDate (Date swimDate){
		List <Swim> swims = new ArrayList<Swim>();
		swimRepo.findAll().forEach(date-> {
			if(date.getSwimDate().equals(swimDate))
				swims.add(date);
		});
		
		return swims;
		
	}

	public List<Swim> getSwimsByUser(){
		List <Swim> SwimsByUserID = new ArrayList<>();
		swimRepo.findAllByUserOrderBySwimDateDesc(userService.getCurrentUser()).forEach(swim-> SwimsByUserID.add(swim));
		return SwimsByUserID;
	}

}

