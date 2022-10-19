package com.example.hotelBooking.business.abstracts;

import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.entity.concretes.Customer;
import com.example.hotelBooking.entity.dtos.CustomerDtos.CustomerListDto;
import com.example.hotelBooking.entity.requests.customerRequests.CreateCustomerRequest;

import java.util.List;

public interface CustomerService {
    // A service interface.
    Result add(CreateCustomerRequest createCustomerRequests) throws BusinessException;

    Result deleteByCustomerId(int id) throws BusinessException;

    DataResult<List<CustomerListDto>> getAll() throws BusinessException;

    DataResult<Customer> getCustomerById(int customerId);

    DataResult<CustomerListDto> getById(int customerId);

    void checkIfCustomerIdExists(int customerId) throws BusinessException;


}
