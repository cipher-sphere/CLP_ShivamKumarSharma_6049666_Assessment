package com.demos.jpaassessment;

import jakarta.persistence.*;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityManager em = emf.createEntityManager();

    @Override
    public void addOrder(Order o, int cid) {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Customer c = em.find(Customer.class, cid);

        if(c == null)
        {
            System.out.println("Customer not found");
            tx.rollback();
            return;
        }

        o.setCustomer(c);

        em.persist(o);

        tx.commit();

        System.out.println("Order Added Successfully");
    }

    @Override
    public Order viewOrderById(int id) {

        return em.find(Order.class, id);
    }

    @Override
    public List<Order> viewOrdersByCustomerName(String name) {

        Query q = em.createQuery(
                "select o from Order o where o.customer.customer_name=:n");

        q.setParameter("n", name);

        return q.getResultList();
    }
}