package com.example.hotelBooking.entity.concretes;

import com.example.hotelBooking.core.utilities.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rooms")

public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "person_capacity")
    private int personCapacity;

    @Column(name = "floor")
    private int floor;

    @Column(name = "daily_price")
    private double dailyPrice;

    @Column(name = "room_Type")
    @Enumerated(EnumType.STRING)
    private RoomType roomType;


    @OneToMany(mappedBy = "room")
    private List<RoomBooking> roomBookings;

    @OneToMany(mappedBy = "room")
    private List<RoomRenovation> roomRenovations;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


}
