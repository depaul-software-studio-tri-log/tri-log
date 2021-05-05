package com.depaul.trilog.swim;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SwimService {
	
	@Autowired
	private SwimRepository swimRepo;
	
	
	public List<Swim> getAllSwims(){
		List<Swim> swims = new ArrayList <Swim>();
		swimRepo.findAll().forEach( swim-> swims.add(swim));
		
		return swims;
	}
	
	
	public Swim addSwim(Swim swim) {
		return swimRepo.save(swim);
		
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

}
