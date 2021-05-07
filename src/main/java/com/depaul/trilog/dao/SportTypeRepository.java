package com.depaul.trilog.dao;

import com.depaul.trilog.entities.SportType;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SportTypeRepository extends CrudRepository<SportType, Integer> {

    List<SportType> findAllByOrderById();

}
