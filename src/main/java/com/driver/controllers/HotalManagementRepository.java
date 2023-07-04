package com.driver.controllers;
import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class HotalManagementRepository {
    Map<String,Hotel> hotalDb = new HashMap<>();
    Map<Integer,User> userDb = new HashMap<>();
    Map<String,Booking> bookingDb = new HashMap<>();
    Map<String,Integer> userRent = new HashMap<>();
    public String addHotel(Hotel hotel) {
        if(hotel.getHotelName() == null){
            return "FAILURE";
        }else if(hotalDb.containsKey(hotel.getHotelName())){
            return "FAILURE";
        } else {
            hotalDb.put(hotel.getHotelName(),hotel);
            return "SUCCESS";
        }
    }

    public Integer addUder(User user) {
        int adharNum = user.getaadharCardNo();
        userDb.put(adharNum,user);
        return adharNum;
    }

    public String getHotelWithMostFacilities() {
        int maxFacilitity = 0;
        for(String key : hotalDb.keySet()){
            List<Facility> facilityList = hotalDb.get(key).getFacilities();
            maxFacilitity = Math.max(maxFacilitity,facilityList.size());
        }
        if(maxFacilitity == 0)return "";
        List<String> hotelName = new ArrayList<>();
        for(String key : hotalDb.keySet()) {
            List<Facility> facilities = hotalDb.get(key).getFacilities();
            if (maxFacilitity == facilities.size()) {
                hotelName.add(key);
            }
        }
        Collections.sort(hotelName);
        return hotelName.get(0);

    }

    public int bookARoom(Booking booking) {
        String hotelName = booking.getHotelName();
        if(!hotalDb.containsKey(hotelName)) {
            return -1;
        }
        if(hotalDb.get(hotelName).getAvailableRooms() >= booking.getNoOfRooms()){
                Hotel hotel = hotalDb.get(hotelName);
                int totalRoomAvialable = hotel.getAvailableRooms();
                totalRoomAvialable -= booking.getNoOfRooms();
                hotel.setAvailableRooms(totalRoomAvialable);
                hotalDb.put(hotelName,hotel);
                String bookingId = UUID.randomUUID()+"";
                System.out.println(bookingId +" bookingId");
                int amoutToBePaid = hotel.getPricePerNight() * booking.getNoOfRooms();
                userRent.put(bookingId,amoutToBePaid);
                System.out.println(amoutToBePaid + " Amout to be Paid");
                return amoutToBePaid;
            }
           return -1;
    }

    public int getBooking(Integer aadharCard) {
        int count = 0;
        for(String key : bookingDb.keySet()){
            if(aadharCard.equals(bookingDb.get(key).getBookingAadharCard())){
                count++;
            }
        }
        return count;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        if(!hotalDb.containsKey(hotelName)) return null;
        Hotel hotel = hotalDb.get(hotelName);
        List<Facility>  facilities = hotel.getFacilities();
        for(int i=0; i <newFacilities.size(); i++){
            if(!facilities.contains(newFacilities.get(i))){
                facilities.add(newFacilities.get(i));
            }
        }
        hotel.setFacilities(facilities);
        hotalDb.put(hotelName,hotel);
        return  hotel;
    }
}

