package com.example.hotelBooking.dataAccess;

import com.example.hotelBooking.entity.abstracts.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
