package com.app.shopppig.model;

import java.util.Date;

/**
 * Created by dmorenoh on 1/8/16.
 */
public class Item {
    private int id;
    private String shoppingListId;
    private String name;
    private boolean synchronizedItem;
    private boolean checked;
    private int quantity;
    private Date lastSyncDate;
    private Date lastUpdateDate;
    private Date createdDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSynchronizedItem() {
        return synchronizedItem;
    }

    public void setSynchronizedItem(boolean synchronizedItem) {
        this.synchronizedItem = synchronizedItem;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(Date lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(String shoppingListId) {
        this.shoppingListId = shoppingListId;
    }


}
