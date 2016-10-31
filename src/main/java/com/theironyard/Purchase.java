package com.theironyard;

import javax.persistence.*;

/**
 * Created by michaelplott on 10/24/16.
 */
@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String cID;

    @Column(nullable = false)
    String date;

    @Column(nullable = false)
    String cCard;

    @Column(nullable = false)
    String ccv;

    @Column(nullable = false)
    String category;

    @ManyToOne
    Customer customer;

    public Purchase() {
    }

    public Purchase(String cID, String date, String cCard, String ccv, String category) {
        this.cID = cID;
        this.date = date;
        this.cCard = cCard;
        this.ccv = ccv;
        this.category = category;
    }

    public Purchase(String cID, String date, String cCard, String ccv, String category, Customer customer) {
        this.cID = cID;
        this.date = date;
        this.cCard = cCard;
        this.ccv = ccv;
        this.category = category;
        this.customer = customer;
    }
}
