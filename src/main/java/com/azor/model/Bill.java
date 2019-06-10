package com.azor.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Bill extends RecursiveTreeObject<Bill> {
    private StringProperty ID;
    private StringProperty Date;

    public Bill(StringProperty ID, StringProperty date) {
        this.ID = ID;
        Date = date;
    }

    public Bill(ResultSet resultSet){
        try {
            ID = new SimpleStringProperty(resultSet.getString(1));
            Date = new SimpleStringProperty(resultSet.getString(2));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getDate() {
        return Date.get();
    }

    public StringProperty dateProperty() {
        return Date;
    }

    public void setDate(String date) {
        this.Date.set(date);
    }
}
