package com.example.hotelBooking.dataAccess;

import com.example.hotelBooking.entity.concretes.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<Payment, Integer> {

    Payment getAllByRoomBooking_Id(int roomBokingId);

    boolean existsByRoomBooking_Id(int roomBookingId);

}
