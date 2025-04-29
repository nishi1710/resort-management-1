package com.example.resortmanagement.dao;

import com.example.resortmanagement.model.OrderItem;
import java.util.List;

public interface OrderItemDao {

    // Method to retrieve all order items
    List<OrderItem> findAll();

    // Method to save an order item
    void save(OrderItem orderItem);

    // Method to find order item by its id
    OrderItem findById(int id);
}
