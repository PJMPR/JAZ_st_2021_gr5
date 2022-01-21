package com.pjwstk.sakila.security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class UsersController {

    @GetMapping("register")
    public ResponseEntity register(){
        return ResponseEntity.ok("dzialam");
    }
}
