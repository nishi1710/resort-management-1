package com.example.resortmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.resortmanagement.dao.ResortDao;
import com.example.resortmanagement.entity.User;
import com.example.resortmanagement.model.Resort;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ResortDao resortDao;

    private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        return user != null && "ADMIN".equals(user.getRole());
    }

    @GetMapping("/resorts")
    public String viewResorts(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/access-denied";

        List<Resort> resorts = resortDao.findAll();
        model.addAttribute("resorts", resorts);
        return "admin_dashboard"; // HTML page in templates/admin/resorts.html
    }

    @GetMapping("/resorts/add")
public String showAddForm(Model model, HttpSession session) {
    if (!isAdmin(session)) return "redirect:/access-denied"; // Ensure only admins can access this
    model.addAttribute("resort", new Resort());
    return "add_resort"; // Corrected to match the file name 'add_resort.html'
}

@PostMapping("/resorts/add")
public String addResort(@ModelAttribute Resort resort, HttpSession session) {
    if (!isAdmin(session)) return "redirect:/access-denied"; // Only allow Admins

    resortDao.save(resort); // Save the new resort into database

    return "redirect:/dashboard"; // Redirect to the admin resorts dashboard
}



@GetMapping("/resorts/edit/{id}")
public String showEditForm(@PathVariable Long id, Model model, HttpSession session) {
    if (!isAdmin(session)) return "redirect:/access-denied";

    Resort resort = resortDao.findById(id);
    model.addAttribute("resort", resort);
    return "edit_resort"; // HTML page where the admin can edit the resort details
}

@PostMapping("/resorts/edit/{id}")
public String updateResort(@PathVariable Long id, @ModelAttribute Resort resort, HttpSession session, Model model) {
    if (!isAdmin(session)) return "redirect:/access-denied";

    Resort existingResort = resortDao.findById(id);
    if (existingResort == null) {
        return "redirect:/dashboard"; // Resort not found, redirect to dashboard
    }

    // Update only the other fields, do not change resortId
    existingResort.setName(resort.getName());
    existingResort.setLocation(resort.getLocation());
    existingResort.setDescription(resort.getDescription());
    existingResort.setLongdescription(resort.getLongdescription());

    // Save the updated resort
    resortDao.save(existingResort);

    // Remove cached resorts list from the session (if cached)
    session.removeAttribute("resorts");

    // Fetch the updated list of resorts from the database
    List<Resort> resorts = resortDao.findAll();

    // Add the fresh resorts list to the model to display in the view
    model.addAttribute("resorts", resorts);

    // Redirect to the admin resorts page with the updated list
    return "admin_dashboard";  // This will render the updated data on the dashboard
}


    // Delete the resort
@GetMapping("/resorts/delete/{id}")
public String deleteResort(@PathVariable Long id, HttpSession session) {
    if (!isAdmin(session)) return "redirect:/access-denied";

    // Remove the resort from the database
    resortDao.delete(id);
    return "redirect:/dashboard"; // Redirect to the resorts list after deleting
}


    @GetMapping("/admin/resort/management")
    public String viewAllResorts(Model model) {
        List<Resort> resorts = resortDao.findAll();
        model.addAttribute("resorts", resorts);
        return "redirect:/dashboard"; // Fix: Corrected the redirect to /admin/resorts
    }

    @GetMapping("/logout")
public String logout(HttpSession session) {
    // Invalidate the session to log the user out
    session.invalidate();
    return "redirect:/login?logout"; // Redirect to login page with logout message
}

}
