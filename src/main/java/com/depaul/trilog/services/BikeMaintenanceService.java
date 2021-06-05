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
	
    @Autowired
    private UserService userService;
	
	
	public BikeMaintenance addBikeMainNote(BikeMaintenance bikeMainNote) {
		bikeMainNote.setUser(userService.getCurrentUser());
		return bikeMainRepo.save(bikeMainNote);
		
		
	}
	
	
	public List<BikeMaintenance> getMainNotes(){
		List <BikeMaintenance> mainNotes = new ArrayList<BikeMaintenance>();
		bikeMainRepo.findAll().forEach(note-> mainNotes.add(note));
		
		
		return mainNotes;
		
	}
	
	public List<BikeMaintenance> getNotesByUser(){
		List <BikeMaintenance> mainNotesByUser = new ArrayList<BikeMaintenance>();
		bikeMainRepo.findAllByUser(userService.getCurrentUser()).forEach(note -> mainNotesByUser.add(note));
		
		
		return mainNotesByUser;
		
	}
	
	
	public void deleteNote (BikeMaintenance bikeMain) {
		bikeMainRepo.delete(bikeMain);
	}
	
	

}
