// package com.example.resortmanagement.command;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// import com.example.resortmanagement.model.FoodItem;
// import com.example.resortmanagement.model.OrderItem;
// import com.example.resortmanagement.repository.FoodItemRepository;
// import com.example.resortmanagement.repository.OrderItemRepository;

// @Component
// public class PlaceOrderCommand implements OrderCommand {

//     @Autowired
//     private FoodItemRepository foodRepo;

//     @Autowired
//     private OrderItemRepository orderRepo;

//     private List<OrderItem> orderItems = new ArrayList<>();
//     private double total = 0;

//     @Override
//     public void execute(Map<String, String> params) {
//         orderRepo.deleteAll();
//         orderItems.clear();
//         total = 0;

//         for (String key : params.keySet()) {
//             if (key.startsWith("qty_")) {
//                 int id = Integer.parseInt(key.substring(4));
//                 int qty = Integer.parseInt(params.get(key));
//                 if (qty > 0) {
//                     FoodItem item = foodRepo.findById(id).orElse(null);
//                     if (item != null) {
//                         OrderItem order = new OrderItem();
//                         order.setName(item.getName());
//                         order.setQty(qty);
//                         order.setPrice(qty * item.getPrice());
//                         total += order.getPrice();
//                         orderRepo.save(order);
//                         orderItems.add(order);
//                     }
//                 }
//             }
//         }
//     }

//     public List<OrderItem> getOrderItems() {
//         return orderItems;
//     }

//     public double getTotal() {
//         return total;
//     }
// }

package com.example.resortmanagement.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.resortmanagement.dao.FoodItemDao;
import com.example.resortmanagement.dao.OrderItemDao;
import com.example.resortmanagement.model.FoodItem;
import com.example.resortmanagement.model.OrderItem;

@Component
public class PlaceOrderCommand implements OrderCommand {

    @Autowired
    private FoodItemDao foodItemDao;

    @Autowired
    private OrderItemDao orderItemDao;

    private List<OrderItem> orderItems = new ArrayList<>();
    private double total = 0;

    @Override
    public void execute(Map<String, String> params) {
        // orderItemDao.deleteAll();  // Assuming you have this method in OrderItemDao
        // orderItems.clear();
        // total = 0;

        for (String key : params.keySet()) {
            if (key.startsWith("qty_")) {
                int id = Integer.parseInt(key.substring(4));
                int qty = Integer.parseInt(params.get(key));
                if (qty > 0) {
                    FoodItem item = foodItemDao.findById(id);  // Using DAO method for foodItem
                    if (item != null) {
                        OrderItem order = new OrderItem();
                        order.setName(item.getName());
                        order.setQty(qty);
                        order.setPrice(qty * item.getPrice());
                        total += order.getPrice();
                        orderItemDao.save(order);  // Using DAO method for saving orderItem
                        orderItems.add(order);
                    }
                }
            }
        }
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public double getTotal() {
        return total;
    }
}
