package com.example.alpharoombooking.repositories;

import com.example.alpharoombooking.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
