// package com.example.resortmanagement.controller;


// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.example.resortmanagement.command.OrderInvoker;
// import com.example.resortmanagement.command.PlaceOrderCommand;
// import com.example.resortmanagement.repository.FoodItemRepository;

// @Controller
// public class FoodController {

//     @Autowired
//     private FoodItemRepository foodRepo;

//     @Autowired
//     private PlaceOrderCommand placeOrderCommand;

//     @Autowired
//     private OrderInvoker invoker;

//     @GetMapping("/food")
//     public String showFoodItems(Model model) {
//         model.addAttribute("items", foodRepo.findAll());
//         return "index";
//     }

//     @PostMapping("/checkout")
//     public String checkout(@RequestParam Map<String, String> params, Model model) {
//         invoker.setCommand(placeOrderCommand);
//         invoker.placeOrder(params);

//         model.addAttribute("orderItems", placeOrderCommand.getOrderItems());
//         model.addAttribute("total", placeOrderCommand.getTotal());
//         return "bill";
//     }

//     @PostMapping("/dashboard")
//     public String completeOrder() {
//         return "dashboard";
//     }
// }

package com.example.resortmanagement.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.resortmanagement.command.OrderInvoker;
import com.example.resortmanagement.command.PlaceOrderCommand;
import com.example.resortmanagement.service.FoodItemService;

@Controller
public class FoodController {

    @Autowired
    private FoodItemService foodItemService;  // Using FoodItemService now

    @Autowired
    private PlaceOrderCommand placeOrderCommand;

    @Autowired
    private OrderInvoker invoker;

    @GetMapping("/food")
    public String showFoodItems(Model model) {
        // Fetching food items using FoodItemService instead of FoodItemRepository
        model.addAttribute("items", foodItemService.getAllFoodItems());
        return "index";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam Map<String, String> params, Model model) {
        invoker.setCommand(placeOrderCommand);
        invoker.placeOrder(params);

        model.addAttribute("orderItems", placeOrderCommand.getOrderItems());
        model.addAttribute("total", placeOrderCommand.getTotal());
        return "bill";
    }

    @PostMapping("/dashboard")
    public String completeOrder() {
        return "dashboard";
    }
}

