package com.depaul.trilog.swim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwimRepository extends JpaRepository<Swim, Long> {

}
