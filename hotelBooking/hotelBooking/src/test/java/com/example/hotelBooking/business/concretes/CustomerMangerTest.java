package com.example.hotelBooking.business.concretes;

import com.example.hotelBooking.core.utilities.exceptions.BusinessException;
import com.example.hotelBooking.core.utilities.mapping.ModelMapperManager;
import com.example.hotelBooking.dataAccess.CustomerDao;
import com.example.hotelBooking.entity.requests.customerRequests.CreateCustomerRequest;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerMangerTest {

    CustomerManager customerManager;
    @Mock
    CustomerDao customerDao;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        customerManager  = new CustomerManager(this.customerDao, new ModelMapperManager(new ModelMapper()));
    }

    @Test
    @DisplayName("Customer Create Successfully Without Any Error")
    public void createCustomerSuccessfully() throws BusinessException {

        CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
        createCustomerRequest.setFirstName("Ömer Faruk");
        createCustomerRequest.setLastName("Aykaç");
        createCustomerRequest.setPassword("12345");
        createCustomerRequest.setEmail("farukomeraykac@gmail.com");

        boolean result = customerManager.add(createCustomerRequest).isSuccess();

        Assert.assertTrue(result);

    }


}