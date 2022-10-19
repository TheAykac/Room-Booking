package com.example.hotelBooking.business.concretes;

import com.example.hotelBooking.business.abstracts.CreditCardService;
import com.example.hotelBooking.business.abstracts.CustomerService;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.mapping.ModelMapperService;
import com.example.hotelBooking.core.utilities.messages.BusinessMessages;
import com.example.hotelBooking.core.utilities.result.*;
import com.example.hotelBooking.dataAccess.CreditCardDao;
import com.example.hotelBooking.entity.concretes.CreditCard;
import com.example.hotelBooking.entity.dtos.creditCardDtos.CreditCardListDto;
import com.example.hotelBooking.entity.requests.creditCardRequests.CreateCreditCardRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CreditCardManager implements CreditCardService {
    // This is a constructor injection.
    private final CreditCardDao creditCardDao;
    private final ModelMapperService modelMapperService;
    private final CustomerService customerService;

    @Autowired
    public CreditCardManager(CreditCardDao creditCardDao, ModelMapperService modelMapperService, CustomerService customerService) {
        this.creditCardDao = creditCardDao;
        this.modelMapperService = modelMapperService;
        this.customerService = customerService;
    }

    // This method is adding a new credit card to the database.
    @Override
    public Result add(CreateCreditCardRequest createCreditCardRequest) throws BusinessException {
        try {
            CreditCard creditCard = this.modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class);
            creditCard.setCustomer(this.customerService.getCustomerById(createCreditCardRequest.getCustomerId()).getData());
            if (checkIfNotExistsByCardNumber(creditCard.getCardNumber())) {
                this.creditCardDao.save(creditCard);
            }
            log.info(BusinessMessages.LogMessages.ADD_OPERATINON_WORK + " CreditCardManager -> Add Operation");
            return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.ADD_OPERATINON_NOT_WORK + " CreditCardManager -> Add Operation");
            return new ErrorResult(e.getMessage());
        }
    }

    /**
     * It gets all the credit cards from the database and returns them as a list.
     *
     * @return A list of CreditCardListDto objects.
     */
    @Override
    public DataResult<List<CreditCardListDto>> getAll() throws BusinessException {
        List<CreditCard> creditCards = this.creditCardDao.findAll();
        List<CreditCardListDto> creditCardListDtos = creditCards.stream().map(creditCard -> this.modelMapperService.forDto().map(creditCard, CreditCardListDto.class)).collect(Collectors.toList());
        log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + "CreditCardManager -> Add Operation");
        return new SuccessDataResult<>(creditCardListDtos, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * It gets the credit card by id.
     *
     * @param creditCarId The id of the credit card to be searched.
     * @return DataResult<CreditCardListDto>
     */
    @Override
    public DataResult<CreditCardListDto> getById(int creditCarId) throws BusinessException {
        try {
            checkIfExistsById(creditCarId);
            CreditCard creditCard = this.creditCardDao.getById(creditCarId);
            log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + "CreditCardManager -> GetAll Operation");
            CreditCardListDto creditCardListDto = this.modelMapperService.forDto().map(creditCard, CreditCardListDto.class);
            return new SuccessDataResult<>(creditCardListDto, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.GET_LIST_NOT_WORK + "CreditCardManager -> GetAll Operation");
            return new ErrorDataResult<>(e.getMessage());
        }
    }

    // Getting all the credit cards by customer id.
    @Override
    public DataResult<List<CreditCardListDto>> getAllCreditCardByCustomer_Id(int customerId) throws BusinessException {
        try {
            this.customerService.checkIfCustomerIdExists(customerId);
            List<CreditCard> creditCardList = this.creditCardDao.getAllByCustomer_UserId(customerId);
            List<CreditCardListDto> result = creditCardList.stream().map(creditCard -> this.modelMapperService.forDto().map(creditCard, CreditCardListDto.class)).collect(Collectors.toList());
            log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + "CreditCardManager -> GetAllCreditCardByCustomer_Id Operation");
            return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.GET_LIST_NOT_WORK + "CreditCardManager -> GetAllCreditCardByCustomer_Id Operation");
            return new ErrorDataResult<>(e.getMessage());
        }
    }

    /**
     * > This function checks if the user wants to save the credit card information and if so, it saves the credit card
     * information
     *
     * @param createCreditCardRequest This is the request object that contains the credit card information.
     * @param cardSaveInformation This is the enum that we created earlier.
     */
    @Override
    /**
     * This function checks if a credit card exists by customer id
     *
     * @param customerId The id of the customer to check if the credit card exists.
     */
    public void checkSaveInformationAndSaveCreditCard(CreateCreditCardRequest createCreditCardRequest, CreditCardManager.CardSaveInformation cardSaveInformation) throws BusinessException {
        log.info(BusinessMessages.LogMessages.CHECKING_DB + "CreditCardManager -> checkSaveInformationAndSaveCreditCard Operation");
        if (cardSaveInformation.equals(CardSaveInformation.SAVE)) {
            add(createCreditCardRequest);
        }
    }

    /**
     * This function checks if a credit card exists by customer id
     *
     * @param customerId The id of the customer to check if the credit card exists.
     */
    @Override
    public void checkIfNotExistsByCustomer_Id(int customerId) throws BusinessException {
        log.info(BusinessMessages.LogMessages.CHECKING_DB + "CreditCardManager -> CheckIfNotExistsByCustomer_Id Operation");
        if (!this.creditCardDao.existsByCustomer_UserId(customerId)) {
            throw new BusinessException(BusinessMessages.CustomerMessages.CUSTOMER_ID_NOT_FOUND + customerId);
        }
    }

    /**
     * > This function checks if a credit card with the given card number exists in the database
     *
     * @param cardNumber The card number to be checked.
     * @return A boolean value.
     */
    private boolean checkIfNotExistsByCardNumber(String cardNumber) {
        log.info(BusinessMessages.LogMessages.CHECKING_DB + "CreditCardManager -> CheckIfNotExistsByCardNumber Operation");
        return !this.creditCardDao.existsByCardNumber(cardNumber);
    }

    /**
     * This function checks if a credit card exists in the database by its id
     *
     * @param creditCardId The id of the credit card to be checked.
     */
    private void checkIfExistsById(int creditCardId) throws BusinessException {
        log.info(BusinessMessages.LogMessages.CHECKING_DB + "CreditCardManager -> CheckIfExistsById Operation");
        if (!this.creditCardDao.existsById(creditCardId)) {
            throw new BusinessException(BusinessMessages.CreditCardMessages.CREDIT_CARD_ID_NOT_FOUND + creditCardId);
        }
    }

    // An enum that we created to check if the user wants to save the credit card information.
    public enum CardSaveInformation {
        SAVE, DONT_SAVE
    }
}
