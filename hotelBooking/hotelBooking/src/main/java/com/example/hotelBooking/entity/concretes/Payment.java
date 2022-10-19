package com.example.hotelBooking.entity.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int id;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne()
    @JoinColumn(name = "room_booking_id")
    private RoomBooking roomBooking;

}
