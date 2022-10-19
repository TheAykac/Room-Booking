package com.example.hotelBooking.business.abstracts;

import com.example.hotelBooking.api.models.MakePayment;
import com.example.hotelBooking.business.concretes.CreditCardManager;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.entity.dtos.paymentDtos.PaymentListDto;

import java.util.List;

public interface PaymentService {
    // A service interface.

    Result makePayment(MakePayment makePayment, CreditCardManager.CardSaveInformation cardSaveInformation) throws BusinessException;

    DataResult<List<PaymentListDto>> getAll() throws BusinessException;

    DataResult<PaymentListDto> getById(int paymentId) throws BusinessException;

    void checkIfExistByPaymentId(int paymentId) throws BusinessException;


}
