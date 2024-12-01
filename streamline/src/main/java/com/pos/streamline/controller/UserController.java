package com.pos.streamline.controller;

import com.pos.streamline.entity.User;
import com.pos.streamline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws Exception {
        User userData = null;
        try {
            userData =  userService.register(user);
        }
        catch (Exception e) {
            e.fillInStackTrace();
            throw new Exception("error in saving."+e.getMessage());
        }

        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) throws Exception {
        String token = null;
        try {
            token =  userService.login(user);
        }
        catch (Exception e) {
            e.fillInStackTrace();
            throw new Exception("error in saving."+ e.getMessage());
        }

        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
