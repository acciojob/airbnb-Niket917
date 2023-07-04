package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class HotalManagementService {

    @Autowired
    HotalManagementRepository hotalManagementRepository ;

    public String addHotal(Hotel hotel){

            return hotalManagementRepository.addHotel(hotel);

    }


    public Integer addUder(User user) {
        return hotalManagementRepository.addUder(user);
    }

    public String getHotelWithMostFacilities() {
        return hotalManagementRepository.getHotelWithMostFacilities();
    }

    public int bookARoom(Booking booking) {
        return hotalManagementRepository.bookARoom(booking);
    }

    public int getBooking(Integer aadharCard) {
        return hotalManagementRepository.getBooking(aadharCard);
    }


    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        return hotalManagementRepository.updateFacilities(newFacilities,hotelName);
    }
}

