package com.azor.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Table {
    private int id;
    private String name;

    public Table(ResultSet set){
        try {
            id = set.getInt(1);
            name = set.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Table(int id, String name){
        this.id = id;
        this.name = name;
    }

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

    @Override
    public String toString() {
        return String.format("%s", name);
    }
}
