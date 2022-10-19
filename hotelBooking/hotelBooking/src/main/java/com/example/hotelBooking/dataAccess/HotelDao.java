package com.example.hotelBooking.dataAccess;

import com.example.hotelBooking.entity.concretes.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface HotelDao extends JpaRepository<Hotel, Integer> {

    Hotel getById(int hotelId);

    boolean existsByEmail(String email);

    @Query("Select h from Hotel h where h.email=:email ")
    Hotel findByEmail(@Param("email") String email);


}

