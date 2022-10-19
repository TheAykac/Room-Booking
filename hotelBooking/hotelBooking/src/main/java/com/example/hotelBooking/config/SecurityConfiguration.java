package com.example.hotelBooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    UserDetailsService userDetailsService;


    /**
     * A function that is used to control the access of the user to the endpoints.
     *
     * @param http This is the main entry point for configuring the security of a web application.
     * @return SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        auth ->
                                auth.antMatchers("/swagger-ui.html",
                                                "/swagger-ui/**",
                                                "/swagger-ui/index.html**",
                                                "/v3/api-docs",
                                                "/api/customer/add**",
                                                "/api/campaign/getAll**",
                                                "/api/hotel/add",
                                                "/api/hotel/getAll",
                                                "/api/room/getAll",
                                                "/api/room/findByDailyPriceLessThenEqual",
                                                "/api/room/getAllPagedRoom",
                                                "/api/room/getAllSortedRoom",
                                                "/api/room//getAvailableAllRooms").permitAll()


                                        .antMatchers("/api/customer/getAll").hasAuthority("SYSTEM_MANAGEMENT")
                                        .antMatchers("/api/customer/getById").hasAuthority("SYSTEM_MANAGEMENT")


                                        .antMatchers("/api/creditCard/getAll").hasAuthority("SYSTEM_MANAGEMENT")
                                        .antMatchers("/api/creditCard/getById").hasAuthority("SYSTEM_MANAGEMENT")
                                        .antMatchers("/api/creditCard/getAllCreditCardByCustomer_Id").hasAuthority("SYSTEM_MANAGEMENT")

                                        .antMatchers("/api/campaign/add").hasAuthority("SYSTEM_MANAGEMENT")
                                        .antMatchers("/api/campaign/getById").hasAuthority("SYSTEM_MANAGEMENT")

                                        .antMatchers("/api/hotel/delete").hasAnyAuthority("HOTEL_MANAGEMENT", "SYSTEM_MANAGEMENT")
                                        .antMatchers("/api/hotel/update").hasAnyAuthority("HOTEL_MANAGEMENT", "SYSTEM_MANAGEMENT")

                                        .antMatchers("/api/hotel/getById").hasAuthority("SYSTEM_MANAGEMENT")
                                        .antMatchers("/api/hotel/updateHotelStatu").hasAuthority("SYSTEM_MANAGEMENT")

                                        .antMatchers("/api/invoice/getAll").hasAuthority("SYSTEM_MANAGEMENT")
                                        .antMatchers("/api/invoice/getInvociceByPaymetn_Id").hasAnyAuthority("SYSTEM_MANAGEMENT", "CUSTOMER")
                                        .antMatchers("/api/invoice/getAllByCustomer_Id").hasAnyAuthority("SYSTEM_MANAGEMENT", "CUSTOMER")

                                        .antMatchers("/api/payment/getAll").hasAuthority("SYSTEM_MANAGEMENT")
                                        .antMatchers("/api/payment/getById").hasAuthority("SYSTEM_MANAGEMENT")
                                        .antMatchers("/api/payment/makePayment").hasAnyAuthority("SYSTEM_MANAGEMENT", "CUSTOMER", "HOTEL_MANAGEMENT")

                                        .antMatchers("/api/roomBooking/add").hasAnyAuthority("SYSTEM_MANAGEMENT", "CUSTOMER", "HOTEL_MANAGEMENT")
                                        .antMatchers("/api/roomBooking/getAll").hasAuthority("SYSTEM_MANAGEMENT")
                                        .antMatchers("/api/roomBooking/getById").hasAuthority("SYSTEM_MANAGEMENT")
                                        .antMatchers("/api/roomBooking/delete").hasAnyAuthority("SYSTEM_MANAGEMENT", "CUSTOMER")
                                        .antMatchers("/api/roomBooking/getAllByRoomBooking_RoomId").hasAuthority("SYSTEM_MANAGEMENT")
                                        .antMatchers("/api/roomBooking/getAllByCustomer_Id").hasAuthority("SYSTEM_MANAGEMENT")

                                        .antMatchers("/api/room/add").hasAnyAuthority("SYSTEM_MANAGEMENT", "HOTEL_MANAGEMENT")
                                        .antMatchers("/api/room/getById").hasAuthority("HOTEL_MANAGEMENT")
                                        .antMatchers("/api/room/delete").hasAnyAuthority("SYSTEM_MANAGEMENT", "HOTEL_MANAGEMENT")

                                        .antMatchers("api/roomRenovation/add").hasAnyAuthority("SYSTEM_MANAGEMENT", "HOTEL_MANAGEMENT")
                                        .antMatchers("api/roomRenovation/delete").hasAnyAuthority("SYSTEM_MANAGEMENT", "HOTEL_MANAGEMENT")
                                        .antMatchers("api/roomRenovation/getAll").hasAnyAuthority("SYSTEM_MANAGEMENT", "HOTEL_MANAGEMENT")

                                        .antMatchers("/api/systemManagement/add").hasAuthority("SYSTEM_MANAGEMENT")
                                        .antMatchers("/api/systemManagement/delete").hasAuthority("SYSTEM_MANAGEMENT")
                                        .antMatchers("/api/systemManagement/getAll").hasAuthority("SYSTEM_MANAGEMENT")
                                        .antMatchers("/api/systemManagement/getById").hasAuthority("SYSTEM_MANAGEMENT")


                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin().and()
                .build();
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}



