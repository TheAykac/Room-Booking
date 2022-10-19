package com.example.hotelBooking.entity.requests.hotelRequests;

import com.example.hotelBooking.core.utilities.enums.Cities;
import com.example.hotelBooking.core.utilities.enums.HotelStar;
import com.example.hotelBooking.core.utilities.enums.HotelType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateHotelRequest {

    @NotNull
    private int id;

    private String hotelName;

    private long hotelTel;


    private HotelStar hotelStar;


    private HotelType hotelType;


    private Cities cities;
}
