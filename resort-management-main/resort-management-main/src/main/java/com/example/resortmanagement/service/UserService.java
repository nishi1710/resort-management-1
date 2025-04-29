package com.example.resortmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.resortmanagement.dao.UserDao;
import com.example.resortmanagement.entity.User;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User register(User user) {
        user.setRole("CUSTOMER"); // Set default role
        System.out.println("Registering user:");
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Role: " + user.getRole());
        
        userDao.save(user);  // Save the user (no return because your UserDao.save() is void)
        return user;         // Returning the same user object after saving
    }

    public User authenticate(String email, String password) {
        User user = userDao.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean emailExists(String email) {
        return userDao.findByEmail(email) != null;
    }
}
