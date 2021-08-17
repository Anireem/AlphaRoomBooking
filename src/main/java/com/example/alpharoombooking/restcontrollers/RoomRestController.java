package com.example.alpharoombooking.restcontrollers;

import com.example.alpharoombooking.exceptions.ResourceNotFoundException;
import com.example.alpharoombooking.models.Room;
import com.example.alpharoombooking.models.Room;
import com.example.alpharoombooking.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class RoomRestController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    // Get by id
    @GetMapping("rooms/{id}")
    public ResponseEntity<Room> getById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Комната не найдена, id: " + id));
        return ResponseEntity.ok().body(room);
    }

    // Save
    @PostMapping("rooms")
    public Room save(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    // Update
    @PutMapping("rooms/{id}")
    public ResponseEntity<Room> update(@PathVariable("id") Long id, @RequestBody Room room) {
        return ResponseEntity.ok(roomRepository.save(room));
    }

    // Delete
    @DeleteMapping("rooms/{id}")
    public Map<String, Boolean> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Комната не найдена, id: " + id));

        roomRepository.delete(room);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
