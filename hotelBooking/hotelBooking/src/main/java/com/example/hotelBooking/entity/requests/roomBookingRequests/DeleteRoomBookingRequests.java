package com.example.hotelBooking.entity.requests.roomBookingRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRoomBookingRequests {
    @NotNull
    private int id;
}
