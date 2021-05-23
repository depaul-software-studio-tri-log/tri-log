package com.depaul.trilog.services;

import com.depaul.trilog.dao.CyclingRepository;
import com.depaul.trilog.dao.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CyclingService {
    @Autowired
    private CyclingRepository cyclingRepo;
}
