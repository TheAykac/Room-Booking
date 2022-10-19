package com.example.hotelBooking.entity.requests.roomBookingRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDeliveryDateRequest {

    @NotNull
    @Min(1)
    private int roomBookingId;

    @NotNull
    private LocalDate finisDate;
}
