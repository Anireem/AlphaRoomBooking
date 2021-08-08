package com.example.alpharoombooking.controllers;

import com.example.alpharoombooking.models.Room;
import com.example.alpharoombooking.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    // Открываем форму списка
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("rooms", roomRepository.findAll());
        return "rooms/index";
    }

    // Открываем форму редактирования
    @GetMapping ("{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("room", roomRepository.getById(id));
        return "rooms/show";
    }

    // Открываем форму создания нового
    @GetMapping ("new")
    public String addNew(@ModelAttribute("room") Room room) {
        return "rooms/show";
    }

    // Сохранение
    @PostMapping()
    public String create(@ModelAttribute("room") Room room) {
        roomRepository.save(room);
        return "redirect:/rooms";
    }

    // Удаление
    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        roomRepository.deleteById(id);
        return "redirect:/rooms";
    }
}
