package com.example.hotelBooking.entity.requests.roomBookingRequests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "totalPrice"})
public class CreateRoomBookingRequest {
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate finishDate;

    @NotNull
    private int roomId;

    @NotNull
    private int customerUserId;


    private Double totalPrice;
}
