package com.example.hotelBooking.dataAccess;

import com.example.hotelBooking.entity.concretes.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoomDao extends JpaRepository<Room, Integer> {

    List<Room> findByHotelUserId(int id);

    boolean existsById(int roomId);

    List<Room> findByDailyPriceLessThanEqual(double dailyPrice);


}
