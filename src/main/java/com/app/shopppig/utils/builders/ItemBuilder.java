package com.app.shopppig.utils.builders;

import com.app.shopppig.model.Item;

import java.util.Date;

/**
 * Created by dmorenoh on 4/8/16.
 */
public class ItemBuilder {
    private Item target = new Item();

    private int id;
    private String shoppingListId;
    private String name;
    private boolean synchronizedItem;
    private boolean checked;
    private int quantity;
    private Date lastSyncDate;
    private Date lastUpdateDate;
    private Date createdDate;

    public static ItemBuilder aItemBuilder(int id) {
        return new ItemBuilder(id);
    }

    public ItemBuilder(int id){
        this.id=id;
    }

    public Item build(){
        target.setId(this.id);
        target.setChecked(this.checked);
        target.setLastUpdateDate(this.lastUpdateDate);
        target.setName(this.name);
        target.setQuantity(this.quantity);
        return target;
    }

    public ItemBuilder withName(String name){
        this.name = name;
        return this;
    }

    public ItemBuilder withChecked(boolean checked){
        this.checked = checked;
        return this;
    }

    public ItemBuilder withQuantity(int quantity){
        this.quantity = quantity;
        return this;
    }

    public ItemBuilder withLastUpdateDate(Date lastUpdateDate){
        this.lastUpdateDate = lastUpdateDate;
        return this;
    }

}
