package com.example.hotelBooking.entity.concretes;

import com.example.hotelBooking.entity.abstracts.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "system_management")
@PrimaryKeyJoinColumn(name = "system_management_id", referencedColumnName = "user_id")
public class SystemManagement extends User {


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


}
