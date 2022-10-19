package com.example.hotelBooking.api.controllers;

import com.example.hotelBooking.business.abstracts.RoomService;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.messages.BusinessMessages;
import com.example.hotelBooking.core.utilities.result.DataResult;
import com.example.hotelBooking.core.utilities.result.Result;
import com.example.hotelBooking.entity.dtos.roomDtos.RoomListDto;
import com.example.hotelBooking.entity.requests.roomRequests.CreateRoomRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/room")
@Slf4j
public class RoomController {
    // This is a constructor injection.
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // This is a controller class.
    @PostMapping("/add")
    public Result add(CreateRoomRequest createRoomRequest) throws BusinessException {
        return this.roomService.add(createRoomRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<RoomListDto>> getAll() throws Exception {
        log.info(BusinessMessages.LogMessages.ALL_LIST);
        return this.roomService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<RoomListDto> getById(int roomId) throws BusinessException {
        return this.roomService.getById(roomId);
    }

    @DeleteMapping("/delete")
    public Result delete(int id) throws BusinessException {
        return this.roomService.deleteRoomByID(id);
    }

    @GetMapping("/findByDailyPriceLessThenEqual")
    public DataResult<List<RoomListDto>> findByDailyPriceLessThenEqual(double dailyPrice) throws BusinessException {
        return this.roomService.findByDailyPriceLessThenEqual(dailyPrice);
    }

    @GetMapping("/getAllPagedRoom")
    public DataResult<List<RoomListDto>> getAllPagedRoom(int pageNo, int pageSize) throws BusinessException {
        return this.roomService.getAllPagedRoom(pageNo, pageSize);
    }

    @GetMapping("/getAllSortedRoom")
    public DataResult<List<RoomListDto>> getAllSortedRoom(int sort) throws BusinessException {
        return this.roomService.getAllSortedRoom(sort);
    }

    @GetMapping("/getAvailableAllRooms")
    public DataResult<List<RoomListDto>> getAvailableAllRooms(@Valid @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate startDate, @Valid @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate finishDate) throws BusinessException {
        return this.roomService.getAllAvailableRooms(startDate, finishDate);
    }


}
