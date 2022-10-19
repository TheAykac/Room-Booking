package com.example.hotelBooking.entity.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private int id;

    @Column(name = "invoice_no", unique = true, length = 16, nullable = false)
    private String invoiceNo;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    private Date creationDate;

    @Column(name = "start_date", nullable = false, updatable = false)
    private LocalDate startDate;

    @Column(name = "finish_date", nullable = false, updatable = false)
    private LocalDate finishDate;

    @Column(name = "total_rental_day", nullable = false, updatable = false)
    private int totalBookingDay;

    @Column(name = "booking_room_total_price", nullable = false, updatable = false)
    private double bookingRoomTotalPrice;


    @ManyToOne
    @JoinColumn(name = "room_booking_id", nullable = false, updatable = false)
    private RoomBooking roomBooking;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    @JoinColumn(name = "customer_id", nullable = false, updatable = false)
    private Customer customer;

    @OneToOne()
    @JoinColumn(name = "payment_id", unique = true, nullable = false, updatable = false)
    private Payment payment;
}
