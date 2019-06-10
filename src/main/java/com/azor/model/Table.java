package com.azor.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Table {
    private String name;
    private boolean status;

    public Table(ResultSet set){
        try {
            name = "Table " + set.getInt("id");
            status = set.getBoolean("isBlank");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%s : %s", name, status ? "blank" : "booked");
    }
}
