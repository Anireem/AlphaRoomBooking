package com.example.alpharoombooking.restcontrollers;

import com.example.alpharoombooking.exceptions.ResourceNotFoundException;
import com.example.alpharoombooking.models.User;
import com.example.alpharoombooking.repositories.RoomRepository;
import com.example.alpharoombooking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Service
@PreAuthorize("hasRole('ADMIN')")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    // Get all
    @GetMapping("users")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // Get by id
    @GetMapping("users/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден, id: " + id));
        return ResponseEntity.ok().body(user);
    }

    // Save
    @PostMapping("users")
    public User save(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Update
    @PutMapping("users/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    // Delete
    @DeleteMapping("users/{id}")
    public Map<String, Boolean> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден, id: " + id));

        userRepository.delete(user);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
