package com.depaul.trilog.dao;

import com.depaul.trilog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.depaul.trilog.entities.Swim;

import java.util.List;
import java.util.Map;

@Repository
public interface SwimRepository extends JpaRepository<Swim, Long> {

    List<Swim> findAllByUserOrderBySwimDateDesc(User userr);
}
