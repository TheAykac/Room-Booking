package com.example.hotelBooking.entity.requests.campaignRequests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCampaignRequest {

    @NotNull
    private String campaignName;

    @NotNull
    private int percentDiscount;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate campaignStartDate;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate campaignFinishDate;

    @NotNull
    private String campaignCode;

    @NotNull
    private double campaignQuantity;
}


