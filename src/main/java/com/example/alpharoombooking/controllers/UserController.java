package com.example.alpharoombooking.controllers;

import com.example.alpharoombooking.models.Role;
import com.example.alpharoombooking.models.User;
import com.example.alpharoombooking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;

@Controller
@RequestMapping("/users")
@Service
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Открываем форму списка
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/index";
    }

    // Открываем форму редактирования
    @GetMapping ("{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userRepository.getById(id));
        return "users/show";
    }

    // Открываем форму создания нового
    @GetMapping ("new")
    public String addNew(@ModelAttribute("user") User user) {
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.EMPLOYEE));
        return "users/show";
    }
    
}
