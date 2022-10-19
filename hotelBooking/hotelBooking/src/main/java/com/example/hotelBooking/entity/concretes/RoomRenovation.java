package com.example.hotelBooking.entity.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room_renavations")

public class RoomRenovation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_renovation_id")
    private int id;

    @Column(name = "room_renovation_description")
    private String roomRenovationDescription;


    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
