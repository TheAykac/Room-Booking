package com.example.hotelBooking.api.controllers;

import com.example.hotelBooking.business.abstracts.InvoiceService;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.entity.dtos.invoiceDtos.InvoiceListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceCotroller {
    // A constructor injection.
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceCotroller(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // This is a controller class.
    @GetMapping("/getAll")
    public DataResult<List<InvoiceListDto>> getAll() {
        return this.invoiceService.getAll();
    }


    public DataResult<InvoiceListDto> getById(int invoiceId) throws BusinessException {
        return this.invoiceService.getById(invoiceId);
    }

    @GetMapping("/getInvociceByPaymetn_Id")
    public DataResult<InvoiceListDto> getInvociceByPaymetn_Id(int paymentId) throws BusinessException {
        return this.invoiceService.getInvociceByPaymetn_Id(paymentId);
    }

    @GetMapping("/getAllByRoomBooking_Id")
    public DataResult<List<InvoiceListDto>> getAllByRoomBooking_Id(int roomBookingId) throws BusinessException {
        return this.invoiceService.getAllByRoomBooking_Id(roomBookingId);
    }

    @GetMapping("/getAllByCustomer_Id")
    public DataResult<List<InvoiceListDto>> getAllByCustomer_Id(int customerId) throws BusinessException {
        return this.invoiceService.getAllByCustomer_Id(customerId);
    }
}
