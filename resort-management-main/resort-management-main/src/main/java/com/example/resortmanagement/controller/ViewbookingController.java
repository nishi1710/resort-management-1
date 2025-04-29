// package com.example.resortmanagement.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;

// import com.example.resortmanagement.model.Booking;
// import com.example.resortmanagement.repository.BookingRepository;

// import jakarta.servlet.http.HttpSession;

// @Controller
// public class ViewbookingController {

//     @Autowired
//     private BookingRepository bookingRepository;

//     @GetMapping("/my_bookings")
// public String getUserBookings(HttpSession session, Model model) {
//     String email = (String) session.getAttribute("userEmail");
//     if (email == null) {
//         return "redirect:/login";  // No email in session → not logged in
//     }

//     List<Booking> userBookings = bookingRepository.findByEmail(email);
//     model.addAttribute("bookings", userBookings);
//     model.addAttribute("userEmail", email);
//     return "my_bookings";
// }

    
// }
package com.example.resortmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.resortmanagement.model.Booking;
import com.example.resortmanagement.dao.BookingDao;

import jakarta.servlet.http.HttpSession;

@Controller
public class ViewbookingController {

    @Autowired
    private BookingDao bookingDao;

    @GetMapping("/my_bookings")
public String getUserBookings(HttpSession session, Model model) {
    String email = (String) session.getAttribute("userEmail");
    if (email == null) {
        return "redirect:/login";  // No email in session → not logged in
    }

    List<Booking> userBookings = bookingDao.findByEmail(email);
    model.addAttribute("bookings", userBookings);
    model.addAttribute("userEmail", email);
    return "my_bookings";
}

    
}
