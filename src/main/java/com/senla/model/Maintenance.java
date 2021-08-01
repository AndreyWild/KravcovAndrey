package com.senla.model;


import com.senla.api.service.IPriceService;

import java.util.ArrayList;
import java.util.List;

public class Maintenance extends AEntity implements IPriceService {

    private String name;
    private Double price;

    public Maintenance() {
    }

    public Maintenance(Maintenance maintenance) {
        setId(maintenance.getId());
        this.name = maintenance.getName();
        this.price = maintenance.getPrice();
    }

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

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return (getId() == null ? "" : getId() + ". ") + name + " - " + price + "$";
    }
}
