package com.example.hotelBooking.business.concretes;

import com.example.hotelBooking.business.abstracts.SystemManagementService;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.mapping.ModelMapperService;
import com.example.hotelBooking.core.utilities.messages.BusinessMessages;
import com.example.hotelBooking.core.utilities.result.*;
import com.example.hotelBooking.dataAccess.SystemManagementDao;
import com.example.hotelBooking.entity.concretes.SystemManagement;
import com.example.hotelBooking.entity.dtos.systemManagementDtos.SystemManagementListDtos;
import com.example.hotelBooking.entity.requests.systemManagementRequests.CreateSystemManagementRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SystemManagementManager implements SystemManagementService {

    private final SystemManagementDao systemManagementDao;
    private final ModelMapperService modelMapperService;

    @Autowired
    public SystemManagementManager(SystemManagementDao systemManagementDao, ModelMapperService modelMapperService) {
        this.systemManagementDao = systemManagementDao;
        this.modelMapperService = modelMapperService;
    }

    @Override

    // This method is adding a new system management to the database.
    public Result add(CreateSystemManagementRequest createSystemManagementRequest) throws BusinessException {
        try {
            checkValidEmail(createSystemManagementRequest.getEmail());
            checkIfExistByEmail(createSystemManagementRequest.getEmail());
            SystemManagement systemManagement = this.modelMapperService.forRequest().map(createSystemManagementRequest, SystemManagement.class);
            systemManagement.setRole("SYSTEM_MANAGEMENT");
            this.systemManagementDao.save(systemManagement);
            log.info(BusinessMessages.LogMessages.ADD_OPERATINON_WORK + " SytemManagementManager -> Add Operation");
            return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.ADD_OPERATINON_NOT_WORK + " SytemManagementManager -> Add Operation");
            return new ErrorResult(e.getMessage());
        }

    }

    /**
     * It deletes a system management record from the database.
     *
     * @param systemManagementId The id of the systemManagement to be deleted.
     * @return A Result object.
     */
    @Override
    public Result delete(int systemManagementId) throws BusinessException {
        try {
            checkIfExistsById(systemManagementId);
            this.systemManagementDao.deleteById(systemManagementId);
            log.info(BusinessMessages.LogMessages.DELETE_OPERATINON_WORK + " SytemManagementManager -> Delete Operation");
            return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.DELETE_OPERATINON_NOT_WORK + " SytemManagementManager -> Delete Operation");
            return new ErrorResult(e.getMessage());
        }
    }

    /**
     * It gets all the system management data from the database and returns it as a list.
     *
     * @return A list of SystemManagementListDtos objects.
     */
    @Override
    public DataResult<List<SystemManagementListDtos>> getAll() throws BusinessException {
        List<SystemManagement> systemManagements = this.systemManagementDao.findAll();
        List<SystemManagementListDtos> systemManagementListDtos = systemManagements.stream().map(systemManagement -> this.modelMapperService.forDto().map(systemManagements, SystemManagementListDtos.class)).collect(Collectors.toList());
        log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + " SytemManagementManager -> GetAll Operation");
        return new SuccessDataResult<>(systemManagementListDtos, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * It gets the system management by id.
     *
     * @param systemManagementId The id of the systemManagement to be searched.
     * @return A DataResult<SystemManagementListDtos> object is being returned.
     */
    @Override
    public DataResult<SystemManagementListDtos> getById(int systemManagementId) throws BusinessException {
        SystemManagement systemManagement = this.systemManagementDao.getById(systemManagementId);
        SystemManagementListDtos systemManagementListDtos = this.modelMapperService.forDto().map(systemManagement, SystemManagementListDtos.class);
        log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + " SytemManagementManager -> GetById Operation");
        return new SuccessDataResult<>(systemManagementListDtos, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * > This function checks if the systemManagementId exists in the database
     *
     * @param systemManagementId The id of the systemManagement to be checked.
     */
    @Override
    public void checkIfExistsById(int systemManagementId) throws BusinessException {
        log.info(BusinessMessages.LogMessages.CHECKING_DB + " SytemManagementManager -> checkIfExistsById Operation");
        if (!this.systemManagementDao.existsById(systemManagementId)) {
            throw new BusinessException(BusinessMessages.SystemManagementMessages.SYTEM_ID_NOT_FOUND);
        }
    }

    /**
     * > This function checks if the email exists in the database
     *
     * @param email The email of the user to be checked.
     */
    private void checkIfExistByEmail(String email) throws BusinessException {
        log.info(BusinessMessages.LogMessages.CHECKING_DB + " SytemManagementManager -> checkIfExistByEmail Operation");
        if (this.systemManagementDao.existsByEmail(email)) {
            throw new BusinessException(BusinessMessages.User.USER_EMAIL_NOT_FOUND);
        }
    }

    // Checking if the email is valid or not.
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
