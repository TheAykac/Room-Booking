package com.example.hotelBooking.business.concretes;

import com.example.hotelBooking.business.abstracts.*;
import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.generate.GenerateRandomCode;
import com.example.hotelBooking.core.utilities.mapping.ModelMapperService;
import com.example.hotelBooking.core.utilities.messages.BusinessMessages;
import com.example.hotelBooking.core.utilities.result.*;
import com.example.hotelBooking.dataAccess.InvoiceDao;
import com.example.hotelBooking.entity.concretes.Invoice;
import com.example.hotelBooking.entity.dtos.invoiceDtos.InvoiceListDto;
import com.example.hotelBooking.entity.dtos.roomBookingDtos.RoomBookingListDto;
import com.example.hotelBooking.entity.requests.invoiceRequest.CreateInvoiceRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InvoiceManagar implements InvoiceService {

    // This is a constructor.
    private final InvoiceDao invoiceDao;
    private final ModelMapperService modelMapperService;
    private final RoomBookingService roomBookingService;
    private final RoomService roomService;
    private final CustomerService customerService;
    private final PaymentService paymentService;

    @Autowired
    public InvoiceManagar(InvoiceDao invoiceDao, ModelMapperService modelMapperService, RoomBookingService roomBookingService, RoomService roomService, CustomerService customerService, @Lazy PaymentService paymentService) {
        this.invoiceDao = invoiceDao;
        this.modelMapperService = modelMapperService;
        this.roomBookingService = roomBookingService;
        this.roomService = roomService;
        this.customerService = customerService;
        this.paymentService = paymentService;
    }

    /**
     * It gets all the invoices from the database and maps them to the InvoiceListDto.
     *
     * @return A list of InvoiceListDto objects.
     */
    @Override
    public DataResult<List<InvoiceListDto>> getAll() {
        List<Invoice> invoices = this.invoiceDao.findAll();
        List<InvoiceListDto> invoiceListDtos = invoices.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, InvoiceListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(invoiceListDtos, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    /**
     * > This function adds an invoice to the database
     *
     * @param createInvoiceRequest This is the request object that is passed to the service layer.
     * @return A SuccessResult object with a message.
     */
    @Override
    public Result add(CreateInvoiceRequest createInvoiceRequest) throws BusinessException {
        Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
        invoice.setId(0);
        invoice.setCustomer(this.customerService.getCustomerById(createInvoiceRequest.getCustomerId()).getData());
        this.invoiceDao.save(invoice);
        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }

    /**
     * It creates an invoice for a room booking.
     *
     * @param roomBookingId The id of the room booking.
     * @param paymentId Payment id of the payment made by the customer.
     */
    @Override
    public void createAddInvoice(int roomBookingId, int paymentId) throws BusinessException {
        try {
            log.info(BusinessMessages.LogMessages.CREATE_INVOICE + "InvoiceManger -> CreateAddInvoice Operation");
            DataResult<RoomBookingListDto> roomBooking = this.roomBookingService.getById(roomBookingId);
            int totalDays = this.roomBookingService.getTotalDaysForBooking(roomBooking.getData().getStartDate(), roomBooking.getData().getFinishDate());
            double priceOfDays = this.roomBookingService.calculateRoomBookingTotalDayPrice(roomBooking.getData().getStartDate(), roomBooking.getData().getFinishDate(), this.roomService.getDailyPriceByRoomId(roomBooking.getData().getRoomId()));
            double totalPrice = priceOfDays;
            CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest();
            createInvoiceRequest.setInvoiceNo(generateCode());
            createInvoiceRequest.setPaymentId(paymentId);
            createInvoiceRequest.setFinishDate(roomBooking.getData().getFinishDate());
            createInvoiceRequest.setCustomerId(roomBooking.getData().getCustomerId());
            createInvoiceRequest.setStartDate(roomBooking.getData().getStartDate());
            createInvoiceRequest.setRoomBookingId(roomBookingId);
            createInvoiceRequest.setRoomBookingTotalPrice(totalPrice);
            createInvoiceRequest.setTotalBookingDay(totalDays);
            add(createInvoiceRequest);
            log.info(BusinessMessages.LogMessages.ADD_OPERATINON_WORK + " ");
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * It gets the invoice by id.
     *
     * @param invoiceId The id of the invoice to be searched.
     * @return DataResult<InvoiceListDto>
     */
    @Override
    public DataResult<InvoiceListDto> getById(int invoiceId) throws BusinessException {
        try {
            checkIfExistsByInvoiceId(invoiceId);
            Invoice invoice = this.invoiceDao.getById(invoiceId);
            InvoiceListDto invoiceListDto = this.modelMapperService.forDto().map(invoice, InvoiceListDto.class);
            log.info(BusinessMessages.LogMessages.ADD_OPERATINON_WORK + " InvoiceManger -> GetById Operation");
            return new SuccessDataResult<>(invoiceListDto, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.ADD_OPERATINON_NOT_WORK + " InvoiceManger -> GetById Operation");
            return new ErrorDataResult<>(e.getMessage());
        }
    }

    /**
     * It gets the invoice by payment id.
     *
     * @param paymentId Payment Id
     * @return DataResult<InvoiceListDto>
     */
    @Override
    public DataResult<InvoiceListDto> getInvociceByPaymetn_Id(int paymentId) throws BusinessException {
        try {
            checkIfNotExistsByPayment_Id(paymentId);
            Invoice invoice = this.invoiceDao.getInvoiceByPayment_Id(paymentId);
            InvoiceListDto invoiceListDto = this.modelMapperService.forDto().map(invoice, InvoiceListDto.class);
            log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + " InvoiceManger -> GetInvoiceByPayment_Id Operation");
            return new SuccessDataResult<>(invoiceListDto, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.GET_LIST_NOT_WORK + " InvoiceManger -> GetInvoiceByPayment_Id Operation");
            return new ErrorDataResult<>(e.getMessage());
        }
    }

    /**
     * It gets all the invoices by room booking id.
     *
     * @param roomBookingId The id of the room booking.
     * @return A list of InvoiceListDto objects.
     */
    @Override
    public DataResult<List<InvoiceListDto>> getAllByRoomBooking_Id(int roomBookingId) throws BusinessException {
        try {
            checkIfnotExistsByRoomBooking_Id(roomBookingId);
            List<Invoice> invoices = this.invoiceDao.getAllByRoomBooking_Id(roomBookingId);
            List<InvoiceListDto> invoiceListDtos = invoices.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, InvoiceListDto.class)).collect(Collectors.toList());
            log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + " InvoiceManger -> GetAllByRoomBooking_Id Operation");
            return new SuccessDataResult<>(invoiceListDtos, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.GET_LIST_NOT_WORK + " InvoiceManger -> GetAllByRoomBooking_Id Operation");
            return new ErrorDataResult<>(e.getMessage());
        }
    }

    /**
     * It gets all the invoices of a customer.
     *
     * @param customerId The id of the customer who wants to see the invoice list.
     * @return A list of InvoiceListDto objects.
     */
    @Override
    public DataResult<List<InvoiceListDto>> getAllByCustomer_Id(int customerId) throws BusinessException {
        try {
            checkIfNotExistsByCustomer_Id(customerId);
            List<Invoice> invoices = this.invoiceDao.getAllByCustomer_UserId(customerId);
            List<InvoiceListDto> invoiceListDtos = invoices.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, InvoiceListDto.class)).collect(Collectors.toList());
            log.info(BusinessMessages.LogMessages.GET_LIST_WORKED + " InvoiceManger -> GetAllByCustomer_Id Operation");
            return new SuccessDataResult<>(invoiceListDtos, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
        } catch (Exception e) {
            log.warn(BusinessMessages.LogMessages.GET_LIST_NOT_WORK + " InvoiceManger -> GetAllByCustomer_Id Operation");
            return new ErrorDataResult<>(e.getMessage());
        }
    }

    /**
     * This function checks if the customer id exists in the database
     *
     * @param customerId The id of the customer that we want to check if it exists in the database.
     */
    @Override
    public void checkIfNotExistsByCustomer_Id(int customerId) throws BusinessException {
        log.info(BusinessMessages.LogMessages.CHECKING_DB + " InvoiceManger -> CheckIfNotExistsByCustomer_Id Operation");
    /**
     * This function checks if the invoice exists in the database by the room booking id
     *
     * @param roomBookingId The id of the room booking that we want to check if it exists in the database.
     */
        if (!this.invoiceDao.existsByCustomer_UserId(customerId)) {
            throw new BusinessException(BusinessMessages.InvoiceMessages.CUSTOMER_ID_NOT_FOUND + customerId);
        }
    }

    @Override
    public void checkIfnotExistsByRoomBooking_Id(int roomBookingId) throws BusinessException {
        log.info(BusinessMessages.LogMessages.CHECKING_DB + " InvoiceManger -> CheckIfNotExistsByRoomBooking_Id Operation");
        if (!this.invoiceDao.existsByRoomBooking_Id(roomBookingId)) {
            throw new BusinessException(BusinessMessages.InvoiceMessages.ROOMBOOKING_ID_NOT_FOUND + roomBookingId);
        }
    }

    /**
     * This function checks if the invoice exists in the database by the payment id
     *
     * @param paymentId The id of the payment that we want to check if it exists in the database.
     */
    @Override
    public void checkIfNotExistsByPayment_Id(int paymentId) throws BusinessException {
        log.info(BusinessMessages.LogMessages.CHECKING_DB + " InvoiceManger -> CheckIfNotExistsByPayment_Id Operation");
        if (!this.invoiceDao.existsByPayment_Id(paymentId)) {
            throw new BusinessException(BusinessMessages.InvoiceMessages.PAYMENT_ID_NOT_FOUND + paymentId);
        }
    }

    /**
     * This function checks if the invoice exists in the database by the invoice id
     *
     * @param invoiceId The id of the invoice to be checked.
     */
    private void checkIfExistsByInvoiceId(int invoiceId) throws BusinessException {
        log.info(BusinessMessages.LogMessages.CHECKING_DB + " InvoiceManger -> CheckIfExistsByInvoiceId Operation");
        if (!this.invoiceDao.existsById(invoiceId)) {
            throw new BusinessException(BusinessMessages.InvoiceMessages.INVOICE_ID_NOT_FOUND + invoiceId);
        }
    }

    /**
     * Generate a random code, check if it exists in the database, if it does, generate another one, if it doesn't, return
     * it
     *
     * @return A random code that is not already in the database.
     */
    private String generateCode() {
        while (true) {
            String code = GenerateRandomCode.generate();
            if (!this.invoiceDao.existsByInvoiceNo(code)) {
                return code;
            }
        }
    }


}