package com.app.shopppig.helper;

import com.app.shopppig.model.Item;
import com.app.shopppig.repository.ShoppingListRepository;
import com.app.shopppig.utils.builders.ItemBuilder;
import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static com.app.shopppig.utils.builders.ItemBuilder.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by dmorenoh on 3/8/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class SynchronizerEngineServiceTest {

    @InjectMocks
    private SynchronizerEngineService testSubject;

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void synchronizeItem_offLineItemNoLongerExist_thrownExceptionAsMergedConflict() {
        Item offlineItem = new Item();
        when(shoppingListRepository.getItem(offlineItem.getId())).thenReturn(null);
        expectedException.expect(SynchronizationException.class);
        expectedException.expectMessage("Item is not longer available");
        testSubject.synchronizeItem(offlineItem);
    }

    @Test
    public void synchronizeItem_storedItemIsCheckedButOfflineItemIsNotChecked_thrownExceptionAsMergedConflict(){
        Date olderDate = DateTime.now().minusMinutes(20).toDate();
        Date newestDate = DateTime.now().minusMinutes(10).toDate();

        Item offlineItem = aItemBuilder(1).withName("Item")
                .withChecked(false)
                .withQuantity(20)
                .withLastUpdateDate(newestDate).build();

        Item storedItem = aItemBuilder(1).withName("Item")
                .withChecked(true)
                .withQuantity(20)
                .withLastUpdateDate(olderDate).build();

        when(shoppingListRepository.getItem(offlineItem.getId())).thenReturn(storedItem);
        expectedException.expect(SynchronizationException.class);
        expectedException.expectMessage("Merge conflict: Item has been already checked");
        testSubject.synchronizeItem(offlineItem);
    }

    @Test
    public void synchronizeItem_storedItemIsCheckedButOfflineItemIsCheckedButDifferentQuantity_thrownExceptionAsMergedConflict(){
        Date olderDate = DateTime.now().minusMinutes(20).toDate();
        Date newestDate = DateTime.now().minusMinutes(10).toDate();

        Item offlineItem = aItemBuilder(1).withName("Item")
                .withChecked(true)
                .withQuantity(10)
                .withLastUpdateDate(newestDate).build();

        Item storedItem = aItemBuilder(1).withName("Item")
                .withChecked(true)
                .withQuantity(20)
                .withLastUpdateDate(olderDate).build();

        when(shoppingListRepository.getItem(offlineItem.getId())).thenReturn(storedItem);
        expectedException.expect(SynchronizationException.class);
        expectedException.expectMessage("Merge conflict: Item has been already checked");
        testSubject.synchronizeItem(offlineItem);
    }

    @Test
    public void synchronizeItem_offLineItemLastUpdateIsOlderThanStoredItemLastUpdate_notRequiredToSyncAndReturnPersistedItem() {
        Date olderDate = DateTime.now().minusMinutes(20).toDate();
        Date newestDate = DateTime.now().minusMinutes(10).toDate();

        Item offlineItem = aItemBuilder(1).withName("Item")
                                        .withChecked(false)
                                        .withQuantity(20)
                                        .withLastUpdateDate(olderDate).build();

        Item storedItem = aItemBuilder(1).withName("Item")
                                        .withChecked(false)
                                        .withQuantity(20)
                                        .withLastUpdateDate(newestDate).build();

        when(shoppingListRepository.getItem(offlineItem.getId())).thenReturn(storedItem);
        Item result = testSubject.synchronizeItem(offlineItem);
        assertThat(result, equalTo(storedItem));
    }


}