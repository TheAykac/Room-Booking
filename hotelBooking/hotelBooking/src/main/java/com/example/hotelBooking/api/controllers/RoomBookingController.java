package com.example.hotelBooking.api.controllers;

import com.example.hotelBooking.business.abstracts.RoomBookingService;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.entity.dtos.roomBookingDtos.RoomBookingListDto;
import com.example.hotelBooking.entity.requests.roomBookingRequests.CreateRoomBookingRequest;
import com.example.hotelBooking.entity.requests.roomBookingRequests.DeleteRoomBookingRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/roomBooking")
public class RoomBookingController {
    // This is a constructor injection.
    private final RoomBookingService roomBokingService;

    @Autowired
    public RoomBookingController(RoomBookingService roomBokingService) {
        this.roomBokingService = roomBokingService;
    }

    // This is a controller class.
    @PostMapping("/add")
    public Result add(@Valid @RequestBody CreateRoomBookingRequest createRoomBookingReqeuest) throws BusinessException {
        return this.roomBokingService.add(createRoomBookingReqeuest);
    }

    @GetMapping("/getAll")
    public DataResult<List<RoomBookingListDto>> getAll() throws BusinessException {
        return this.roomBokingService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<RoomBookingListDto> getById(int roomBookingId) throws BusinessException {
        return this.roomBokingService.getById(roomBookingId);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteRoomBookingRequests deleteRoomBookingRequests) throws BusinessException {
        return this.roomBokingService.delete(deleteRoomBookingRequests);
    }

    @GetMapping("/getAllByRoomBooking_RoomId")
    public DataResult<List<RoomBookingListDto>> getAllByRoomBooking_RoomId(int roomId) throws BusinessException {
        return this.roomBokingService.getAllByRoomBooking_RoomId(roomId);
    }

    @GetMapping("/getAllByCustomer_Id")
    public DataResult<List<RoomBookingListDto>> getAllByCustomer_Id(int customerId) throws BusinessException {
        return this.roomBokingService.getAllByCustomer_Id(customerId);
    }
}
