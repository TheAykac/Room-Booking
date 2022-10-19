package com.example.hotelBooking.config;

import com.example.hotelBooking.dataAccess.UserDao;
import com.example.hotelBooking.entity.abstracts.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    /**
     * It takes a username, finds the user in the database, and returns a UserDetails object
     *
     * @param username The username of the user to load.
     * @return A CustomUserDetail object.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDao.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new CustomUserDetail(user);
    }
}
