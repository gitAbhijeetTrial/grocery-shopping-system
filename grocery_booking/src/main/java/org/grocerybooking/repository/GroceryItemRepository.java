package org.grocerybooking.repository;

import org.grocerybooking.model.GroceryItem;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {

    @Query(value = "select itemid,name,price,description,inventorycount from grocery_items where inventorycount > 0 ;", nativeQuery = true)
    List<GroceryItem> findAllAvailableItems();
}
