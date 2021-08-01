package com.senla.model;

import java.util.List;

public class Guest extends AEntity {

    private String name;
    private Integer age;
    private List<Order> orders;


    public Guest() {
    }

    public Guest(Guest guest) {
        setId(guest.getId());
        this.name = guest.getName();
        this.age = guest.getAge();
        this.orders = guest.getOrders();
    }

    public Guest(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return (getId() == null ? "" : getId() + ". ")
                + name
                + ", " + age + " age";
    }
}
