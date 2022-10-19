package com.example.hotelBooking.entity.dtos.hotelDtos;

import com.example.hotelBooking.core.utilities.enums.Cities;
import com.example.hotelBooking.core.utilities.enums.HotelStar;
import com.example.hotelBooking.core.utilities.enums.HotelType;
import com.example.hotelBooking.core.utilities.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelListDto {

    private int id;
    private String hotelName;
    private long hotelTel;
    private HotelType hotelType;
    private HotelStar hotelStar;
    private Cities cities;
    private UserRole userRole;
}
