package com.depaul.trilog.dao;

import com.depaul.trilog.entities.Run;
import com.depaul.trilog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CyclingRepository extends JpaRepository<Run, Long> {
    List<Run> findByUser (User user);
}
