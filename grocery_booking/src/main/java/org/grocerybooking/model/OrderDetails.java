package org.grocerybooking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "orderdetails")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderdetailid;

    @ManyToOne
    @JoinColumn(name = "orderid", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "itemid", nullable = false)
    private GroceryItem groceryItem;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unitprice")
    private Double unitPrice;


}
