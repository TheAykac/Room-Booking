package com.example.hotelBooking.business.abstracts;

import com.example.hotelBooking.core.utilities.enums.HotelStatu;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.entity.dtos.hotelDtos.HotelListDto;
import com.example.hotelBooking.entity.requests.hotelRequests.CreateHotelRequest;

import java.util.List;

public interface HotelService {
// A service interface.


    Result add(CreateHotelRequest createHotelRequest) throws BusinessException;

    Result delete(int id) throws BusinessException;


    Result updateHolteStatu(HotelStatu hotelStatu, int hotelId) throws BusinessException;

    DataResult<List<HotelListDto>> getAll() throws BusinessException;

    DataResult<HotelListDto> getById(int hotelId) throws BusinessException;

    void checkIfHotelStatu(int userId) throws BusinessException;

}
