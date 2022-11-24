package com.tmb.TrackMyBus;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface QueryRepository extends JpaRepository <QueryDetails, Integer>{
    
}
