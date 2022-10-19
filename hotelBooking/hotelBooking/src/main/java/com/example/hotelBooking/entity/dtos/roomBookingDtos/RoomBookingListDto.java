package com.example.hotelBooking.entity.dtos.roomBookingDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingListDto {

    private int id;
    private LocalDate startDate;
    private LocalDate finishDate;
    private int roomId;
    private int customerId;
    private double totalPrice;


}
