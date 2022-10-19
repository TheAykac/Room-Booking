package com.example.hotelBooking.business.concretes;

import com.example.hotelBooking.api.models.MakePayment;
import com.example.hotelBooking.business.abstracts.*;
import com.example.hotelBooking.business.adapters.posAdapters.PosService;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.mapping.ModelMapperService;
import com.example.hotelBooking.core.utilities.messages.BusinessMessages;
import com.example.hotelBooking.core.utilities.result.*;
import com.example.hotelBooking.dataAccess.PaymentDao;
import com.example.hotelBooking.entity.concretes.Campaign;
import com.example.hotelBooking.entity.concretes.Payment;
import com.example.hotelBooking.entity.dtos.paymentDtos.PaymentListDto;
import com.example.hotelBooking.entity.dtos.roomBookingDtos.RoomBookingListDto;
import com.example.hotelBooking.entity.requests.hotelRequests.paymentRequests.CreatePaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PaymentManager implements PaymentService {

    // A constructor injection.
    private final PaymentDao paymentDao;
    private final RoomBookingService roomBookingService;
    private final ModelMapperService modelMapperService;
    private final PosService posService;
    private final InvoiceService invoiceService;
    private final CreditCardService creditCardService;
    private final CampaignService campaignService;

    @Autowired
    public PaymentManager(PaymentDao paymentDao, RoomBookingService roomBookingService, ModelMapperService modelMapperService, PosService posService, InvoiceService invoiceService, CreditCardService creditCardService, CampaignService campaignService) {
        this.paymentDao = paymentDao;
        this.roomBookingService = roomBookingService;
        this.modelMapperService = modelMapperService;
        this.posService = posService;
        this.invoiceService = invoiceService;
        this.creditCardService = creditCardService;
        this.campaignService = campaignService;
    }

    /**
     * It gets all the payments from the database and maps them to a list of PaymentListDto objects.
     *
     * @return A list of PaymentListDto objects.
     */
    @Override
    public DataResult<List<PaymentListDto>> getAll() throws BusinessException {
        List<Payment> payments = this.paymentDao.findAll();
        List<PaymentListDto> paymentListDtos = payments.stream().map(payment -> this.modelMapperService.forDto().map(payment, PaymentListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(paymentListDtos, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * > This function gets a payment by its id
     *
     * @param paymentId The id of the payment to be searched.
     * @return A DataResult<PaymentListDto> object.
     */
    @Override
    public DataResult<PaymentListDto> getById(int paymentId) throws BusinessException {
        checkIfExistByPaymentId(paymentId);
        Payment payment = this.paymentDao.getById(paymentId);
        PaymentListDto paymentListDto = this.modelMapperService.forDto().map(payment, PaymentListDto.class);
        return new SuccessDataResult<>(paymentListDto, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * It makes a payment for a room booking.
     *
     * @param makePayment This is the object that contains the payment information.
     * @param cardSaveInformation This parameter is used to save the credit card information.
     * @return Result
     */
    @Override
    public Result makePayment(MakePayment makePayment, CreditCardManager.CardSaveInformation cardSaveInformation) throws BusinessException {
        try {
            checkAllCommonCampaignValidation(makePayment);
            checkIfExistsByRoomBookingId(makePayment.getRoomBookingId());
            calculatingTheAmountPayable(makePayment);
            this.posService.payment(makePayment.getCreateCreditCardRequest().getCardNumber(), makePayment.getCreateCreditCardRequest().getCardOwner(), makePayment.getCreateCreditCardRequest().getCardCvv(), makePayment.getCreateCreditCardRequest().getCardExpirationDate(), makePayment.getCreateCreditCardRequest().getCustomerId());
            checkCampaignCodeisNull(makePayment);
            runPaymentSuccessorForRoomBooking(makePayment, cardSaveInformation);
            log.info(BusinessMessages.LogMessages.PAYMENT_SUCCES + " Paymenetmanager -> MakePayment Operation");
            return new SuccessResult(BusinessMessages.PaymentMessages.PAYMENT_AND_BOOKING_ROOMSUCCESSFULLY);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.PAYMENT_FAILED + " PaymantManager -> MakePayment Operation");
            return new ErrorResult(e.getMessage());
        }
    }

    /**
     * This function checks if the payment exists in the database by the payment id
     *
     * @param paymentId The id of the payment to be checked.
     */
    @Override
    public void checkIfExistByPaymentId(int paymentId) throws BusinessException {
        log.info(BusinessMessages.LogMessages.CHECKING_DB + " PaymentManager -> CheckIfExistByPaymentId Operation");
        if (!this.paymentDao.existsById(paymentId)) {
            throw new BusinessException(BusinessMessages.PaymentMessages.PAYMENT_ID_NOT_FOUND + paymentId);
        }
    }

    @Transactional(rollbackFor = BusinessException.class)
    // A function that is used to make a payment for a room booking.
    void runPaymentSuccessorForRoomBooking(MakePayment makePayment, CreditCardManager.CardSaveInformation cardSaveInformation) throws BusinessException {
        RoomBookingListDto roomBooking = this.roomBookingService.getById(makePayment.getRoomBookingId()).getData();
        int roomBookingId = makePayment.getRoomBookingId();
        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest();
        createPaymentRequest.setRoomBookingId(roomBookingId);
        createPaymentRequest.setTotalPrice(roomBooking.getTotalPrice());
        makePayment.getCreateCreditCardRequest().setCustomerId(roomBooking.getCustomerId());
        Payment payment = this.modelMapperService.forDto().map(createPaymentRequest, Payment.class);
        int paymentId = this.paymentDao.save(payment).getId();
        this.creditCardService.checkSaveInformationAndSaveCreditCard(makePayment.getCreateCreditCardRequest(), cardSaveInformation);
        this.invoiceService.createAddInvoice(roomBookingId, paymentId);
    }

    /**
     * It checks if a payment exists for a given room booking id
     *
     * @param roomBookingId The id of the room booking that we want to check if it has a payment or not.
     */
    private void checkIfExistsByRoomBookingId(int roomBookingId) throws BusinessException {
        if (this.paymentDao.existsByRoomBooking_Id(roomBookingId)) {
            throw new BusinessException(BusinessMessages.PaymentMessages.PAYMEYNT_ALREADY_DONE + roomBookingId);
        }
    }

    /**
     * > This function checks if the campaign exists, if the campaign start date is valid, if the campaign finish date is
     * valid and if the campaign quantity is valid
     *
     * @param makePayment The object that contains the request parameters.
     */
    private void checkAllCommonCampaignValidation(MakePayment makePayment) throws BusinessException {
        this.campaignService.checkIfExistsByCampaignCode(makePayment.getUseCampaignCodeRequest().getCampaignCode());
        this.campaignService.checkIfCapmaignStartDate(makePayment.getUseCampaignCodeRequest().getCampaignCode());
        this.campaignService.checkIfCapmaignFinishDate(makePayment.getUseCampaignCodeRequest().getCampaignCode());
        this.campaignService.checkIfCampaignCampaignQuantity(makePayment.getUseCampaignCodeRequest().getCampaignCode());
    }

    /**
     * > This function checks if the campaign code is null or not
     *
     * @param makePayment The object that contains the request parameters.
     */
    private void checkCampaignCodeisNull(MakePayment makePayment) throws BusinessException {
        this.campaignService.checkIfExistsByCampaignCode(makePayment.getUseCampaignCodeRequest().getCampaignCode());
        Campaign campaign = this.campaignService.getByCampaignCode(makePayment.getUseCampaignCodeRequest().getCampaignCode()).getData();
        if (campaign != null) {
            campaign.setCampaignQuantityUsed(campaign.getCampaignQuantityUsed() + 1);
        }
    }

    /**
     * It calculates the amount payable for a room booking
     *
     * @param makePayment The object that contains the information of the payment.
     */
    private void calculatingTheAmountPayable(MakePayment makePayment) throws BusinessException {
        RoomBookingListDto roomBooking = this.roomBookingService.getById(makePayment.getRoomBookingId()).getData();
        Campaign campaign = this.campaignService.getByCampaignCode(makePayment.getUseCampaignCodeRequest().getCampaignCode()).getData();
        if (campaign != null) {
            double totalPrice = roomBooking.getTotalPrice() - (roomBooking.getTotalPrice() * campaign.getPercentDiscount() / 100);
        } else {
            double totalPrice = roomBooking.getTotalPrice();
        }

    }


}
