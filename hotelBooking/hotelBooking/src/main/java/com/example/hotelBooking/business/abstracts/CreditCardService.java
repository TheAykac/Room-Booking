package com.example.hotelBooking.business.abstracts;

import com.example.hotelBooking.business.concretes.CreditCardManager;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.entity.dtos.creditCardDtos.CreditCardListDto;
import com.example.hotelBooking.entity.requests.creditCardRequests.CreateCreditCardRequest;

import java.util.List;

public interface CreditCardService {
    // A service interface.
    Result add(CreateCreditCardRequest createCreditCardRequest) throws BusinessException;

    DataResult<List<CreditCardListDto>> getAll() throws BusinessException;

    DataResult<CreditCardListDto> getById(int creditCarId) throws BusinessException;

    DataResult<List<CreditCardListDto>> getAllCreditCardByCustomer_Id(int customerId) throws BusinessException;

    void checkSaveInformationAndSaveCreditCard(CreateCreditCardRequest createCreditCardRequest, CreditCardManager.CardSaveInformation cardSaveInformation) throws BusinessException;

    void checkIfNotExistsByCustomer_Id(int customerId) throws BusinessException;


}
