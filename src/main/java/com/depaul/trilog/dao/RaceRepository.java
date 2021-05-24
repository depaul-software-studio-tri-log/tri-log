package com.depaul.trilog.dao;

import com.depaul.trilog.entities.Race;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceRepository extends CrudRepository<Race, Integer> {

    @Query(nativeQuery=true, value="SELECT * FROM races WHERE date > NOW() AND userid = (?1) ORDER BY date")
    List<Race> getAllFutureRacesForUser(int userId);

    @Query(nativeQuery=true, value="SELECT * FROM races WHERE date > NOW() and userid = (?1) ORDER BY date LIMIT 1")
    Race getNextRaceForUser(int userId);

}
