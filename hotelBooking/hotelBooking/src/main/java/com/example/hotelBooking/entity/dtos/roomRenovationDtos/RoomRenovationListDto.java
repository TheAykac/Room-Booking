package com.example.hotelBooking.entity.dtos.roomRenovationDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRenovationListDto {

    private int id;
    private int roomID;
    private String roomRenovationDescription;

}
