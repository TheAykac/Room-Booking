package com.example.hotelBooking.dataAccess;

import com.example.hotelBooking.entity.concretes.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardDao extends JpaRepository<CreditCard, Integer> {

    boolean existsByCreditCardId(int creditCardId);

    boolean existsByCustomer_UserId(int customerId);

    boolean existsByCardNumber(String cardNumber);

    List<CreditCard> getAllByCustomer_UserId(int customerId);

}
