package com.example.hotelBooking.entity.dtos.systemManagementDtos;

import com.example.hotelBooking.core.utilities.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemManagementListDtos {
    private String email;
    private String password;
    private UserRole userRole;
    private String firstName;
    private String lastName;
}
