package com.example.hotelBooking.entity.requests.roomRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRoomRequest {

    @NotNull
    private int id;
}
