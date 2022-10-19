package com.example.hotelBooking.entity.concretes;

import com.example.hotelBooking.entity.abstracts.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "customer_id", referencedColumnName = "user_id")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends User {


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @OneToMany(mappedBy = "customer")
    private List<RoomBooking> roomBookings;

    @OneToMany(mappedBy = "customer")
    private List<Invoice> invoices;


}
