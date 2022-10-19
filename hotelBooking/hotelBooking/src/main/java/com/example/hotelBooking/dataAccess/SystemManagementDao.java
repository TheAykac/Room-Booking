package com.example.hotelBooking.dataAccess;

import com.example.hotelBooking.entity.concretes.SystemManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemManagementDao extends JpaRepository<SystemManagement, Integer> {
    boolean existsByEmail(String email);


}
