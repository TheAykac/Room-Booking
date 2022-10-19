package com.example.hotelBooking.business.abstracts;

import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.entity.dtos.roomDtos.RoomListDto;
import com.example.hotelBooking.entity.requests.roomRequests.CreateRoomRequest;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    // A service interface.
    Result add(CreateRoomRequest createRoomRequest) throws BusinessException;

    Result deleteRoomByID(int roomId) throws BusinessException;


    DataResult<List<RoomListDto>> getAll() throws BusinessException;

    DataResult<RoomListDto> getById(int roomId) throws BusinessException;

    DataResult<List<RoomListDto>> findByDailyPriceLessThenEqual(double dailyPrice) throws BusinessException;

    DataResult<List<RoomListDto>> getAllPagedRoom(int pageNo, int pageSize) throws BusinessException;

    DataResult<List<RoomListDto>> getAllSortedRoom(int sort) throws BusinessException;

    DataResult<List<RoomListDto>> getAllAvailableRooms(LocalDate startDate, LocalDate finishDate)throws BusinessException;

    void checkIsRoomIdExists(int roomId) throws BusinessException;

    double getDailyPriceByRoomId(int roomId) throws BusinessException;


}
