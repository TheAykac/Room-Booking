package com.example.hotelBooking.dataAccess;

import com.example.hotelBooking.entity.concretes.RoomRenovation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRenovationDao extends JpaRepository<RoomRenovation, Integer> {
    boolean existsByRoomId(int id);

    RoomRenovation findByRoomId(int id);


}

