package com.depaul.trilog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.depaul.trilog.entities.Swim;

@Repository
public interface SwimRepository extends JpaRepository<Swim, Long> {

}
