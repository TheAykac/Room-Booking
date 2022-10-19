package com.example.hotelBooking.business.concretes;

import com.example.hotelBooking.business.abstracts.CustomerService;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.mapping.ModelMapperService;
import com.example.hotelBooking.core.utilities.messages.BusinessMessages;
import com.example.hotelBooking.core.utilities.result.*;
import com.example.hotelBooking.dataAccess.CustomerDao;
import com.example.hotelBooking.entity.concretes.Customer;
import com.example.hotelBooking.entity.dtos.CustomerDtos.CustomerListDto;
import com.example.hotelBooking.entity.requests.customerRequests.CreateCustomerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerManger implements CustomerService {
    // This is a constructor injection.
    private final CustomerDao customerDao;
    private final ModelMapperService modelMapperService;

    @Autowired
    public CustomerManger(CustomerDao customerDao, ModelMapperService modelMapperService) {
        this.customerDao = customerDao;
        this.modelMapperService = modelMapperService;
    }

   /**
     * It adds a customer to the database.
     *
     * @param createCustomerRequest This is the request object that is sent from the controller.
     * @return Result
     */
     @Override
    public Result add(CreateCustomerRequest createCustomerRequest) throws BusinessException {
        try {
            checkValidEmail(createCustomerRequest.getEmail());
            checkIfCustomerEmailExists(createCustomerRequest.getEmail());
            Customer customer = this.modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
            customer.setRole("CUSTOMER");
            this.customerDao.save(customer);
            getEmail(createCustomerRequest);
            log.info(BusinessMessages.LogMessages.ADD_OPERATINON_WORK + " CustomerMager -> Add Operation");
            return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
        } catch (Exception e) {
            log.info(BusinessMessages.LogMessages.ADD_OPERATINON_NOT_WORK + " CustomerMager -> Add Operation");
            return new ErrorResult(e.getMessage());
        }
    }

    /**
     * It deletes a customer from the database.
     *
     * @param id The id of the customer to be deleted.
     * @return A Result object.
     */
    @Override
    public Result deleteByCustomerId(int id) throws BusinessException {
        try {
            checkIfCustomerIdExists(id);
            this.customerDao.deleteById(id);
            log.info(BusinessMessages.LogMessages.DELETE_FROM_DATABASE + " CustomerMager -> Delete Operation");
            return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + id);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.DELETE_OPERATINON_NOT_WORK + " CustomerMager -> Delete Operation");
            return new ErrorResult(e.getMessage());
        }
    }

    /**
     * It gets all the customers from the database and returns them as a list.
     *
     * @return A list of CustomerListDto objects.
     */
    @Override
    public DataResult<List<CustomerListDto>> getAll() throws BusinessException {
        List<Customer> customers = this.customerDao.findAll();
        List<CustomerListDto> customerListDtos = customers.stream().map(customer -> this.modelMapperService.forDto().map(customer, CustomerListDto.class)).collect(Collectors.toList());
        log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + " CustomerMager -> GetAll Operation");
        return new SuccessDataResult<>(customerListDtos, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * This function checks if the customerId exists in the database
     *
     * @param customerId The customer id to check if it exists in the database.
     */
    @Override
    public void checkIfCustomerIdExists(int customerId) throws BusinessException {
        log.info(BusinessMessages.LogMessages.CHECKING_DB + " CustomerMager -> CheckIfCustomerIdExists Operation");
    /**
     * > This function returns a `DataResult` object which contains a `Customer` object and a `BusinessMessage` object
     *
     * @param customerId The id of the customer to be retrieved.
     * @return DataResult<Customer>
     */
        if (!this.customerDao.existsById(customerId)) {
            throw new BusinessException(BusinessMessages.CustomerMessages.CUSTOMER_ID_NOT_FOUND + customerId);
        }
    }

   /**
     * > It gets a customer by id and returns a CustomerListDto
     *
     * @param customerId The id of the customer to be retrieved.
     * @return DataResult<CustomerListDto>
     */
     @Override
    public DataResult<Customer> getCustomerById(int customerId) {
        return new SuccessDataResult<>(this.customerDao.getById(customerId), BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    @Override
    public DataResult<CustomerListDto> getById(int customerId) {
        Customer customer = this.customerDao.getById(customerId);
        CustomerListDto customerListDto = this.modelMapperService.forDto().map(customer, CustomerListDto.class);
        return new SuccessDataResult<>(customerListDto, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * > This function checks if a customer with the given email already exists in the database
     *
     * @param email The email to check if it exists in the database.
     */
    private void checkIfCustomerEmailExists(String email) throws BusinessException {
        log.info(BusinessMessages.LogMessages.CHECKING_DB + " CustomerMager -> CheckIfCustomerEmailExists Operation");
        if (this.customerDao.existsByEmail(email)) {
            throw new BusinessException(BusinessMessages.CustomerMessages.CUSTOMER_EMAIL_ALREAY_EXISTS + email);
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

    private void getEmail(CreateCustomerRequest createCustomerRequest) {
        Customer customer = this.customerDao.getByEmail(createCustomerRequest.getEmail());

    }
}
