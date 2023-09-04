package com.driver.controllers;


import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/hotel")
public class HotelManagementController {

//    @Autowired
    Service service;
    @PostMapping("/add-hotel")
    public String addHotel(@RequestBody Hotel hotel){

        //You need to add an hotel to the database
        //incase the hotelName is null or the hotel Object is null return an empty a FAILURE
        //Incase somebody is trying to add the duplicate hotelName return FAILURE
        //in all other cases return SUCCESS after successfully adding the hotel to the hotelDb.

        return service.addHotel(hotel);
    }

    @PostMapping("/add-user")
    public Integer addUser(@RequestBody User user){

        //You need to add a User Object to the database
        //Assume that user will always be a valid user and return the aadharCardNo of the user

       return service.addUser(user);
    }

    @GetMapping("/get-hotel-with-most-facilities")
    public String getHotelWithMostFacilities(){

        //Out of all the hotels we have added so far, we need to find the hotelName with most no of facilities
        //Incase there is a tie return the lexicographically smaller hotelName
        //Incase there is not even a single hotel with atleast 1 facility return "" (empty string)

        return service.getHotelWithMostFacilities();
    }

    @PostMapping("/book-a-room")
    public int bookARoom(@RequestBody Booking booking){

        //The booking object coming from postman will have all the attributes except bookingId and amountToBePaid;
        //Have bookingId as a random UUID generated String
        //save the booking Entity and keep the bookingId as a primary key
        //Calculate the total amount paid by the person based on no. of rooms booked and price of the room per night.
        //If there arent enough rooms available in the hotel that we are trying to book return -1 
        //in other case return total amount paid

        return service.bookARoom(booking);
    }
    
    @GetMapping("/get-bookings-by-a-person/{aadharCard}")
    public int getBookings(@PathVariable("aadharCard")Integer aadharCard)
    {
        //In this function return the bookings done by a person 
        return service.getBookings(aadharCard);
    }

    @PutMapping("/update-facilities")
    public Hotel updateFacilities(@RequestParam("newFacilities") List<Facility> newFacilities, @RequestParam("hotelName") String hotelName){

        //We are having a new facilites that a hotel is planning to bring.
        //If the hotel is already having that facility ignore that facility otherwise add that facility in the hotelDb
        //return the final updated List of facilities and also update that in your hotelDb
        //Note that newFacilities can also have duplicate facilities possible
        return service.updateFacilities(newFacilities , hotelName);
    }

}
