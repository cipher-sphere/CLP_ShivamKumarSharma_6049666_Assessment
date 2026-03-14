package com.demos.jpaassessment;

import java.util.*;

public class DaoClient {

    static OrderDao dao = new OrderDaoImpl();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        String opt = null;

        do {

            System.out.println("Menu:->\n1-ADD"+'\n'+" 2--VIEW BY ORDER ID"+'\n'+" 3--VIEW BY CUSTOMER NAME");

            int mtype = scan.nextInt();

            processMenu(mtype);

            System.out.println("press y to continue");

            opt = scan.next();

        } while(opt.equalsIgnoreCase("y"));
    }

    public static void processMenu(int mtype) {

        switch(mtype) {

        case 1:
            addOrder();
            break;

        case 2:
            viewOrderByOrderID();
            break;

        case 3:
            viewOrdersByCustName();
            break;

        default:
            System.out.println("Invalid option");
        }
    }

    public static void addOrder() {

        Order o = new Order();

        System.out.println("Enter Customer ID:");
        int cid = scan.nextInt();

        System.out.println("Enter Order Amount:");
        double amt = scan.nextDouble();

        o.setOrder_amt(amt);
        o.setOrder_date(new Date());

        dao.addOrder(o, cid);
    }

    public static void viewOrderByOrderID() {

        System.out.println("Enter Order ID:");

        int id = scan.nextInt();

        Order o = dao.viewOrderById(id);

        if(o!=null)
        {
            System.out.println("Order ID: "+o.getOrder_id());
            System.out.println("Amount: "+o.getOrder_amt());
            System.out.println("Date: "+o.getOrder_date());
        }
        else
        {
            System.out.println("Order Not Found");
        }
    }

    public static void viewOrdersByCustName() {

        System.out.println("Enter Customer Name:");

        String name = scan.next();

        List<Order> list = dao.viewOrdersByCustomerName(name);

        for(Order o : list)
        {
            System.out.println("Order ID: "+o.getOrder_id());
            System.out.println("Amount: "+o.getOrder_amt());
            System.out.println("Date: "+o.getOrder_date());
        }
    }
}