package com.example.hotelBooking.api.models;


import com.example.hotelBooking.entity.requests.campaignRequests.UseCampaignCodeRequest;
import com.example.hotelBooking.entity.requests.creditCardRequests.CreateCreditCardRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakePayment {

    @NotNull
    @Valid
    CreateCreditCardRequest createCreditCardRequest;

    UseCampaignCodeRequest useCampaignCodeRequest;

    @NotNull
    @Valid
    int roomBookingId;


}
