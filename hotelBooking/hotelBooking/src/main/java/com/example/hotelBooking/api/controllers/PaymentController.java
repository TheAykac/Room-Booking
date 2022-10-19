package com.example.hotelBooking.api.controllers;

import com.example.hotelBooking.api.models.MakePayment;
import com.example.hotelBooking.business.abstracts.PaymentService;
import com.example.hotelBooking.business.concretes.CreditCardManager;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.entity.dtos.paymentDtos.PaymentListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    // A constructor injection.
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // This is a controller class.
    @GetMapping("/getAll")
    public DataResult<List<PaymentListDto>> getAll() throws BusinessException {
        return this.paymentService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<PaymentListDto> getById(int paymentId) throws BusinessException {
        return this.paymentService.getById(paymentId);
    }

    @PostMapping("/makePaymnet")
    public Result makePayment(@RequestBody MakePayment makePayment, CreditCardManager.CardSaveInformation cardSaveInformation) throws BusinessException {
        return this.paymentService.makePayment(makePayment, cardSaveInformation);
    }
}
