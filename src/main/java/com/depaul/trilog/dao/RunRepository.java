package com.depaul.trilog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.depaul.trilog.entities.Run;
import com.depaul.trilog.entities.User;



public interface RunRepository extends JpaRepository <Run, Long>{
	List<Run> findByUser (User user);

}
