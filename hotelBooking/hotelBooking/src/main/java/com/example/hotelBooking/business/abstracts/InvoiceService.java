package com.example.hotelBooking.business.abstracts;

import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.entity.dtos.invoiceDtos.InvoiceListDto;
import com.example.hotelBooking.entity.requests.invoiceRequest.CreateInvoiceRequest;

import java.util.List;

public interface InvoiceService {

    // A service interface.

    Result add(CreateInvoiceRequest createInvoiceRequest) throws BusinessException;

    DataResult<List<InvoiceListDto>> getAll();

    DataResult<InvoiceListDto> getById(int invoiceId) throws BusinessException;

    DataResult<InvoiceListDto> getInvociceByPaymetn_Id(int paymentId) throws BusinessException;

    DataResult<List<InvoiceListDto>> getAllByRoomBooking_Id(int roomBookingId) throws BusinessException;

    DataResult<List<InvoiceListDto>> getAllByCustomer_Id(int customerId) throws BusinessException;


    void checkIfNotExistsByCustomer_Id(int customerId) throws BusinessException;

    void checkIfnotExistsByRoomBooking_Id(int roomBookingId) throws BusinessException;

    void createAddInvoice(int roomBookingId, int paymentId) throws BusinessException;

    void checkIfNotExistsByPayment_Id(int paymentId) throws BusinessException;
}
