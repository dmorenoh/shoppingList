package com.app.shopppig.service;

import com.app.shopppig.exception.ShoppingLitSynchronizationException;
import com.app.shopppig.helper.MergeShoppingListHelper;
import com.app.shopppig.helper.SynchronizationException;
import com.app.shopppig.helper.SynchronizerEngineService;
import com.app.shopppig.model.Item;
import com.app.shopppig.model.MergeShoppingListResult;
import com.app.shopppig.model.ShoppingList;
import com.app.shopppig.repository.ShoppingListRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.*;

/**
 * Created by dmorenoh on 1/8/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShoppingListServiceTest {
    @InjectMocks
    private ShoppingListService testSubject;

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Mock
    private MergeShoppingListHelper mergeShoppingListHelper;

    @Mock
    private SynchronizerEngineService synchronizerEngineService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void synchronizeShoppingList_notShoppingListFound_throwException() {
        when(shoppingListRepository.getShoppingListById(anyInt())).thenReturn(null);
        expectedException.expect(ShoppingLitSynchronizationException.class);
        testSubject.synchronizeShoppingList(new ShoppingList());
    }

    @Test
    public void synchronizeShoppingList_mergedConflictsFound_thrownException() {
        when(shoppingListRepository.getShoppingListById(anyInt())).thenReturn(new ShoppingList());
        MergeShoppingListResult mergeResultWithMergeConflicts = aMergeResultWithMergeConflicts();
        when(mergeShoppingListHelper.getMergedConflictItems(anyList(), anyList())).thenReturn(mergeResultWithMergeConflicts);
        expectedException.expect(ShoppingLitSynchronizationException.class);
        testSubject.synchronizeShoppingList(new ShoppingList());
        verify(mergeShoppingListHelper).getMergedConflictItems(anyList(), anyList());
    }

    @Test
    public void synchronizeShoppingList_noMergedConflictsFound_returnShoppingListSyncronized() {
        when(shoppingListRepository.getShoppingListById(anyInt())).thenReturn(new ShoppingList());
        MergeShoppingListResult mergeResultWithoutMergeConflicts = aMergeResultWithoutMergeConflicts();
        when(mergeShoppingListHelper.getMergedConflictItems(anyList(), anyList())).thenReturn(mergeResultWithoutMergeConflicts);
        ShoppingList result = testSubject.synchronizeShoppingList(new ShoppingList());
        verify(mergeShoppingListHelper).getMergedConflictItems(anyList(), anyList());
        assertThat(result.getItems(), equalTo(mergeResultWithoutMergeConflicts.getMergedItemList()));
    }

    @Test
    public void saveOrUpdateItem_synchronizedEngineThrowsException_throwsException(){
        Item persistedItem = new Item();
        Item offlineItem = anExistingItem();
        when(shoppingListRepository.getItem(anyInt())).thenReturn(persistedItem);
        when(synchronizerEngineService.synchronizeItem(offlineItem)).thenThrow(SynchronizationException.class);
        expectedException.expect(SynchronizationException.class);
        testSubject.saveOrUpdateItem(offlineItem);
    }

    @Test
    public void saveOrUpdateItem_newItemToBeSaved_returnSavedItem(){
        Item offlineItem = aNewItem();
        Item newSavedItem = new Item();
        when(shoppingListRepository.saveItem(offlineItem)).thenReturn(newSavedItem);
        Item result = testSubject.saveOrUpdateItem(offlineItem);
        verify(synchronizerEngineService,never()).synchronizeItem(any(Item.class));
        verify(shoppingListRepository).saveItem(offlineItem);
        assertThat(result, equalTo(newSavedItem));
    }

    @Test
    public void saveOrUpdateItem_offlineItemIsNotNew_saveAndReturnSyncItem (){
        Item syncItem = new Item();
        Item offlineItem = anExistingItem();
        when(synchronizerEngineService.synchronizeItem(offlineItem)).thenReturn(syncItem);
        Item result = testSubject.saveOrUpdateItem(offlineItem);
        verify(shoppingListRepository,never()).saveItem(offlineItem);
        assertThat(result, equalTo(syncItem));
    }

    private Item anExistingItem() {
        Item item = new Item();
        item.setId(123);
        return item;
    }

    private Item aNewItem() {
        Item item = new Item();
        item.setId(-1);
        return item;
    }

    private MergeShoppingListResult aMergeResultWithMergeConflicts() {
        MergeShoppingListResult mergeResultWithMergeConflicts = new MergeShoppingListResult();
        List<Item> mergingConflictsList = Arrays.asList(new Item(), new Item());
        mergeResultWithMergeConflicts.setMergedConflictsList(mergingConflictsList);
        return mergeResultWithMergeConflicts;
    }

    private MergeShoppingListResult aMergeResultWithoutMergeConflicts() {
        MergeShoppingListResult mergeResultWithMergeConflicts = new MergeShoppingListResult();
        List<Item> mergedList = Arrays.asList(new Item(), new Item());
        mergeResultWithMergeConflicts.setMergedItemList(mergedList);
        return mergeResultWithMergeConflicts;
    }
}