package com.example.alpharoombooking.controllers;

import com.example.alpharoombooking.models.Booking;
import com.example.alpharoombooking.models.Room;
import com.example.alpharoombooking.repositories.BookingRepository;
import com.example.alpharoombooking.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String addNew(@ModelAttribute("booking") Booking booking, @ModelAttribute("roomId") String roomId, Model model) {
        if (!roomId.equals("")) {
            Room room = roomRepository.findById(Long.parseLong(roomId)).get();
            booking.setRoom(room);
        }

        model.addAttribute("rooms", roomRepository.findAll());
        return "bookings/show";
    }

}
