package com.azor.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillInfo extends RecursiveTreeObject<BillInfo> {
    private int id;
    private StringProperty name;
    private StringProperty price;
    private StringProperty count;

    public BillInfo(ResultSet set){
        try {
            id = set.getInt(1);
            name = new SimpleStringProperty("\t" + set.getString("name"));
            price = new SimpleStringProperty("" + set.getFloat("price"));
            count = new SimpleStringProperty("" + set.getInt("count"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BillInfo(String name, String price, String count){
        this.id = 0;
        this.name = new SimpleStringProperty("\t" + name);
        this.price = new SimpleStringProperty(price);
        this.count = new SimpleStringProperty(count);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public String getCount() {
        return count.get();
    }

    public StringProperty countProperty() {
        return count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s", id, name, price, count);
    }
}
