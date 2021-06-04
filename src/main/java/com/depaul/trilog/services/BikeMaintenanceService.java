package com.depaul.trilog.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depaul.trilog.dao.BikeMaintenanceRepository;
import com.depaul.trilog.entities.BikeMaintenance;

@Service
public class BikeMaintenanceService {
	
	
	@Autowired
	BikeMaintenanceRepository bikeMainRepo;
	
	
	public BikeMaintenance addBikeMainNote(BikeMaintenance bikeMainNote) {
		return bikeMainRepo.save(bikeMainNote);
		
		
	}
	
	
	public List<BikeMaintenance> getMainNotes(){
		List <BikeMaintenance> mainNotes = new ArrayList<BikeMaintenance>();
		bikeMainRepo.findAll().forEach(note-> mainNotes.add(note));
		
		
		return mainNotes;
		
	}
	
	
	public void deleteNote (BikeMaintenance bikeMain) {
		bikeMainRepo.delete(bikeMain);
	}
	
	

}
