package com.app.shopppig.model;

import java.util.List;

/**
 * Created by dmorenoh on 2/8/16.
 */
public class MergeShoppingListResult {
    private List<Item> mergedItemList;
    private List<Item> mergedConflictsList;

    public List<Item> getMergedItemList() {
        return mergedItemList;
    }

    public void setMergedItemList(List<Item> mergedItemList) {
        this.mergedItemList = mergedItemList;
    }

    public List<Item> getMergedConflictsList() {
        return mergedConflictsList;
    }

    public void setMergedConflictsList(List<Item> mergedConflictsList) {
        this.mergedConflictsList = mergedConflictsList;
    }
}
