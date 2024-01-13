package org.grocerybooking.controller;

import org.grocerybooking.exception.ResourceNotFoundException;
import org.grocerybooking.model.GroceryItem;
import org.grocerybooking.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //Add new grocery item
    @PostMapping("/items")
    public ResponseEntity<GroceryItem> addGroceryItem(@RequestBody GroceryItem item) {
        GroceryItem newItem = adminService.addGroceryItem(item);
        return ResponseEntity.ok(newItem);
    }

    //View all grocery item
    @GetMapping("/items")
    public ResponseEntity<List<GroceryItem>> getAllGroceryItem() {
        List<GroceryItem> groceryItems= adminService.getAllGroceryItem();
        return ResponseEntity.ok(groceryItems);
    }

    //Update an existing grocery item
    @PutMapping("/items/{itemId}")
    public ResponseEntity<GroceryItem> updateGroceryItem(@PathVariable Long itemId, @RequestBody GroceryItem itemDetails) throws ResourceNotFoundException {
        GroceryItem updatedItem = adminService.updateGroceryItem(itemId,itemDetails);
        return ResponseEntity.ok(updatedItem);
    }

    //Remove a grocery item
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<?> removeGroceryItem(@PathVariable Long itemId) {
            adminService.removeGroceryItem(itemId);
            return ResponseEntity.ok().build();
    }

    //Mange inventory for a grocery item
    @PatchMapping("/items/{itemId}/inventory")
    public ResponseEntity<GroceryItem> updateInventory(@PathVariable Long itemId, @RequestParam int newInventory) throws ResourceNotFoundException {
        GroceryItem updatedItem = adminService.updateInventory(itemId,newInventory);
        return ResponseEntity.ok(updatedItem);
    }
}