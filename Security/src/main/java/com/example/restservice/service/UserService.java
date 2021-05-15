package com.example.restservice.service;

import com.example.restservice.model.User;
import com.example.restservice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;



    public Optional<User> getOne(String username){
        Optional<User> user = userRepo.findByUsername(username);
        return user;
    }

    public boolean registerUser(User user) {
        userRepo.save(user);
        return true;
    }

    public Optional<User> loginUser(User user){
        Optional<User> userFromDb = userRepo.findByUsername(user.getUsername());
        return userFromDb;
    }

    public List<User> getAllUser() {
        List<User> usersList = userRepo.findAll();
        return usersList;
    }
}
