package com.depaul.trilog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.depaul.trilog.entities.BikeMaintenance;
import com.depaul.trilog.entities.User;




public interface BikeMaintenanceRepository extends JpaRepository<BikeMaintenance, Long> {

		List<BikeMaintenance> findAllByUser (User user);
}
