package com.example.alpharoombooking.restcontrollers;

import com.example.alpharoombooking.models.User;
import com.example.alpharoombooking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("users")
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
