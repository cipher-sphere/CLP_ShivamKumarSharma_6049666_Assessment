package com.demos.jpaassessment;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name="abes_customer")
public class Customer {

    @Id
    private int customer_id;

    @Column(nullable=false)
    private String customer_name;

    @OneToMany(mappedBy="customer")
    private Set<Order> orders;

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}