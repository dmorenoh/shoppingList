package com.app.shopppig.model;

import java.util.List;
import java.util.Map;

/**
 * Created by dmorenoh on 1/8/16.
 */
public class ShoppingList {
    private int id;
    private String name;
    private List<Item> items;
    private User owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItem(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }
}
