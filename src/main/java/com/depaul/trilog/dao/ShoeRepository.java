package com.depaul.trilog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.depaul.trilog.entities.Shoe;
import com.depaul.trilog.entities.User;

public interface ShoeRepository extends JpaRepository <Shoe, Long>{
	List<Shoe> findByUser (User user);
	
	
}
