package com.example.hotelBooking.entity.dtos.CustomerDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
