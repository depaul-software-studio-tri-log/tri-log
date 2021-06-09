package com.depaul.trilog.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depaul.trilog.dao.ShoeRepository;
import com.depaul.trilog.entities.Run;
import com.depaul.trilog.entities.Shoe;
import com.depaul.trilog.entities.User;

@Service
public class ShoeService {
	
	@Autowired
	private ShoeRepository shoeRepo;
	
	@Autowired
	private UserService userService;
	
	
	public Shoe addShoe(Shoe shoe) {
		shoe.setUser(userService.getCurrentUser());
		shoeRepo.save(shoe);
		
		return shoe;
	}
	
	public void deleteShoe(Shoe shoe) {
		shoeRepo.delete(shoe);
	}
	
	
	public List<Shoe> getShoesByUser(User user){
		List<Shoe> shoes = new ArrayList<Shoe>();
		shoeRepo.findByUser(userService.getCurrentUser()).forEach(shoe-> shoes.add(shoe));
		return shoes;
			
		
	}
	
	public int getShoeMileage(Shoe shoe) {
		//List <Run> runByShoe = new ArrayList<>();
		int mileage =shoe.getMileage();
		return mileage;
	}
	
	
	public List<Shoe> getShoes(){
		List<Shoe> allShoes = new ArrayList<Shoe>();
		shoeRepo.findAll().forEach(shoe -> allShoes.add(shoe));
		return allShoes;
		
	}
	
	
	public Shoe getShoe() {
		Shoe shoe = new Shoe();
		shoe.getShoeid();
		
		return shoe;
	}
	

}
