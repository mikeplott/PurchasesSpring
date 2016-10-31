package com.theironyard;

import javax.persistence.*;

/**
 * Created by michaelplott on 10/24/16.
 */
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String email;

    public Customer() {
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
