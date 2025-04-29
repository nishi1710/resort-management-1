package com.example.resortmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.resortmanagement.dao.FoodItemDao;
import com.example.resortmanagement.model.FoodItem;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemDao foodItemDao;

    public List<FoodItem> getAllFoodItems() {
        return foodItemDao.findAll();
    }
}
