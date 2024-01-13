package org.grocerybooking.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "passwordhash")
    private String password;

    @Column(name = "role")
    private String role;

}
