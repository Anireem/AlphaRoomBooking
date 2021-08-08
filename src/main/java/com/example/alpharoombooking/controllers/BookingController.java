package com.example.alpharoombooking.controllers;

import com.example.alpharoombooking.models.Role;
import com.example.alpharoombooking.models.Booking;
import com.example.alpharoombooking.repositories.BookingRepository;
import com.example.alpharoombooking.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    // Открываем форму списка
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("bookings", bookingRepository.findAll());
        return "bookings/index";
    }

    // Открываем форму редактирования
    @GetMapping ("{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("booking", bookingRepository.getById(id));
        model.addAttribute("rooms", roomRepository.findAll());
        return "bookings/show";
    }

    // Открываем форму создания нового
    @GetMapping ("new")
    public String addNew(@ModelAttribute("booking") Booking booking, Model model) {
        model.addAttribute("rooms", roomRepository.findAll());
        return "bookings/show";
    }

    // Сохранение
    @PostMapping()
    public String create(@ModelAttribute("booking") Booking booking) {
        bookingRepository.save(booking);
        return "redirect:/bookings";
    }

    // Удаление
    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        bookingRepository.deleteById(id);
        return "redirect:/bookings";
    }
}
