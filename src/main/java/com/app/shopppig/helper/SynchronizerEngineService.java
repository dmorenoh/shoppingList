package com.app.shopppig.helper;

import com.app.shopppig.model.Item;
import com.app.shopppig.repository.ShoppingListRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by dmorenoh on 3/8/16.
 */
@Service
public class SynchronizerEngineService {
    @Inject
    private ShoppingListRepository shoppingListRepository;

    public Item synchronizeItem(Item offlineItem) {
        Item storedItem = shoppingListRepository.getItem(offlineItem.getId());
        if (storedItem == null) {
            throw new SynchronizationException("Item is not longer available");
        }

        if (storedItem.getLastUpdateDate().after(offlineItem.getLastUpdateDate())) {
            return storedItem;
        }

        if (storedItem.isChecked()) {
            if (offlineItem.isChecked() && storedItem.getQuantity() == offlineItem.getQuantity()) {
                return storedItem;
            } else {
                throw new SynchronizationException("Merge conflict: Item has been already checked");
            }
        }

        return null;
    }
}
