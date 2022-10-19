package com.example.hotelBooking.business.abstracts;

import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.entity.dtos.systemManagementDtos.SystemManagementListDtos;
import com.example.hotelBooking.entity.requests.systemManagementRequests.CreateSystemManagementRequest;

import java.util.List;

public interface SystemManagementService {

    // A service interface.
    Result add(CreateSystemManagementRequest createSystemManagementRequest) throws BusinessException;

    Result delete(int systemManagementId) throws BusinessException;

    DataResult<List<SystemManagementListDtos>> getAll() throws BusinessException;

    DataResult<SystemManagementListDtos> getById(int systemManagementId) throws BusinessException;

    void checkIfExistsById(int systemManagementId) throws BusinessException;


}
