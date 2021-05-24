package com.depaul.trilog.dao;

import com.depaul.trilog.entities.Cycling;
import com.depaul.trilog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CyclingRepository extends JpaRepository<Cycling, Long> {
    List<Cycling> findAllByUserOrderByCyclingDate (User user);

}
