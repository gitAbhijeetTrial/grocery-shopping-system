package org.grocerybooking.service.impl;

import org.grocerybooking.exception.ResourceNotFoundException;
import org.grocerybooking.model.*;
import org.grocerybooking.repository.GroceryItemRepository;
import org.grocerybooking.repository.OrderDetailsRepository;
import org.grocerybooking.repository.OrderRepository;
import org.grocerybooking.repository.UserRepository;
import org.grocerybooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    GroceryItemRepository groceryItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<GroceryItem> getAllAvailableItems() throws ResourceNotFoundException {
        List<GroceryItem> groceryItemList = groceryItemRepository.findAllAvailableItems();
        if (groceryItemList.isEmpty()) {
            throw new ResourceNotFoundException(GroceryItem.class, "Items are not available");
        } else {
            return groceryItemList;
        }
    }

    @Override
    public Order placeOrder(Long userId, List<ItemOrder> itemOrders) throws ResourceNotFoundException {

        // Check if user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        Order order = new Order();
        order.setUserId(user);
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        Order placedOrder = orderRepository.save(order);

        double total_price = 0.0;
        List<OrderDetails> detailsList = new ArrayList<>();

        List<GroceryItem> updatedGroceryItem = new ArrayList<>();

        // Fetch all GroceryItems in one go if possible
        List<GroceryItem> groceryItemList = groceryItemRepository.findAllAvailableItems();

        for (ItemOrder itemOrder : itemOrders) {

            GroceryItem groceryItem = groceryItemList.stream()
                    .filter(item -> item.getItemid().intValue() == itemOrder.getItemId())
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Item not found with ID: " + itemOrder.getItemId()));


            double itemPrice = groceryItem.getPrice();
            total_price += itemPrice * itemOrder.getQuantity();

            if (groceryItem.getInventoryCount() < itemOrder.getQuantity()) {
                throw new IllegalArgumentException("Insufficient inventory for item ID: " + itemOrder.getItemId());
            }

            // Update Inventory
            groceryItem.setInventoryCount(groceryItem.getInventoryCount() - itemOrder.getQuantity());

            // Prepare OrderDetails
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrder(order);
            orderDetails.setGroceryItem(groceryItem);
            orderDetails.setQuantity(itemOrder.getQuantity());
            orderDetails.setUnitPrice(itemPrice);
            detailsList.add(orderDetails);
            updatedGroceryItem.add(groceryItem);
        }

        // Batch save OrderDetails
        orderDetailsRepository.saveAll(detailsList);
        groceryItemRepository.saveAll(updatedGroceryItem);

        placedOrder.setTotalPrice(total_price);
        placedOrder.setStatus("ORDER_PLACED");
        return orderRepository.save(placedOrder);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
