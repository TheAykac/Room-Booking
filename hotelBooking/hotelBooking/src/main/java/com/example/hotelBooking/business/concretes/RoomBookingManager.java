package com.example.hotelBooking.business.concretes;

import com.example.hotelBooking.business.abstracts.RoomBookingService;
import com.example.hotelBooking.business.abstracts.RoomRenovationService;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.mapping.ModelMapperService;
import com.example.hotelBooking.core.utilities.messages.BusinessMessages;
import com.example.hotelBooking.core.utilities.result.*;
import com.example.hotelBooking.dataAccess.RoomBokingDao;
import com.example.hotelBooking.dataAccess.RoomDao;
import com.example.hotelBooking.entity.concretes.Room;
import com.example.hotelBooking.entity.concretes.RoomBooking;
import com.example.hotelBooking.entity.dtos.roomBookingDtos.RoomBookingListDto;
import com.example.hotelBooking.entity.requests.roomBookingRequests.CreateRoomBookingRequest;
import com.example.hotelBooking.entity.requests.roomBookingRequests.DeleteRoomBookingRequests;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoomBookingManager implements RoomBookingService {

    // This is constructor injection.
    private final RoomBokingDao roomBokingDao;
    private final RoomDao roomDao;
    private final RoomRenovationService roomRenovationService;
    private final ModelMapperService modelMapperService;


    @Autowired
    public RoomBookingManager(RoomBokingDao roomBokingDao, RoomDao roomDao, RoomRenovationService roomRenovationService, ModelMapperService modelMapperService) {
        this.roomBokingDao = roomBokingDao;
        this.roomDao = roomDao;
        this.roomRenovationService = roomRenovationService;
        this.modelMapperService = modelMapperService;

    }

    /**
     * It adds a new room booking.
     *
     * @param createRoomBookingRequest This is the request object that comes from the client.
     * @return Result
     */
    @Override
    public Result add(CreateRoomBookingRequest createRoomBookingRequest) throws BusinessException {
        try {
            checkAllCommonCrateValidation(createRoomBookingRequest);
            this.roomRenovationService.checkIfRoomId(createRoomBookingRequest.getRoomId());
            Room room = this.roomDao.getById(createRoomBookingRequest.getRoomId());
            RoomBooking roomBooking = this.modelMapperService.forRequest().map(createRoomBookingRequest, RoomBooking.class);
            roomBooking.setTotalPrice(calculateRoomBookingTotalDayPrice(createRoomBookingRequest.getStartDate(), createRoomBookingRequest.getFinishDate(), room.getDailyPrice()));

            this.roomBokingDao.save(roomBooking);
            log.info(BusinessMessages.LogMessages.ADD_OPERATINON_WORK + "RoomBookingManager -> Add Operation");
            return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.ADD_OPERATINON_NOT_WORK + "RoomBookingManager -> Add Operation");
            return new ErrorResult(e.getMessage());
        }

    }

    /**
     * It deletes a room booking from the database.
     *
     * @param deleteRoomBookingRequests This is the request object that is sent from the client.
     * @return Result
     */
    @Override
    public Result delete(DeleteRoomBookingRequests deleteRoomBookingRequests) throws BusinessException {
        try {
            checkIfNotRoomBokingIdExists(deleteRoomBookingRequests.getId());
            RoomBooking roomBooking = this.modelMapperService.forRequest().map(deleteRoomBookingRequests, RoomBooking.class);
            this.roomBokingDao.deleteById(deleteRoomBookingRequests.getId());
            log.info(BusinessMessages.LogMessages.DELETE_FROM_DATABASE + "RoomBookingManager -> Delete Operation");
            return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + deleteRoomBookingRequests.getId());
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.GET_LIST_NOT_WORK + "RoomBookingManager -> Delete Operation");
            return new ErrorResult(e.getMessage());
        }
    }

    /**
     * It gets all the room bookings from the database and maps them to a list of RoomBookingListDto objects.
     *
     * @return A list of room bookings.
     */
    @Override
    public DataResult<List<RoomBookingListDto>> getAll() throws BusinessException {
    /**
     * It gets the room booking by id.
     *
     * @param roomBookinId The id of the room booking to be retrieved.
     * @return A DataResult<RoomBookingListDto> object.
     */
        List<RoomBooking> roomBookings = this.roomBokingDao.findAll();
        List<RoomBookingListDto> roomBookingListDtos = roomBookings.stream().map(roomBooking -> this.modelMapperService.forDto().map(roomBooking, RoomBookingListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(roomBookingListDtos, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public DataResult<RoomBookingListDto> getById(int roomBookinId) throws BusinessException {
        RoomBooking roomBooking = this.roomBokingDao.getById(roomBookinId);
        RoomBookingListDto roomBookingListDto = this.modelMapperService.forDto().map(roomBooking, RoomBookingListDto.class);
        return new SuccessDataResult<>(roomBookingListDto, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * It gets all the room bookings by room id.
     *
     * @param roomId The id of the room to be searched.
     * @return A list of room bookings.
     */
    @Override
    public DataResult<List<RoomBookingListDto>> getAllByRoomBooking_RoomId(int roomId) throws BusinessException {
        List<RoomBooking> roomBookings = this.roomBokingDao.getAllByRoom_Id(roomId);
        List<RoomBookingListDto> roomBookingListDtos = roomBookings.stream().map(roomBooking -> this.modelMapperService.forDto().map(roomBooking, RoomBookingListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(roomBookingListDtos, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    // Getting all the room bookings by customer id.
    @Override
    public DataResult<List<RoomBookingListDto>> getAllByCustomer_Id(int customerId) throws BusinessException {
        List<RoomBooking> roomBookings = this.roomBokingDao.getAllByCustomer_UserId(customerId);
        List<RoomBookingListDto> roomBookingListDtos = roomBookings.stream().map(roomBooking -> this.modelMapperService.forDto().map(roomBooking, RoomBookingListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(roomBookingListDtos, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * This function checks if the room booking id exists in the database
     *
     * @param id The id of the room booking to be checked.
     */
    @Override
    public void checkIfNotRoomBokingIdExists(int id) throws BusinessException {
        log.info(BusinessMessages.LogMessages.CHECKING_DB);
    // It checks if the finish date is before the start date.
        if (!this.roomBokingDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.RoomBooking.ROOMBOOKING_ID_NOT_FOUND + id);
        }
    }

    @Override
    public void checkIfFisrtDateBeforeSecondDate(LocalDate startDate, LocalDate finishDate) throws BusinessException {
        if (finishDate.isBefore(startDate) || startDate.equals(finishDate)) {
            throw new BusinessException(BusinessMessages.RoomBooking.FINISH_DATE_CANNOT_BEFORE_START_DATE);
        }
    }

   /**
     * > This function returns the number of days between two dates
     *
     * @param startDate The start date of the booking
     * @param finishDate The date the user wants to finish their booking.
     * @return The number of days between the start date and the finish date.
     */
     @Override
    public int getTotalDaysForBooking(LocalDate startDate, LocalDate finishDate) throws BusinessException {
        return (int) ChronoUnit.DAYS.between(startDate, finishDate);
    }

    /**
     * > Calculate the total price of a room booking, given the start and finish dates, and the daily price
     *
     * @param startDate The date the booking starts
     * @param finishDate The date the booking finishes.
     * @param dailyPrice the price of the room per day
     * @return The total price of the booking.
     */
    @Override
    public double calculateRoomBookingTotalDayPrice(LocalDate startDate, LocalDate finishDate, double dailyPrice) throws BusinessException {
        int totalDays = getTotalDaysForBooking(startDate, finishDate);
        double result = totalDays * dailyPrice;
        return result;
    /**
     * This function checks if the room booking id exists in the database
     *
     * @param id The id of the room booking to be checked.
     */
    }

    /**
     * It checks if the room is already booked for the given dates.
     *
     * @param createRoomBookingRequest The request object that contains the parameters for the request.
     */
    @Override
    public void checkAllCommonCrateValidation(CreateRoomBookingRequest createRoomBookingRequest) throws BusinessException {
        checkIfFisrtDateBeforeSecondDate(createRoomBookingRequest.getStartDate(), createRoomBookingRequest.getFinishDate());
        checkIfRoomAlreadyBookingForCreate(createRoomBookingRequest.getRoomId(), createRoomBookingRequest.getStartDate(), createRoomBookingRequest.getFinishDate());
    }

    /**
     * This function checks if the room booking id exists in the database
     *
     * @param id The id of the room booking to be checked.
     */
    @Override
    public void checkIfRoomBookingIdExists(int id) throws BusinessException {
        log.info(BusinessMessages.LogMessages.CHECKING_DB);
        if (!this.roomBokingDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.RoomBooking.ROOMBOOKING_ID_NOT_FOUND + id);
        }
    }

    /**
     * If the start date of the room booking is after the start date of the new booking and the finish date of the room
     * booking is before the finish date of the new booking, or if the start date of the room booking is equal to the start
     * date of the new booking or the finish date of the room booking is equal to the finish date of the new booking, then
     * throw an exception
     *
     * @param roomBooking the room booking that is being checked
     * @param startDate the start date of the booking
     * @param finishDate The date the user wants to finish booking the room.
     */
    private void checkIfRoomAlreadyBookingBetweenStartAndFinishDates(RoomBooking roomBooking, LocalDate startDate, LocalDate finishDate) throws BusinessException {
        if ((roomBooking.getStartDate().isAfter(startDate) && (roomBooking.getFinishDate().isBefore(finishDate))) || (roomBooking.getStartDate().equals(startDate) || (roomBooking.getFinishDate().equals(finishDate)))) {
            throw new BusinessException(BusinessMessages.RoomBooking.ROOM_ALREADY_BOOKING_ENTERED_DATES + "startDate: " + startDate + "finishDate: " + finishDate);
        }
    }
    /**
     * > If the room booking start date is before the entered date and the room booking finish date is after the entered
     * date, then throw a business exception
     *
     * @param roomBooking The room booking object that is being checked.
     * @param enteredDate The date that the user has entered.
     */
    private void checkIfRoomAlreadyBookingOnTheEnteredDate(RoomBooking roomBooking, LocalDate enteredDate) throws BusinessException {
        if (roomBooking.getStartDate().isBefore(enteredDate) && (roomBooking.getFinishDate().isAfter(enteredDate))) {
            throw new BusinessException(BusinessMessages.RoomBooking.ROOM_ALREADY_BOOKING_ENTERED_DATES + enteredDate);
        }
    }
    /**
     * It checks if the room is already booked for the given dates.
     *
     * @param roomId the id of the room that is being booked
     * @param startDate the date from which the room is booked
     * @param finishDate the date when the room is no longer booked
     */
    @Override
    public void checkIfRoomAlreadyBookingForCreate(int roomId, LocalDate startDate, LocalDate finishDate) throws BusinessException {
        List<RoomBooking> roomBookings = this.roomBokingDao.getAllByRoom_Id(roomId);
        if (roomBookings != null) {
            for (RoomBooking roomBooking : roomBookings) {
                checkIfRoomAlreadyBookingOnTheEnteredDate(roomBooking, startDate);
                checkIfRoomAlreadyBookingOnTheEnteredDate(roomBooking, finishDate);
                checkIfRoomAlreadyBookingBetweenStartAndFinishDates(roomBooking, startDate, finishDate);

            }
        }
    }

}
