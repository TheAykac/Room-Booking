package com.example.hotelBooking.dataAccess;

import com.example.hotelBooking.entity.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);

    boolean existsById(int customerId);

    Customer getByEmail(String email);
}
