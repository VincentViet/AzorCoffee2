package com.azor.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Drink extends RecursiveTreeObject<Drink> {
    private StringProperty name;
    private StringProperty price;
    private StringProperty categoryID;
    private StringProperty categoryName;


    public Drink(ResultSet set) {
        try {
            name = new SimpleStringProperty(set.getString("name"));
            price = new SimpleStringProperty(set.getString("price"));
            categoryID = new SimpleStringProperty(set.getString("categoryID"));
            categoryName = new SimpleStringProperty("default");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Drink(String name, String price, int categoryID) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
        this.categoryID = new SimpleStringProperty(Integer.toString(categoryID));
    }

    @Override
    public String toString() {
        return name.get();
    }

    public String getCategoryName() {
        return categoryName.get();
    }

    public StringProperty categoryNameProperty() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName.set(categoryName);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getCategoryID() {
        return categoryID.get();
    }

    public StringProperty categoryIDProperty() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID.set(categoryID);
    }
}