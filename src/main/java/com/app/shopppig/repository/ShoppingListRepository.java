package com.app.shopppig.repository;

import com.app.shopppig.model.Item;
import com.app.shopppig.model.ShoppingList;
import org.springframework.stereotype.Component;

/**
 * Created by dmorenoh on 1/8/16.
 */
@Component
public class ShoppingListRepository {

    public ShoppingList getShoppingListById(int id) {
        return null;
    }

    public void createShoppingList(ShoppingList shoppingList) {
    }

    public ShoppingList saveShoppingList(ShoppingList shoppingList) {
        return null;
    }

    public Item saveItem(Item item) {
        return null;
    }

    public Item getItem(int id) {
        return null;
    }

    public Item updateItem(Item offlineItem) {
        return null;
    }
}
