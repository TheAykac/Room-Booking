package com.example.hotelBooking.entity.requests.campaignRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UseCampaignCodeRequest {

    private String campaignCode;
}
