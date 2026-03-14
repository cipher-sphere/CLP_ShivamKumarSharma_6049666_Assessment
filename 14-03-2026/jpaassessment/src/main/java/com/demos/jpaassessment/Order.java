package com.demos.jpaassessment;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="abes_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    @Temporal(TemporalType.DATE)
    private Date order_date;

    private double order_amt;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    public int getOrder_id() {
        return order_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public double getOrder_amt() {
        return order_amt;
    }

    public void setOrder_amt(double order_amt) {
        this.order_amt = order_amt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}