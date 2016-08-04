package com.app.shopppig.exception;

import com.app.shopppig.model.Item;

import java.util.List;

/**
 * Created by dmorenoh on 1/8/16.
 */
public class ShoppingLitSynchronizationException extends RuntimeException {
    private List<Item> mergedConflictItems;

    public List<Item> getMergedConflictItems() {
        return mergedConflictItems;
    }

    public void setMergedConflictItems(List<Item> mergedConflictItems) {
        this.mergedConflictItems = mergedConflictItems;
    }
}
