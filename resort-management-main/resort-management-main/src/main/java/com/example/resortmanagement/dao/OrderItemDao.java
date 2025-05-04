package com.example.resortmanagement.dao;

import java.util.List;

import com.example.resortmanagement.model.OrderItem;

public interface OrderItemDao {

    // Method to retrieve all order items
    List<OrderItem> findAll();

    // Method to save an order item
    void save(OrderItem orderItem);

    // Method to find order item by its id
    OrderItem findById(int id);
}
