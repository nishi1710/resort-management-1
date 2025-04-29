package com.example.resortmanagement.dao;

import java.util.List;

import com.example.resortmanagement.model.Booking;

public interface BookingDao {
    void saveBooking(Booking booking);
    List<Booking> findByEmail(String email);
}
