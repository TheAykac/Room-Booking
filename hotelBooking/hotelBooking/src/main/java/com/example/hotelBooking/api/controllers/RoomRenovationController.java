package com.example.hotelBooking.api.controllers;

import com.example.hotelBooking.business.abstracts.RoomRenovationService;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.entity.dtos.roomRenovationDtos.RoomRenovationListDto;
import com.example.hotelBooking.entity.requests.roomRenovationRequests.CreateRoomRenovation;
import com.example.hotelBooking.entity.requests.roomRenovationRequests.DeleteRoomRenovationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/roomRenovation")
public class RoomRenovationController {

    // This is a constructor injection.
    private final RoomRenovationService roomRenovationService;

    @Autowired
    public RoomRenovationController(RoomRenovationService roomRenovationService) {
        this.roomRenovationService = roomRenovationService;
    }

    // This is a controller class.
    @PostMapping("/add")
    public Result add(CreateRoomRenovation createRoomRenovation) throws BusinessException {
        return this.roomRenovationService.add(createRoomRenovation);
    }

    @DeleteMapping("/delete")
    public Result delete(DeleteRoomRenovationRequest deleteRoomRenovationRequest) throws BusinessException {
        return this.roomRenovationService.delete(deleteRoomRenovationRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<RoomRenovationListDto>> getAll() throws BusinessException {
        return this.roomRenovationService.getAll();
    }
}
