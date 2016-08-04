package com.app.shopppig.helper;

import com.app.shopppig.model.Item;
import com.app.shopppig.model.MergeShoppingListResult;
import com.app.shopppig.model.ShoppingList;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dmorenoh on 1/8/16.
 */
@Component
public class MergeShoppingListHelper {

    public MergeShoppingListResult getMergedConflictItems(List<Item> persistedItemList, List<Item> requestedItemList) {
        return null;
    }
}
