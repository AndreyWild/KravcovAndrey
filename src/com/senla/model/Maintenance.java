package com.senla.model;

import com.senla.api.service.IAbstractInt;

public class Maintenance extends AEntity implements IAbstractInt {

    private String name;
    private Double price;

    public Maintenance(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Service(" + getId() + ") - '" + name + "' (" + price + "$)";
    }
}
