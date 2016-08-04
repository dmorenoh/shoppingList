package com.app.shopppig.controller;

import com.app.shopppig.model.Item;
import com.app.shopppig.model.ShoppingList;
import com.app.shopppig.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by dmorenoh on 31/7/16.
 */
@RestController
public class ShoppingListController {

    @Inject
    private ShoppingListService shoppingListService;

    @RequestMapping(value = "/ShoppingList/{shoppingId}/", method = RequestMethod.POST)
    public List<Item> updateItem(@PathVariable("shoppingId") String shoppingId,
                                              @RequestBody ShoppingList shoppingList) {
        ShoppingList synchronizedShoppingList = shoppingListService.synchronizeShoppingList(shoppingList);
        return synchronizedShoppingList.getItems();
    }
}
