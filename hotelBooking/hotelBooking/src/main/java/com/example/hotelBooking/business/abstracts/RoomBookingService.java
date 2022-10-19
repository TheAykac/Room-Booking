package com.example.hotelBooking.business.abstracts;

import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.entity.dtos.roomBookingDtos.RoomBookingListDto;
import com.example.hotelBooking.entity.requests.roomBookingRequests.CreateRoomBookingRequest;
import com.example.hotelBooking.entity.requests.roomBookingRequests.DeleteRoomBookingRequests;

import java.time.LocalDate;
import java.util.List;

public interface RoomBookingService {

    // A service interface.
    Result add(CreateRoomBookingRequest createRoomBookingReqeuest) throws BusinessException;

    Result delete(DeleteRoomBookingRequests deleteRoomBookingRequests) throws BusinessException;

    DataResult<List<RoomBookingListDto>> getAll() throws BusinessException;

    DataResult<RoomBookingListDto> getById(int roomBookinId) throws BusinessException;

    DataResult<List<RoomBookingListDto>> getAllByRoomBooking_RoomId(int roomId) throws BusinessException;

    DataResult<List<RoomBookingListDto>> getAllByCustomer_Id(int customerId) throws BusinessException;


    void checkIfNotRoomBokingIdExists(int id) throws BusinessException;

    void checkIfFisrtDateBeforeSecondDate(LocalDate startDate, LocalDate finishDate) throws BusinessException;

    int getTotalDaysForBooking(LocalDate startDate, LocalDate finishDate) throws BusinessException;

    void checkAllCommonCrateValidation(CreateRoomBookingRequest createRoomBookingRequest) throws BusinessException;

    void checkIfRoomBookingIdExists(int id) throws BusinessException;

    void checkIfRoomAlreadyBookingForCreate(int roomId, LocalDate startDate, LocalDate finishDate) throws BusinessException;

    double calculateRoomBookingTotalDayPrice(LocalDate startDate, LocalDate finishDate, double dailyPrice) throws BusinessException;


}
