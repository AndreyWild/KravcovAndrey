package com.senla.model;

public class Guest extends AEntity {

    private String name;
    private Integer age;
    private Order order;

    public Guest() {
    }

    public Guest(Guest guest) {
        setId(guest.getId());
        this.name = guest.getName();
        this.age = guest.getAge();
        this.order = guest.getOrder();
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return (getId() == null ? "" : getId() + ". ")
                + name
                + ", " + age + " age";
    }
}
