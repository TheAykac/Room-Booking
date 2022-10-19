package com.example.hotelBooking.entity.dtos.invoiceDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceListDto {

    private int invoiceId;
    private String invoiceNo;
    private int roomBookingId;
    private LocalDate startDate;
    private LocalDate finishDate;
    private short totalBookingDay;
    private double roomBookingTotalPrice;
    private int customerId;
    private int paymentId;
}
