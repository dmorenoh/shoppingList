package com.app.shopppig.controller;

import com.app.shopppig.model.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by dmorenoh on 1/8/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShoppingListControllerTest {
    @InjectMocks
    private ShoppingListController shoppingListController;

    @Test
    private void synchronizeShoppingList_itemWithMergeConflict_throwException(){
        List<Item> offlineShoppingList = anOfflineMergedConflictShoppingList();
        //when()
        //shoppingListController.sychronizeAndPersistList(offlineShoppingList);
    }

    private List<Item> anOfflineMergedConflictShoppingList() {
        List<Item> offLineShoppingList= new ArrayList<Item>();
        return offLineShoppingList;
    }
}