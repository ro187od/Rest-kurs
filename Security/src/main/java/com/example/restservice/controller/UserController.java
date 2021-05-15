package com.example.restservice.controller;

import com.example.restservice.model.User;
import com.example.restservice.service.UserDetailsServiceImp;
import com.example.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @GetMapping("/users")
    public List<User> getAll() {
        return userService.getAllUser();
    }

    @GetMapping("/users/me")
    public Optional<User> getMe() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        Optional<User> user = userService.getOne(username);
        return user;
    }

    @RequestMapping(value = "/users/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    UserDetails login(@RequestBody User user) {
        return userDetailsServiceImp.loadUserByUsername(user.getUsername());
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<User> getOne(@PathVariable String username) {
        return userService.getOne(username);
    }

    @RequestMapping(value = "/users/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody boolean register(@RequestBody User user) {
        return userService.registerUser(user);
    }


}

