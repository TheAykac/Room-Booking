package com.example.hotelBooking.entity.requests.roomTypeRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoomTypeRequest {

    @NotNull
    @NotBlank
    @Size(min = 3)
    private String name;
}
