package com.example.hotelBooking.core.utilities.messages;

public class BusinessMessages {


    /**
     * A class that contains the messages used in the project.
     */
    public class GlobalMessages {

        public static final String DATA_LISTED_SUCCESSFULLY = "Data Listed Successfully";

        public static final String DATA_ADDED_SUCCESSFULLY = "Data Added Successfully";
        public static final String DATA_UPDATED_SUCCESSFULLY = "Data updated Successfully, dataId: ";
        public static final String DATA_DELETED_SUCCESSFULLY = "Data deleted successfully, dataId: ";
    }

    public class CampaignMessages {
        public static final String CAMPAIG_NOT_STARTED = "The Campaign Has Not Started CampaignCode: ";
        public static final String CAMPAIGN_EXPIRED = "The Campaign Has Expıred CampaignCode: ";
        public static final String CAPIGN_CODE_NOT_FOUNT = "Campaign Code Not Found CampaignCode: ";
        public static final String CAPIGN_CODE_NOT_AMOUNT = "The Usage Amount Of The Campaign Code Has Expired CampaignCode: ";
    }

    public class User {
        public static final String USER_EMAIL_NOT_FOUND = "This Email Is Already Registered email: ";
    }

    public class Hotel {
        public static final String HOTEL_NOT_CONFIRM = "Hotel Status Unconfirmed HotelId: ";
    }

    public class SystemManagementMessages {

        public static final String SYTEM_ID_NOT_FOUND = "Sytem Management ID Not Exists sytemManagementId: ";
    }

    public class RoomRenovation {
        public static final String ROOMRENOVATION_ID_NOT_FOUND = "RoomRenovation Service ID not Exists RoomRenovationID: ";
        public static final String ROOMRENOVATION_ID_EXISTS_ALREADY = "The room you want to rent is currently under maintenance RoomId: ";
    }


    public class RoomBooking {
        public static final String ROOMBOOKING_ID_NOT_FOUND = "RoomBooking Service ID not exists, RoomBokingId: ";
        public static final String FINISH_DATE_CANNOT_BEFORE_START_DATE = "Finish date cannot be earlier than start date";
        public static final String ROOM_ALREADY_BOOKING_ENTERED_DATES = "The Room  already booking entered date";
    }

    public class Room {
        public static final String ROOM_ID_NOT_FOUND = "Room Service ID not exists, roomID: ";
        public static final String ROOM_LISTED_BY_LESS_THEN_EQUAL = "room listed successfully by Daily Price less then equal, dailyPrice: ";
        public static final String ALL_ROOMS_PAGED = "All rooms paged successfully";
        public static final String ALL_ROOMS_SORTED = "All rooms sorted successfully";
        public static final String PAGE_NO_OR_PAGE_SIZE_NOT_VALID = "Page No or Page Size value not valid.";
    }


    public class CreditCardMessages {
        public static final String CREDIT_CARD_ID_NOT_FOUND = "Credit card ID not exists, creditCardId: ";
        public static final String CREDIT_CARD_CARD_NUMBER_NOT_VALID = "Credit card Card Number not Valid, must be only 16 digits and must be only number";
        public static final String CREDIT_CARD_CVV_NOT_VALID = "Credit card CVV not Valid, must be only 3 digits and must be only number";
        public static final String CREDIT_CARD_OWNER_NOT_VALID = "Credit card Owner not Valid, must be only 5-50 digits and must be only String";

    }

    public class CustomerMessages {
        public static final String CUSTOMER_ID_NOT_FOUND = "Customer ID not exists, customerId: ";
        public static final String CUSTOMER_EMAIL_ALREAY_EXISTS = "Customer Email already exists, email: ";
        public static final String CUSTOMER_EMAIL_INVALID = "Customer Email is Invalid, email: ";

    }

    public class InvoiceMessages {
        public static final String INVOICE_ID_NOT_FOUND = "Invoice ID not exists, invoiceId: ";
        public static final String CUSTOMER_ID_NOT_FOUND = "Customer ID Not Exists, customerId: ";
        public static final String PAYMENT_ID_NOT_FOUND = "Payment ID Not Exists, paymentId: ";
        public static final String ROOMBOOKING_ID_NOT_FOUND = "RoomBooking ID Not Exists, roomBookingId: ";
        public static final String INVOICE_NO_NOT_VALID = "Invoice no not valid";
    }


    public class PaymentMessages {
        public static final String PAYMENT_AND_BOOKING_ROOMSUCCESSFULLY = "Payment, Room Booking, Additional Service adding and Invoice creation successfully.";
        public static final String PAYMEYNT_ALREADY_DONE = "Paymet Already Done, RoomBookingId: ";
        public static final String PAYMENT_ID_NOT_FOUND = "Payment ID not exists, paymentId: ";
    }


    public class LogMessages {
        public static final String CHECK_OF_DATE="Checking dates in the database for this room roomId: ";
        public static final String CREATE_INVOICE = "creating an invoice";
        public static final String ADD_OPERATINON_WORK = "The Add Operation Worked";
        public static final String ADD_OPERATINON_NOT_WORK = "The Add Operation Did Not Work";
        public static final String DELETE_OPERATINON_WORK = "The Delete Operation Worked";
        public static final String DELETE_OPERATINON_NOT_WORK = "The Delete Operation Did Not Work";
        public static final String UPDATE_OPERATINON_WORK = "The Update Operation Worked";
        public static final String UPDATE_OPERATINON_NOT_WORK = "The Update Operation Did Not Work";
        public static final String GET_LIST_WORKED = "The Get List Operation Worked";
        public static final String GET_LIST_NOT_WORK = "The Get List Operation Did Not Work";
        public static final String SYSTEM_STARTED = "System Is Started";
        public static final String DELETE_FROM_DATABASE = "Deleted From  Database";
        public static final String ALL_LIST = "Fetching All Customer";
        public static final String PAYMENT_SUCCES = "Successfully Received Payment";
        public static final String PAYMENT_FAILED = "An Error Occurred While Receiving The Payment";

        public static final String PAGED_LIST = "Paged Being Done";

        public static final String SORT_LIST = "Sorted Being Done";
        public static final String CHECKING_DB = "Database Checking";

        public static final String CHECKING_PAGE = "Page No and Page Size Checking";

    }
}
