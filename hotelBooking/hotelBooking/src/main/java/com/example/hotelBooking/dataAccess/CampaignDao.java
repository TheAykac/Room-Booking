package com.example.hotelBooking.dataAccess;

import com.example.hotelBooking.entity.concretes.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignDao extends JpaRepository<Campaign, Integer> {


    Campaign getCampaignByCampaignCode(String campaignCode);

    Campaign getByCampaignCode(String campaingCode);

    boolean existsByCampaignCode(String campaignCode);
}
