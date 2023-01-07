package com.example.hotelBooking.business.concretes;

import com.example.hotelBooking.business.abstracts.CampaignService;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.mapping.ModelMapperService;
import com.example.hotelBooking.core.utilities.messages.BusinessMessages;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.core.utilities.result.SuccessDataResult;
import com.example.hotelBooking.core.utilities.result.SuccessResult;
import com.example.hotelBooking.dataAccess.CampaignDao;
import com.example.hotelBooking.entity.concretes.Campaign;
import com.example.hotelBooking.entity.dtos.campaignDtos.CampaignListDto;
import com.example.hotelBooking.entity.requests.campaignRequests.CreateCampaignRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignManager implements CampaignService {


    // This is the constructor of the CampaignManager class. It is used to inject the dependencies of the class.
    private final CampaignDao campaignDao;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CampaignManager(CampaignDao campaignDao, ModelMapperService modelMapperService) {
        this.campaignDao = campaignDao;
        this.modelMapperService = modelMapperService;
    }

    /**
     * > This function takes a CreateCampaignRequest object, maps it to a Campaign object, saves the Campaign object and
     * returns a SuccessResult object
     *
     * @param createCampaignRequest This is the request object that is passed to the service layer.
     * @return A Result object.
     */
    @Override
    public Result add(CreateCampaignRequest createCampaignRequest) throws BusinessException {
        Campaign campaign = this.modelMapperService.forRequest().map(createCampaignRequest, Campaign.class);
        this.campaignDao.save(campaign);
        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }

    /**
     * > We get all the campaigns from the database, map them to a list of CampaignListDto objects and return a
     * SuccessDataResult object with the list of CampaignListDto objects and a success message
     *
     * @return A list of CampaignListDto objects.
     */
    @Override
    public DataResult<List<CampaignListDto>> getAll() throws BusinessException {
        List<Campaign> campaigns = this.campaignDao.findAll();
        List<CampaignListDto> campaignListDtos = campaigns.stream().map(campaign -> this.modelMapperService.forDto().map(campaign, CampaignListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(campaignListDtos, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * > This function gets a campaign by its id and returns it as a CampaignListDto
     *
     * @param campaignId The id of the campaign to be retrieved.
     * @return A DataResult<CampaignListDto> object.
     */
    @Override
    public DataResult<CampaignListDto> getById(int campaignId) throws BusinessException {
        Campaign campaign = this.campaignDao.getById(campaignId);
        CampaignListDto campaignListDto = this.modelMapperService.forDto().map(campaign, CampaignListDto.class);
        return new SuccessDataResult<>(campaignListDto, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * > This function returns a campaign by campaign code
     *
     * @param campaignCode The campaign code that we want to get the campaign.
     * @return A DataResult object with a Campaign object inside.
     */
    @Override
    public DataResult<Campaign> getByCampaignCode(String campaignCode) throws BusinessException {
        Campaign campaign = this.campaignDao.getByCampaignCode(campaignCode);
        return new SuccessDataResult<>(campaign, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * This function checks if the campaign start date is after the current date. If it is, it throws a business exception
     *
     * @param campaignCode The campaign code that is used to get the campaign from the database.
     */
    @Override
    public void checkIfCapmaignStartDate(String campaignCode) throws BusinessException {
        Campaign campaign = this.campaignDao.getCampaignByCampaignCode(campaignCode);
        if (campaign != null) {
            if (campaign.getCampaignStartDate().isAfter(LocalDate.now())) {
                throw new BusinessException(BusinessMessages.CampaignMessages.CAMPAIG_NOT_STARTED + campaignCode);
            }
        }
    }

    /**
     * This function checks if the campaign is expired or not
     *
     * @param campaignCode The campaign code that is used to get the campaign from the database.
     */
    @Override
    public void checkIfCapmaignFinishDate(String campaignCode) throws BusinessException {
        Campaign campaign = this.campaignDao.getCampaignByCampaignCode(campaignCode);
        if (campaign != null) {
            if (campaign.getCampaignFinishDate().isBefore(LocalDate.now())) {
                throw new BusinessException(BusinessMessages.CampaignMessages.CAMPAIGN_EXPIRED + campaignCode);
            }
        }
    }

    /**
     * This function checks if the campaign quantity is equal to the campaign quantity used
     *
     * @param campaignCode The campaign code that the user entered.
     */
    @Override
    public void checkIfCampaignCampaignQuantity(String campaignCode) throws BusinessException {
        Campaign campaign = this.campaignDao.getCampaignByCampaignCode(campaignCode);
        if (campaign != null) {
            if (campaign.getCampaignQuantity() == campaign.getCampaignQuantityUsed()) {
                throw new BusinessException(BusinessMessages.CampaignMessages.CAPIGN_CODE_NOT_AMOUNT + campaignCode);
            }
        }
    }

    /**
     * This function checks if a campaign code exists in the database
     *
     * @param campaignCode The campaign code to be checked.
     */
    @Override
    public void checkIfExistsByCampaignCode(String campaignCode) throws BusinessException {
        if (campaignCode.length() == 7) {
            if (!this.campaignDao.existsByCampaignCode(campaignCode)) {
                throw new BusinessException(BusinessMessages.CampaignMessages.CAPIGN_CODE_NOT_FOUNT + campaignCode);
            }
        }
    }
}


