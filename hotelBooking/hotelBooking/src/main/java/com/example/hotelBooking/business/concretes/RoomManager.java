package com.example.hotelBooking.business.concretes;

import com.example.hotelBooking.business.abstracts.HotelService;
import com.example.hotelBooking.business.abstracts.RoomBookingService;
import com.example.hotelBooking.business.abstracts.RoomService;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.mapping.ModelMapperService;
import com.example.hotelBooking.core.utilities.messages.BusinessMessages;
import com.example.hotelBooking.core.utilities.result.*;
import com.example.hotelBooking.dataAccess.RoomBokingDao;
import com.example.hotelBooking.dataAccess.RoomDao;
import com.example.hotelBooking.dataAccess.RoomRenovationDao;
import com.example.hotelBooking.entity.concretes.Room;
import com.example.hotelBooking.entity.concretes.RoomBooking;
import com.example.hotelBooking.entity.concretes.RoomRenovation;
import com.example.hotelBooking.entity.dtos.roomDtos.RoomListDto;
import com.example.hotelBooking.entity.requests.roomRequests.CreateRoomRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoomManager implements RoomService {

    private final RoomDao roomDao;
    private final RoomBookingService roomBookingService;
    private final ModelMapperService modelMapperService;
    private final RoomBokingDao roomBookingDao;

    private final HotelService hotelService;


    @Autowired
    public RoomManager(RoomDao roomDao, RoomBookingService roomBookingService, ModelMapperService modelMapperService, RoomBokingDao roomBookingDao, HotelService hotelService) {
        this.roomDao = roomDao;
        this.roomBookingService = roomBookingService;
        this.modelMapperService = modelMapperService;
        this.roomBookingDao = roomBookingDao;
        this.hotelService = hotelService;
    }

    /**
     * It adds a room to the database.
     *
     * @param createRoomRequest This is the request object that comes from the client.
     * @return Result
     */
    @Override
    public Result add(CreateRoomRequest createRoomRequest) throws BusinessException {

        try {
            Room room = this.modelMapperService.forRequest().map(createRoomRequest, Room.class);
            this.hotelService.checkIfHotelStatu(createRoomRequest.getHotelUserId());
            this.roomDao.save(room);
            log.info(BusinessMessages.LogMessages.ADD_OPERATINON_WORK + " RoomManager -> add operation");
            return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    /**
     * It deletes a room from the database.
     *
     * @param roomid The id of the room to be deleted.
     * @return Result object
     */
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.ADD_OPERATINON_NOT_WORK + " RoomManager -> add operation");
            return new ErrorResult(e.getMessage());
        }
    }

    @Override
    public Result deleteRoomByID(int roomid) throws BusinessException {
        try {
            checkIsRoomIdExists(roomid);
            this.roomDao.deleteById(roomid);
            log.info(BusinessMessages.LogMessages.DELETE_FROM_DATABASE + " RoomManager -> deleteRoomById operation");
            return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.DELETE_OPERATINON_NOT_WORK + " RoomManager -> deleteRoomById operation");
            return new ErrorResult(e.getMessage());
        }
    }

    /**
     * It gets all the rooms from the database and returns them as a list of RoomListDto objects.
     *
     * @return A list of RoomListDto objects.
     */
    @Override
    public DataResult<List<RoomListDto>> getAll() throws BusinessException {
        List<Room> rooms = this.roomDao.findAll();
        List<RoomListDto> roomListDtos = rooms.stream().map(room -> this.modelMapperService.forDto().map(room, RoomListDto.class)).collect(Collectors.toList());
        log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + " RoomManager -> getAll operation");
        return new SuccessDataResult<>(roomListDtos, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * It gets the room by id.
     *
     * @param roomId The id of the room to be searched.
     * @return DataResult<RoomListDto>
     */
    @Override
    public DataResult<RoomListDto> getById(int roomId) throws BusinessException {
        checkIsRoomIdExists(roomId);
        Room room = this.roomDao.getById(roomId);
        RoomListDto roomListDto = this.modelMapperService.forDto().map(room, RoomListDto.class);
        log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + " RoomManager -> getById operation");
        return new SuccessDataResult<>(roomListDto, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * It returns a list of rooms that have a daily price less than or equal to the given price.
     *
     * @param dailyPrice The price of the room to be searched.
     * @return A list of rooms that have a daily price less than or equal to the given price.
     */
    @Override
    public DataResult<List<RoomListDto>> findByDailyPriceLessThenEqual(double dailyPrice) {
    /**
     * It gets all rooms in a paged manner.
     *
     * @param pageNo The page number to be displayed.
     * @param pageSize The number of records to be returned in the page.
     * @return A list of rooms.
     */
        List<Room> rooms = this.roomDao.findByDailyPriceLessThanEqual(dailyPrice);
        List<RoomListDto> response = rooms.stream().map(room -> this.modelMapperService.forDto().map(room, RoomListDto.class)).collect(Collectors.toList());
        log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + " RoomManager -> findByDailyPriceLessThenEqual operation");
        return new SuccessDataResult<>(response, BusinessMessages.Room.ROOM_LISTED_BY_LESS_THEN_EQUAL + dailyPrice);
    }

    @Override
    public DataResult<List<RoomListDto>> getAllPagedRoom(int pageNo, int pageSize) throws BusinessException {
        checkIfPageNoAndPageSizeValid(pageNo, pageSize);
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Room> rooms = this.roomDao.findAll(pageable).getContent();
        List<RoomListDto> roomListDtos = rooms.stream().map(room -> this.modelMapperService.forDto().map(room, RoomListDto.class)).collect(Collectors.toList());
        log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + " RoomManager -> getAllPagedRoom operation");
        return new SuccessDataResult<>(roomListDtos, BusinessMessages.Room.ALL_ROOMS_PAGED);
    }

    /**
     * It returns all rooms sorted by the given parameter.
     *
     * @param sort 1 -> Sort by room name
     * @return A list of rooms sorted by the given parameter.
     */
    @Override
    public DataResult<List<RoomListDto>> getAllSortedRoom(int sort) throws BusinessException {
        Sort sort1 = selectSortedType(sort);
    /**
     * > This function checks if the roomid exists in the database
     *
     * @param roomid The id of the room to be checked.
     */
        List<Room> rooms = this.roomDao.findAll(sort1);
        List<RoomListDto> roomListDtos = rooms.stream().map(room -> this.modelMapperService.forDto().map(room, RoomListDto.class)).collect(Collectors.toList());
        log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + " RoomManager -> getAllSortedRoom operation");
        return new SuccessDataResult<>(roomListDtos, BusinessMessages.Room.ALL_ROOMS_SORTED);
    }

    @Override
    public void checkIsRoomIdExists(int roomid) throws BusinessException {
        if (!this.roomDao.existsById(roomid)) {
            log.info(BusinessMessages.LogMessages.CHECKING_DB + "RoomManager -> checkIsRoomIdExists operation");
            throw new BusinessException(BusinessMessages.Room.ROOM_ID_NOT_FOUND + roomid);
        }
    }

    /**
     * This function returns the daily price of a room given its id.
     *
     * @param roomId The id of the room to get the daily price for.
     * @return The daily price of the room.
     */
    @Override
    public double getDailyPriceByRoomId(int roomId) throws BusinessException {
        Room room = this.roomDao.getById(roomId);
        return room.getDailyPrice();
    }

    /**
     * It returns all the rooms that are available for the given date range.
     *
     * @param startDate The date the user wants to start booking.
     * @param finishDate The date the user wants to leave the hotel.
     * @return A list of rooms that are available on the entered dates.
     */
    @Override
    public DataResult<List<RoomListDto>> getAllAvailableRooms(LocalDate startDate, LocalDate finishDate) throws BusinessException {
        try {
            checkIfFisrtDateBeforeSecondDate(startDate, finishDate);
            List<Room> rooms = this.roomDao.findAll();
            List<Room> availableRooms = new ArrayList<>();
            int x = 0;
            for (Room room : rooms) {
                log.info(BusinessMessages.LogMessages.CHECK_OF_DATE + room.getId());
                List<RoomBooking> allBookings = this.roomBookingDao.getAllByRoom_Id(room.getId());
                for (RoomBooking booking : allBookings) {
                    if (checkIfRoomAlreadyBookingOnTheEnteredDate(booking, startDate) &&
                            checkIfRoomAlreadyBookingOnTheEnteredDate(booking, finishDate) &&
                            checkIfRoomAlreadyBookingBetweenStartAndFinishDates(booking, startDate, finishDate)){
                    } else {
                        x++;
                    }
                }
                if (x == 0) {
                    availableRooms.add(room);
                }
                x = 0;
            }
            List<RoomListDto> roomListDtos = availableRooms.stream().map(room -> this.modelMapperService.forDto().map(room, RoomListDto.class)).collect(Collectors.toList());
            log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + " Room Manager -> getAllAvailableRooms");
            return new SuccessDataResult<>(roomListDtos, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
        } catch (Exception e) {
            return new ErrorDataResult<>(e.getMessage());
        }
    }

    /**
     * If the start date of the room booking is after the start date of the new booking and the finish date of the room
     * booking is before the finish date of the new booking, or if the start date of the room booking is equal to the start
     * date of the new booking or the finish date of the room booking is equal to the finish date of the new booking, then
     * return false
     *
     * @param roomBooking The room booking that is being checked.
     * @param startDate The start date of the booking
     * @param finishDate The date the user wants to finish their booking
     * @return A boolean value.
     */
    private boolean checkIfRoomAlreadyBookingBetweenStartAndFinishDates(RoomBooking roomBooking, LocalDate startDate, LocalDate finishDate) throws BusinessException {
        if ((roomBooking.getStartDate().isAfter(startDate) && (roomBooking.getFinishDate().isBefore(finishDate))) || (roomBooking.getStartDate().equals(startDate) || (roomBooking.getFinishDate().equals(finishDate)))) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * > This function checks if the room is already booked on the entered date
     *
     * @param roomBooking The room booking object that is being checked.
     * @param enteredDate The date that the user has entered to check if the room is available.
     * @return A boolean value.
     */
    private boolean checkIfRoomAlreadyBookingOnTheEnteredDate(RoomBooking roomBooking, LocalDate enteredDate) throws BusinessException {
        if (roomBooking.getStartDate().isBefore(enteredDate) && (roomBooking.getFinishDate().isAfter(enteredDate))) {
            return false;
        } else {
            return true;
        }
    }



    /**
     * > This function checks if the pageNo and pageSize are valid
     *
     * @param pageNo The page number to be fetched.
     * @param pageSize The number of records to be fetched in a single request.
     */
    private void checkIfPageNoAndPageSizeValid(int pageNo, int pageSize) throws BusinessException {
        if (pageNo <= 0 || pageSize <= 0) {
            throw new BusinessException(BusinessMessages.Room.PAGE_NO_OR_PAGE_SIZE_NOT_VALID + "pageNO: " + pageNo + "PageSiz: " + pageSize);
        }
    }

    /**
     * Check if the first date is before the second date, if it is, throw an exception.
     *
     * @param startDate the date when the room is booked
     * @param finishDate the date when the room is to be vacated
     */
    private void checkIfFisrtDateBeforeSecondDate(LocalDate startDate, LocalDate finishDate) throws BusinessException {
        if (finishDate.isBefore(startDate) || startDate.equals(finishDate)) {
            throw new BusinessException(BusinessMessages.RoomBooking.FINISH_DATE_CANNOT_BEFORE_START_DATE);
        }
    }

    /**
     * If the sort parameter is 1, then sort by ascending dailyPrice, else if the sort parameter is 0, then sort by
     * descending dailyPrice, else sort by descending dailyPrice
     *
     * @param sort 0 for descending, 1 for ascending
     * @return A Sort object is being returned.
     */
    Sort selectSortedType(int sort) {
        if (sort == 1) {
            return Sort.by(Sort.Direction.ASC, "dailyPrice");
        } else if (sort == 0) {
            return Sort.by(Sort.Direction.DESC, "dailyPrice");
        } else {
            return Sort.by(Sort.Direction.DESC, "dailyPrice");
        }
    }


}
