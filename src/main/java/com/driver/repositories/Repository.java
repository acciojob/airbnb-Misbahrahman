package com.driver.repositories;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import io.swagger.models.auth.In;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


@org.springframework.stereotype.Repository
public class Repository {

    HashMap<String , Hotel> hotelMap;
    HashMap<Integer , User> userMap;
    List<Hotel> hotelList;
    HashMap<Integer , Integer> bookingMap;


    public Repository(){
        hotelMap = new HashMap<>();
        userMap = new HashMap<>();
        hotelList = new ArrayList<>();
        bookingMap = new HashMap<>();
    }
    public String addHotel(Hotel hotel) {
        if(hotelMap.containsKey(hotel.getHotelName()))return "FAILURE";
        hotelMap.put(hotel.getHotelName() , hotel);
        hotelList.add(hotel);
        return "SUCCESS";
    }


    public Integer addUser(User user) {
        userMap.put(user.getaadharCardNo() , user);
        return user.getaadharCardNo();
    }

    public String getHotelWithMostFacilities() {
        hotelList.sort((a, b) -> {
            return (b.getFacilities().size() - a.getFacilities().size());
        });
        Hotel hotel = hotelList.get(0);
        if(hotel.getFacilities().isEmpty())return "";
        return hotel.getHotelName();
    }


    public int bookARoom(Booking booking) {
        Hotel hotel = hotelMap.get(booking.getHotelName());
        if(booking.getNoOfRooms() > hotel.getAvailableRooms()) {
            System.out.println("see " + booking.getNoOfRooms() + " " + hotel.getAvailableRooms());
            return -1;
        }
        int price = hotel.getPricePerNight() * booking.getNoOfRooms();
        booking.setAmountToBePaid(price);
        updateBooking(booking.getBookingAadharCard());
        return  price;
    }

    private void updateBooking(int bookingAadharCard) {
        if(bookingMap.containsKey(bookingAadharCard))bookingMap.put(bookingAadharCard , bookingMap.get(bookingAadharCard) + 1);
        else bookingMap.put(bookingAadharCard , 1);
    }


    public int getBookings(Integer aadharCard) {
        if(bookingMap.containsKey(aadharCard))return bookingMap.get(aadharCard);
        return 0;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        Hotel hotel = hotelMap.get(hotelName);
        List<Facility> list = hotel.getFacilities();
        for(Facility x : newFacilities){
            if(!list.contains(x))list.add(x);
        }
        return hotel;
    }
}
