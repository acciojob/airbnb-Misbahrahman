package com.driver.services;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repositories.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Service
public class Service {
//    @Autowired
    Repository repository;
    public String addHotel(Hotel hotel) {

        //null
        if(hotel == null || hotel.getHotelName() == null)return "FAILURE";
        //duplicate
        return repository.addHotel(hotel);
    }

    public Integer addUser(User user) {
        return repository.addUser(user);
    }

    public String getHotelWithMostFacilities() {
        return repository.getHotelWithMostFacilities();
    }

    public int bookARoom(Booking booking) {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();

        // Convert the UUID to a String
        String bookingId = uuid.toString();

        booking.setBookingId(bookingId);
        return repository.bookARoom(booking);
    }

    public int getBookings(Integer aadharCard) {
       return repository.getBookings(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        return repository.updateFacilities(newFacilities , hotelName);
    }
}
