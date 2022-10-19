package com.example.hotelBooking.entity.requests.invoiceRequest;

import com.example.hotelBooking.core.utilities.messages.BusinessMessages;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {

    @JsonIgnore
    @Pattern(regexp = "^[0-9]{16}", message = BusinessMessages.InvoiceMessages.INVOICE_NO_NOT_VALID)
    private String invoiceNo;

    @JsonIgnore
    @CreationTimestamp
    private Date creationDate;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate finishDate;

    @NotNull
    private int totalBookingDay;
    @NotNull
    private double roomBookingTotalPrice;


    @NotNull
    private int roomBookingId;

    @NotNull
    private int customerId;

    @NotNull
    private int paymentId;
}
