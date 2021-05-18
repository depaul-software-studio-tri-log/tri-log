package com.depaul.trilog.dao;

import com.depaul.trilog.entities.Goals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GoalsRepository extends JpaRepository<Goals, Integer> {
//    List<Goal> findByUser_id(int User_id);
}
