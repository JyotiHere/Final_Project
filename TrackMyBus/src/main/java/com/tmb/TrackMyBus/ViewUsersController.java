package com.tmb.TrackMyBus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class ViewUsersController {
    // GET REQUEST TO VIEW ALL USERS
    @Autowired
    UserRepository repo;

    @GetMapping("/allUsers")
    public List<UserDetails> viewUsers() {
        return repo.findAll();
    }

    // GET REQUEST TO VIEW ALL USERS
    @Autowired
    QueryRepository queryRepo;

    @GetMapping("/allQueries")
    public List<QueryDetails> viewQueries() {
        return queryRepo.findAll();
    }

    // Get user by id
    // @GetMapping("/allUsers/{id}")
    // public ResponseEntity<UserDetails> getUsersById(@PathVariable Integer id){
    // UserDetails getUsers = repo.getById(id);
    // return ResponseEntity.ok().body(getUsers);
    // }

    // DELETE REQUEST to Delete any user based upon id
    // @DeleteMapping("/allUsers/{id}")
    // public String deleteUserById(@PathVariable Integer id) {
    //     UserDetails getUsers = repo.getReferenceById(id);
    //     repo.delete(getUsers);
    //     return "Deleted successfully";
    // }
    
}


