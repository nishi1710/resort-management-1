package com.example.resortmanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.resortmanagement.model.OrderItem;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Retrieve all order items
    @Override
    public List<OrderItem> findAll() {
        String sql = "SELECT * FROM order_item";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderItem.class));
    }

    // Save an order item
    @Override
    public void save(OrderItem orderItem) {
        String sql = "INSERT INTO order_item (name, qty, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, orderItem.getName(), orderItem.getQty(), orderItem.getPrice());
    }

    // Find an order item by its id
    @Override
    public OrderItem findById(int id) {
        String sql = "SELECT * FROM order_item WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(OrderItem.class));
    }
}
