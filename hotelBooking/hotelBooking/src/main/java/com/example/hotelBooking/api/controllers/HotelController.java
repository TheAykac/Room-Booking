package com.example.hotelBooking.api.controllers;

import com.example.hotelBooking.business.abstracts.HotelService;
import com.example.hotelBooking.core.utilities.enums.HotelStatu;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.entity.dtos.hotelDtos.HotelListDto;
import com.example.hotelBooking.entity.requests.hotelRequests.CreateHotelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    // This is a constructor injection.
    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }
    // This is a controller class.
    @PostMapping("/add")
    public Result add(CreateHotelRequest createHotelRequest) throws BusinessException {
        return this.hotelService.add(createHotelRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(int id) throws BusinessException {
        return this.hotelService.delete(id);
    }


    @GetMapping("/getAll")
    public DataResult<List<HotelListDto>> getAll() throws BusinessException {
        return this.hotelService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<HotelListDto> getById(int hotelId) throws BusinessException {
        return this.hotelService.getById(hotelId);
    }

    @PutMapping("/updateHotelStatu")
    public Result updateHolteStatu(HotelStatu hotelStatu, int hotelId) throws BusinessException {
        return this.hotelService.updateHolteStatu(hotelStatu, hotelId);
    }


}
