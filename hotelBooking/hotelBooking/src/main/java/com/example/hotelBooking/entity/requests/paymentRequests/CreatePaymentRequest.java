package com.example.hotelBooking.entity.requests.paymentRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {

    @JsonIgnore
    private double totalPrice;

    @JsonIgnore
    private int roomBookingId;
}
