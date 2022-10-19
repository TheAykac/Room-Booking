package com.example.hotelBooking.api.controllers;

import com.example.hotelBooking.business.abstracts.SystemManagementService;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.entity.dtos.systemManagementDtos.SystemManagementListDtos;
import com.example.hotelBooking.entity.requests.systemManagementRequests.CreateSystemManagementRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/systemManagement")
public class SystemManagementController {
    // This is a constructor injection.
    private final SystemManagementService systemManagementService;

    @Autowired
    public SystemManagementController(SystemManagementService systemManagementService) {
        this.systemManagementService = systemManagementService;
    }

    // This is a controller class.
    @PostMapping("add")
    public Result add(CreateSystemManagementRequest createSystemManagementRequest) throws BusinessException {
        return this.systemManagementService.add(createSystemManagementRequest);
    }


    @DeleteMapping("delete")
    public Result delete(int systemManagementId) throws BusinessException {
        return this.systemManagementService.delete(systemManagementId);
    }

    @GetMapping("getAll")
    public DataResult<List<SystemManagementListDtos>> getAll() throws BusinessException {
        return this.systemManagementService.getAll();
    }

    @GetMapping("getById")
    public DataResult<SystemManagementListDtos> getById(int systemManagementId) throws BusinessException {
        return this.systemManagementService.getById(systemManagementId);
    }
}
