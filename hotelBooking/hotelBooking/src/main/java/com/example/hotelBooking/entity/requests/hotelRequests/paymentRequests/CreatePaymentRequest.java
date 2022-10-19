package com.example.hotelBooking.entity.requests.hotelRequests.paymentRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CreatePaymentRequest {

    @JsonIgnore
    private double totalPrice;

    @JsonIgnore
    private int roomBookingId;

}
