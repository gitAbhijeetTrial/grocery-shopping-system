package org.grocerybooking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderid;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User userId;

    @Column(name = "totalprice")
    private Double totalPrice;

    @Column(name = "orderdate", nullable = false)
    private Timestamp orderDate;

    @Column(name = "status")
    private String status;

    // Constructors
    public Order() {
        this.orderDate = new Timestamp( System.currentTimeMillis() );
    }

    // Additional methods like equals, hashCode, and toString
}
