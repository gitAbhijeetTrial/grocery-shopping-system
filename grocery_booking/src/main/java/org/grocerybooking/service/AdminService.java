package org.grocerybooking.service;

import org.grocerybooking.exception.ResourceNotFoundException;
import org.grocerybooking.model.GroceryItem;

import java.util.List;

public interface AdminService {
    GroceryItem addGroceryItem(GroceryItem item);

    List<GroceryItem> getAllGroceryItem();

    GroceryItem updateGroceryItem(Long itemId, GroceryItem itemDetails) throws ResourceNotFoundException;

    void removeGroceryItem(Long itemId);

    GroceryItem updateInventory(Long itemId, int newInventory) throws ResourceNotFoundException;
}
