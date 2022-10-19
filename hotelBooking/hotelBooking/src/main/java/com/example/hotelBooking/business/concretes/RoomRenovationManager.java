package com.example.hotelBooking.business.concretes;

import com.example.hotelBooking.business.abstracts.RoomRenovationService;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.mapping.ModelMapperService;
import com.example.hotelBooking.core.utilities.messages.BusinessMessages;
import com.example.hotelBooking.core.utilities.result.*;
import com.example.hotelBooking.dataAccess.RoomRenovationDao;
import com.example.hotelBooking.entity.concretes.RoomRenovation;
import com.example.hotelBooking.entity.dtos.roomRenovationDtos.RoomRenovationListDto;
import com.example.hotelBooking.entity.requests.roomRenovationRequests.CreateRoomRenovation;
import com.example.hotelBooking.entity.requests.roomRenovationRequests.DeleteRoomRenovationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoomRenovationManager implements RoomRenovationService {

    // This is the constructor of the class.
    private final RoomRenovationDao roomRenovationDao;

    private final ModelMapperService modelMapperService;


    @Autowired
    public RoomRenovationManager(RoomRenovationDao roomRenovationDao, ModelMapperService modelMapperService) {
        this.roomRenovationDao = roomRenovationDao;
        this.modelMapperService = modelMapperService;
    }

    /**
     * It adds a new room renovation to the database.
     *
     * @param createRoomRenovation The object that will be added to the database.
     * @return Result
     */
    @Override
    public Result add(CreateRoomRenovation createRoomRenovation) throws BusinessException {
        try {
            RoomRenovation roomRenovation = this.modelMapperService.forDto().map(createRoomRenovation, RoomRenovation.class);
            this.roomRenovationDao.save(roomRenovation);
            log.info(BusinessMessages.LogMessages.ADD_OPERATINON_WORK + " RoomRenovationManger -> Add Operation");
            return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.ADD_OPERATINON_NOT_WORK + " RoomRenovationManger -> Add Operation");
            return new ErrorResult(e.getMessage());
        }
    }

    /**
     * It deletes a room renovation by id.
     *
     * @param deleteRoomRenovationRequest This is the request object that is sent from the controller.
     * @return Result
     */
    @Override
    public Result delete(DeleteRoomRenovationRequest deleteRoomRenovationRequest) throws BusinessException {
        try {
            checkIfRoomRenovationById(deleteRoomRenovationRequest.getRoomId());
            RoomRenovation roomRenovation = this.modelMapperService.forDto().map(deleteRoomRenovationRequest, RoomRenovation.class);
            this.roomRenovationDao.deleteById(roomRenovation.getId());
            log.info(BusinessMessages.LogMessages.DELETE_OPERATINON_WORK + " RoomRenovationManger -> Delete Operation");
            return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + deleteRoomRenovationRequest.getRoomId());
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.DELETE_OPERATINON_NOT_WORK + " RoomRenovationManger -> Delete Operation");
            return new ErrorResult(e.getMessage());
        }
    }

    /**
     * It gets all the room renovations from the database.
     *
     * @return A list of RoomRenovationListDto objects.
     */
    @Override
    public DataResult<List<RoomRenovationListDto>> getAll() throws BusinessException {
        try {
            List<RoomRenovation> roomRenovations = this.roomRenovationDao.findAll();
            List<RoomRenovationListDto> roomRenovationListDtos = roomRenovations
                    .stream()
                    .map(roomRenovation -> this.modelMapperService.forDto().map(roomRenovation, RoomRenovationListDto.class))
                    .collect(Collectors.toList());
            log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + " RoomRenovationManger -> GetAll Operation");
            return new SuccessDataResult<>(roomRenovationListDtos, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.GET_LIST_NOT_WORK + " RoomRenovationManger -> GetAll Operation");
            return new ErrorDataResult<>(e.getMessage());
        }
    }

    /**
     * This function checks if the roomId exists in the database. If it does, it throws an exception
     *
     * @param roomId The roomId of the room that is being renovated.
     */
    @Override
    public void checkIfRoomId(int roomId) throws BusinessException {
        if (this.roomRenovationDao.existsByRoomId(roomId)) {
            throw new BusinessException(BusinessMessages.RoomRenovation.ROOMRENOVATION_ID_EXISTS_ALREADY + roomId);
        }
    }

    /**
     * > This function checks if a room renovation exists in the database by its id
     *
     * @param id The id of the room renovation to be checked.
     */
    private void checkIfRoomRenovationById(int id) throws BusinessException {
        log.info(BusinessMessages.LogMessages.CHECKING_DB + " RoomRenovationManger -> CheckIfRoomRenovationById Operation");
        if (!this.roomRenovationDao.existsByRoomId(id)) {
            throw new BusinessException(BusinessMessages.RoomRenovation.ROOMRENOVATION_ID_NOT_FOUND + id);
        }
    }


}
