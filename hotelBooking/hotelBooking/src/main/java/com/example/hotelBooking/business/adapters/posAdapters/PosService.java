package com.example.hotelBooking.business.adapters.posAdapters;

import com.example.hotelBooking.core.utilities.exceptions.BusinessException;

public interface PosService {

    boolean payment(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice) throws BusinessException;

}
