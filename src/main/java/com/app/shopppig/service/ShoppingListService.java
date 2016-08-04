package com.app.shopppig.service;

import com.app.shopppig.exception.ShoppingLitSynchronizationException;
import com.app.shopppig.helper.MergeShoppingListHelper;
import com.app.shopppig.helper.SynchronizerEngineService;
import com.app.shopppig.model.Item;
import com.app.shopppig.model.MergeShoppingListResult;
import com.app.shopppig.model.ShoppingList;
import com.app.shopppig.repository.ShoppingListRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by dmorenoh on 1/8/16.
 */
@Service
public class ShoppingListService {

    @Inject
    private ShoppingListRepository shoppingListRepository;

    @Inject
    private MergeShoppingListHelper mergeShoppingListHelper;

    @Inject
    private SynchronizerEngineService synchronizerEngine;

    public Item saveOrUpdateItem(Item offlineItem) {
        if (offlineItem.getId() < 0) {
            return shoppingListRepository.saveItem(offlineItem);
        }

        return synchronizerEngine.synchronizeItem(offlineItem);
    }

    public ShoppingList synchronizeShoppingList(ShoppingList shoppingList) throws ShoppingLitSynchronizationException {
        ShoppingList persistedShoppingList = shoppingListRepository.getShoppingListById(shoppingList.getId());
        if (persistedShoppingList == null) {
            throw new ShoppingLitSynchronizationException();
        }

        MergeShoppingListResult mergedResult = mergeShoppingListHelper.getMergedConflictItems(persistedShoppingList.getItems(), shoppingList.getItems());

        if (!CollectionUtils.isEmpty(mergedResult.getMergedConflictsList())) {
            ShoppingLitSynchronizationException ex = new ShoppingLitSynchronizationException();
            ex.setMergedConflictItems(mergedResult.getMergedConflictsList());
            throw ex;
        }
        persistedShoppingList.setItem(mergedResult.getMergedItemList());

        return persistedShoppingList;
    }
}
