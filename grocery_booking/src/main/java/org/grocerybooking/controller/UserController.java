package org.grocerybooking.controller;

import org.grocerybooking.exception.ResourceNotFoundException;
import org.grocerybooking.model.GroceryItem;
import org.grocerybooking.model.ItemOrder;
import org.grocerybooking.model.Order;
import org.grocerybooking.model.User;
import org.grocerybooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // View all available grocery items
    @GetMapping("/items")
    public ResponseEntity<List<GroceryItem>> getAllAvailableItems() throws ResourceNotFoundException {
        List<GroceryItem> items = userService.getAllAvailableItems();
        return ResponseEntity.ok(items);
    }

    // Place an order
    @PostMapping("/order/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Order> placeOrder(@PathVariable Long userId, @RequestBody List<ItemOrder> order) throws ResourceNotFoundException {
        Order placedOrder = userService.placeOrder(userId,order);
        if(placedOrder == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(placedOrder);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

}
