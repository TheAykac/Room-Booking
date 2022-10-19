package com.example.hotelBooking.api.controllers;

import com.example.hotelBooking.business.abstracts.CustomerService;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.entity.dtos.CustomerDtos.CustomerListDto;
import com.example.hotelBooking.entity.requests.customerRequests.CreateCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    // This is a constructor injection.
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    // This is a controller class.
    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCustomerRequest createCustomerRequest) throws BusinessException {
        return this.customerService.add(createCustomerRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<CustomerListDto>> getAll() throws BusinessException {
        return this.customerService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<CustomerListDto> getById(int customerId) throws BusinessException {
        return this.customerService.getById(customerId);
    }

    @DeleteMapping("/delete")
    public Result delete(@Valid @RequestBody int id) throws BusinessException {
        return this.customerService.deleteByCustomerId(id);
    }
}

