package org.grocerybooking.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ItemOrder {

    private Integer itemId;

    private Integer quantity;

}
