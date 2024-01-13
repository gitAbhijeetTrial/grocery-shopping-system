package org.grocerybooking.service;

import org.grocerybooking.exception.ResourceNotFoundException;
import org.grocerybooking.model.GroceryItem;
import org.grocerybooking.model.ItemOrder;
import org.grocerybooking.model.Order;
import org.grocerybooking.model.User;

import java.util.List;

public interface UserService {
    List<GroceryItem> getAllAvailableItems() throws ResourceNotFoundException;

    Order placeOrder(Long userId,List<ItemOrder> order) throws ResourceNotFoundException;

    User createUser(User user);
}
