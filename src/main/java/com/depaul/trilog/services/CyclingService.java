package com.depaul.trilog.services;

import com.depaul.trilog.dao.CyclingRepository;
import com.depaul.trilog.entities.Cycling;
import com.depaul.trilog.entities.Run;
import com.depaul.trilog.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CyclingService {
    @Autowired
    private CyclingRepository cyclingRepo;

    @Autowired
    private UserService userService;

    public List<Cycling> getAllRuns(){
        List <Cycling> cyclings = new ArrayList<>();
        cyclingRepo.findAll().forEach(cycling-> cyclings.add(cycling));
        return cyclings;
    }

    public Cycling addCycling(Cycling cycling) {
        cycling.setUser(userService.getCurrentUser());
        cyclingRepo.save(cycling);
        return cycling;
    }

    public List<Cycling> getCyclingByUser(){
        List <Cycling> CyclesByUserID = new ArrayList<>();
        cyclingRepo.findAllByUserOrderByCyclingDate(userService.getCurrentUser()).forEach(cycling-> CyclesByUserID.add(cycling));
        return CyclesByUserID;
    }
}
