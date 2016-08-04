package com.app.shopppig.model;

import java.util.List;
import java.util.Set;

/**
 * Created by dmorenoh on 1/8/16.
 */
public class User {
    private String userName;
    private String password;
    private PersonName personName;
    private String email;
    private String telephoneNumber;
    private Set<ShoppingList> shoppingListSet;
    private List<User> friends;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonName getPersonName() {
        return personName;
    }

    public void setPersonName(PersonName personName) {
        this.personName = personName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Set<ShoppingList> getShoppingListSet() {
        return shoppingListSet;
    }

    public void setShoppingListSet(Set<ShoppingList> shoppingListSet) {
        this.shoppingListSet = shoppingListSet;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
