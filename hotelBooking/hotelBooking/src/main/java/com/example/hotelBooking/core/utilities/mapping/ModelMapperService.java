package com.example.hotelBooking.core.utilities.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {

    ModelMapper forDto();

    ModelMapper forRequest();
}
