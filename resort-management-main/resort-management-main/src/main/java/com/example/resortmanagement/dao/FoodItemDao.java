package com.example.resortmanagement.dao;

import java.util.List;

import com.example.resortmanagement.model.FoodItem;

public interface FoodItemDao {

    // Method to retrieve all food items
    List<FoodItem> findAll();

    // Method to retrieve a food item by its id
    FoodItem findById(int id);
}
