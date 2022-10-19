package com.example.hotelBooking.business.abstracts;

import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.entity.dtos.roomRenovationDtos.RoomRenovationListDto;
import com.example.hotelBooking.entity.requests.roomRenovationRequests.CreateRoomRenovation;
import com.example.hotelBooking.entity.requests.roomRenovationRequests.DeleteRoomRenovationRequest;

import java.util.List;

public interface RoomRenovationService {
    // A service interface.
    Result add(CreateRoomRenovation createRoomRenovation) throws BusinessException;

    Result delete(DeleteRoomRenovationRequest deleteRoomRenovationRequest) throws BusinessException;

    DataResult<List<RoomRenovationListDto>> getAll() throws BusinessException;

    void checkIfRoomId(int roomId) throws BusinessException;
}
