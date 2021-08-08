package com.example.alpharoombooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class AlphaRoomBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlphaRoomBookingApplication.class, args);
        System.out.println(LocalDateTime.now());
    }

}
