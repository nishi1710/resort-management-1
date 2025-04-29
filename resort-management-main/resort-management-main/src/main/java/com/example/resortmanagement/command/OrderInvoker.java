package com.example.resortmanagement.command;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class OrderInvoker {
    private OrderCommand command;

    public void setCommand(OrderCommand command) {
        this.command = command;
    }

    public void placeOrder(Map<String, String> params) {
        if (command != null) {
            command.execute(params);
        }
    }
}
