package com.example.hotelBooking.entity.requests.hotelRequests;

import com.example.hotelBooking.core.utilities.enums.Cities;
import com.example.hotelBooking.core.utilities.enums.HotelStar;
import com.example.hotelBooking.core.utilities.enums.HotelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateHotelRequest {


    private String hotelName;

    private long hotelTel;

    private String hotelEmail;

    private String hotelPassword;

    @Enumerated
    private HotelType hotelType;
    @Enumerated
    private HotelStar hotelStar;
    @Enumerated
    private Cities cities;


}
