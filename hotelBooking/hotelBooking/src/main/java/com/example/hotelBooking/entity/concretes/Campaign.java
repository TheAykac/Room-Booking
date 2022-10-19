package com.example.hotelBooking.entity.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private int id;

    @Column(name = "campaign_name")
    private String campaignName;

    @Column(name = "precent_discount")
    private int percentDiscount;

    @Column(name = "campaign_code")
    private String campaignCode;

    @Column(name = "capaign_quantity")
    private double campaignQuantity;

    @Column(name = "campaign_quantity_used")

    private double campaignQuantityUsed = 0;

    @Column(name = "campaign_start_date")
    private LocalDate campaignStartDate;

    @Column(name = "campaign_finish_date")
    private LocalDate campaignFinishDate;
}
