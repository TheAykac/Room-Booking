package com.example.hotelBooking.entity.requests.roomBookingRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliverTheRoomRequest {

    @NotNull
    @Min(1)
    private int roomBookingId;

    @NotNull
    @Min(1)
    private int roomId;
}
