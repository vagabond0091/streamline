package com.pos.streamline.controller;

import com.pos.streamline.entity.User;
import com.pos.streamline.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")

public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user,HttpServletResponse response) throws Exception {
        User userData = null;
        String token = null;
        try {
            userData =  userService.register(user);
            token =  userService.login(user);
            Cookie cookie = new Cookie("jwt_token", token);
            cookie.setHttpOnly(true); // Prevents JavaScript access
            cookie.setSecure(true); // Requires HTTPS
            cookie.setPath("/"); // Accessible for the entire domain
            cookie.setMaxAge(60 * 60 * 24 * 7); // 7 days expiry
            cookie.setDomain("http://localhost:5173/"); // Replace with your domain
            cookie.setAttribute("SameSite", "Strict"); // Prevent CSRF
            response.addCookie(cookie);
        }
        catch (Exception e) {
            e.fillInStackTrace();
            throw new Exception("error in saving."+e.getMessage());
        }

        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpServletResponse response) throws Exception {
        String token = null;
        try {
            token =  userService.login(user);
            Cookie cookie = new Cookie("jwt_token", token);
            cookie.setHttpOnly(true); // Prevents JavaScript access
            cookie.setSecure(true); // Requires HTTPS
            cookie.setPath("/"); // Accessible for the entire domain
            cookie.setMaxAge(60 * 60 * 24 * 7); // 7 days expiry
            cookie.setDomain("localhost"); // Replace with your domain
            cookie.setAttribute("SameSite", "Strict"); // Prevent CSRF
            response.addCookie(cookie);
        }
        catch (Exception e) {
            e.fillInStackTrace();
            throw new Exception("error in saving."+ e.getMessage());
        }

        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
