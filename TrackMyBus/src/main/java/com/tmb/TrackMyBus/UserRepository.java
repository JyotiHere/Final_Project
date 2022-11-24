package com.tmb.TrackMyBus;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<UserDetails, Integer> {

    UserDetails findByEmailAndPassword(String email, String password);

}
