package com.taskplanner.user.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskplanner.user.model.User;
import com.taskplanner.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email ){
        Optional<User> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }


}
