package org.grocerybooking.service.impl;

import org.grocerybooking.exception.ResourceNotFoundException;
import org.grocerybooking.model.GroceryItem;
import org.grocerybooking.repository.GroceryItemRepository;
import org.grocerybooking.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    GroceryItemRepository groceryItemRepository;
    /**
     * @param item
     * @return
     */
    @Override
    public GroceryItem addGroceryItem(GroceryItem item) {
        return groceryItemRepository.save(item);
    }

    /**
     * @return
     */
    @Override
    public List<GroceryItem> getAllGroceryItem() {
        return groceryItemRepository.findAll();
    }

    /**
     * @param itemId
     * @param itemDetails
     * @return
     */
    @Override
    public GroceryItem updateGroceryItem(Long itemId, GroceryItem itemDetails) throws ResourceNotFoundException {
        Optional<GroceryItem> itemOptional = groceryItemRepository.findById(itemId);

        if(itemOptional.isPresent()) {
            GroceryItem existingItem = itemOptional.get();
            existingItem.setName(itemDetails.getName());
            existingItem.setDescription(itemDetails.getDescription());
            existingItem.setPrice(itemDetails.getPrice());
            return groceryItemRepository.save(existingItem);
        } else {
            throw new ResourceNotFoundException(GroceryItem.class, "id", itemId.toString());
        }
    }

    /**
     * @param itemId
     */
    @Override
    public void removeGroceryItem(Long itemId) {
        Optional<GroceryItem> itemOptional = groceryItemRepository.findById(itemId);
        if(itemOptional.isPresent()) {
            groceryItemRepository.deleteById(itemId);
        }
    }

    /**
     * @param itemId
     * @param newInventory
     * @return
     */
    @Override
    public GroceryItem updateInventory(Long itemId, int newInventory) throws ResourceNotFoundException {
        Optional<GroceryItem> optionalItem = groceryItemRepository.findById(itemId);

        if (optionalItem.isPresent()) {
            GroceryItem existingItem = optionalItem.get();
            existingItem.setInventoryCount(newInventory);
            return groceryItemRepository.save(existingItem);
        } else {
            throw new ResourceNotFoundException(GroceryItem.class, "id", itemId.toString());
        }
    }
}
