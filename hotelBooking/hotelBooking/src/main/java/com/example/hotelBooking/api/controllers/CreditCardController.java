package com.example.hotelBooking.api.controllers;

import com.example.hotelBooking.business.abstracts.CreditCardService;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.entity.dtos.creditCardDtos.CreditCardListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/creditCard")
public class CreditCardController {
    // This is a constructor injection.
    private final CreditCardService creditCardService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }
    // This is a controller class.
    @GetMapping("/getAll")
    public DataResult<List<CreditCardListDto>> getAll() throws BusinessException {
        return this.creditCardService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<CreditCardListDto> getById(int creditCarId) throws BusinessException {
        return this.creditCardService.getById(creditCarId);
    }

    @GetMapping("/getAllCreditCardByCustomer_Id")
    public DataResult<List<CreditCardListDto>> getAllCreditCardByCustomer_Id(int customerId) throws BusinessException {
        return this.creditCardService.getAllCreditCardByCustomer_Id(customerId);
    }
}
