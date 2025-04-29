package com.example.resortmanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.resortmanagement.model.FoodItem;

@Repository
public class FoodItemDaoImpl implements FoodItemDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retrieve all food items
    @Override
    public List<FoodItem> findAll() {
        String sql = "SELECT * FROM food_item";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(FoodItem.class));
    }

    // Retrieve a food item by its id
    @Override
    public FoodItem findById(int id) {
        String sql = "SELECT * FROM food_item WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(FoodItem.class));
    }
}
