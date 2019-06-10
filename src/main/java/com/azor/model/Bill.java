package com.azor.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Bill extends RecursiveTreeObject<Bill> {
    private IntegerProperty ID;
    private StringProperty Date;
    private StringProperty stringID;

    public Bill(IntegerProperty ID, StringProperty date) {
        this.ID = ID;
        Date = date;
        stringID = new SimpleStringProperty(ID.get()+"");
    }

    public Bill(ResultSet resultSet){
        try {
            ID = new SimpleIntegerProperty(resultSet.getInt(1));
            Date = new SimpleStringProperty(resultSet.getString(2));
            stringID = new SimpleStringProperty(ID.get()+"");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getID() {
        return ID.get();
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
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

    public String getStringID() {
        return stringID.get();
    }

    public StringProperty stringIDProperty() {
        return stringID;
    }

    public void setStringID(String stringID) {
        this.stringID.set(stringID);
    }
}
