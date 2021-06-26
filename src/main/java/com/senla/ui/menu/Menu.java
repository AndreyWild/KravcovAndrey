package com.senla.ui.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private String name;

    private List<MenuItem> items = new ArrayList<>();

    public Menu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public void addItem(MenuItem menuItem) {
        items.add(menuItem);
    }
}
