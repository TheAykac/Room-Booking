package com.example.hotelBooking.entity.concretes;

import com.example.hotelBooking.core.utilities.enums.Cities;
import com.example.hotelBooking.core.utilities.enums.HotelStar;
import com.example.hotelBooking.core.utilities.enums.HotelStatu;
import com.example.hotelBooking.core.utilities.enums.HotelType;
import com.example.hotelBooking.entity.abstracts.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotels")
@PrimaryKeyJoinColumn(name = "hotel_id", referencedColumnName = "user_id")

public class Hotel extends User {


    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "hotel_tel")
    private long hotelTel;


    @Column(name = "hotel_type")
    @Enumerated(EnumType.STRING)
    private HotelType hotelType;

    @Column(name = "hotel_star")
    @Enumerated(EnumType.STRING)
    private HotelStar hotelStar;

    @Column(name = "city")
    @Enumerated(EnumType.STRING)
    private Cities cities;

    @Column(name = "hotel_statu")
    @Enumerated(EnumType.STRING)
    private HotelStatu hotelStatu;


    /*@OneToMany(mappedBy = "hotel",fetch = FetchType.LAZY)
    private List<Room> rooms;*/


}
