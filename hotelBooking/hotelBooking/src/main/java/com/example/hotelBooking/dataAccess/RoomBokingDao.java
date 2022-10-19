package com.example.hotelBooking.dataAccess;

import com.example.hotelBooking.entity.concretes.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomBokingDao extends JpaRepository<RoomBooking, Integer> {


    boolean existsByRoomId(int id);

    boolean existsById(int roomBookingId);


    List<RoomBooking> getAllByRoom_Id(int roomId);

    List<RoomBooking> getAllByCustomer_UserId(int customerId);


}
