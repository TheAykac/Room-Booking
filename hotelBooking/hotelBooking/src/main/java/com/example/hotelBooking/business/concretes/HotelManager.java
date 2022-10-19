package com.example.hotelBooking.business.concretes;

import com.example.hotelBooking.business.abstracts.HotelService;
import com.example.hotelBooking.core.utilities.enums.HotelStatu;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.mapping.ModelMapperService;
import com.example.hotelBooking.core.utilities.messages.BusinessMessages;
import com.example.hotelBooking.core.utilities.result.*;
import com.example.hotelBooking.dataAccess.HotelDao;
import com.example.hotelBooking.entity.concretes.Hotel;
import com.example.hotelBooking.entity.dtos.hotelDtos.HotelListDto;
import com.example.hotelBooking.entity.requests.hotelRequests.CreateHotelRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HotelManager implements HotelService {

    // This is constructor injection.
    private final HotelDao hotelDao;
    private final ModelMapperService modelMapperService;

    @Autowired
    public HotelManager(HotelDao hotelDao, ModelMapperService modelMapperService) {
        this.hotelDao = hotelDao;
        this.modelMapperService = modelMapperService;
    }

    // This method is adding a new hotel to the database.
    @Override
    public Result add(CreateHotelRequest createHotelRequest) throws BusinessException {
        try {
            checkValidEmail(createHotelRequest.getHotelEmail());
            checkByExistsEmail(createHotelRequest.getHotelEmail());
            Hotel hotel = this.modelMapperService.forRequest().map(createHotelRequest, Hotel.class);
            hotel.setRole("HOTEL_MANAGEMENT");
            hotel.setHotelStatu(HotelStatu.WAIT_APPROVAL);
            this.hotelDao.save(hotel);
            Hotel hote1 = this.hotelDao.findByEmail(createHotelRequest.getHotelEmail());
            log.info(BusinessMessages.LogMessages.ADD_OPERATINON_WORK + " HotelManager -> Add Operation");
            return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.ADD_OPERATINON_NOT_WORK + " HotelManager -> Add Operation");
            return new ErrorResult(e.getMessage());
        }
    }

    // This method is deleting a hotel from the database.
    @Override
    public Result delete(int id) throws BusinessException {
        try {
            this.hotelDao.deleteById(id);
            log.info(BusinessMessages.LogMessages.DELETE_FROM_DATABASE + " HoteManager -> Delete Operation");
            return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.DELETE_OPERATINON_NOT_WORK + " HoteManager -> Delete Operation");
            return new ErrorResult(e.getMessage());
        }
    }

    /**
     * It gets all the hotels from the database and returns them as a list.
     *
     * @return A list of HotelListDto objects.
     */
    @Override
    public DataResult<List<HotelListDto>> getAll() throws BusinessException {
        List<Hotel> hotels = this.hotelDao.findAll();
        List<HotelListDto> hotelListDtos = hotels.stream().map(hotel -> this.modelMapperService.forDto().map(hotel, HotelListDto.class)).collect(Collectors.toList());
        log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + " HotelManager -> GetAll Operation ");
        return new SuccessDataResult<>(hotelListDtos, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * > This function gets a hotel by its id and returns it as a HotelListDto
     *
     * @param hotelId The id of the hotel to be searched.
     * @return A DataResult object with a HotelListDto object inside.
     */
    @Override
    public DataResult<HotelListDto> getById(int hotelId) throws BusinessException {
        Hotel hotel = this.hotelDao.getById(hotelId);
        HotelListDto hotelListDto = this.modelMapperService.forDto().map(hotel, HotelListDto.class);
        return new SuccessDataResult<>(hotelListDto, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * It updates the status of a hotel.
     *
     * @param hotelStatu the status of the hotel
     * @param hotelId The id of the hotel to be updated.
     * @return A SuccessResult object.
     */
    @Override
    /**
     * It checks if the hotel is approved.
     *
     * @param userId The user ID of the hotel.
     */
    public Result updateHolteStatu(HotelStatu hotelStatu, int hotelId) throws BusinessException {
        Hotel hotel = this.hotelDao.getById(hotelId);
        hotel.setHotelStatu(hotelStatu);
        this.hotelDao.save(hotel);
        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_UPDATED_SUCCESSFULLY);
    }

    @Override
    public void checkIfHotelStatu(int userId) throws BusinessException {
        Hotel hotel = this.hotelDao.getById(userId);
        if (!hotel.getHotelStatu().equals(HotelStatu.APPROVALED)) {
            throw new BusinessException(BusinessMessages.Hotel.HOTEL_NOT_CONFIRM + userId);
        }
    }

    /**
     * It checks if the email exists in the database.
     *
     * @param email The email address of the user to be checked.
     */
    private void checkByExistsEmail(String email) throws BusinessException {
        log.info(BusinessMessages.LogMessages.CHECKING_DB + "HotelManager -> CheckByExistsEmail Operation");
        if (this.hotelDao.existsByEmail(email)) {
            throw new BusinessException(BusinessMessages.User.USER_EMAIL_NOT_FOUND + email);
        }
    }

    /**
     * It checks if the email is valid or not
     *
     * @param email The email address to be validated.
     */
    private void checkValidEmail(String email) throws BusinessException {
        boolean isEmail;
        String emailRegex = "^[a-zA-Z]+[a-zA-Z0-9]*[- . + _]?[a-zA-Z0-9]+[@]{1}[a-z0-9]+[.]{1}[a-z]+[.]?[a-z]+$";
        Pattern patternObject = Pattern.compile(emailRegex);
        if (email == null) {
            isEmail = false;
        }
        Matcher matcherObject = patternObject.matcher(email);
        isEmail = matcherObject.matches();
        if (!isEmail) {
            throw new BusinessException(BusinessMessages.CustomerMessages.CUSTOMER_EMAIL_INVALID + email);
        }
    }


}
