package com.azor.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Category extends RecursiveTreeObject<Category> {
    private int id;
    private String name;

    public Category(ResultSet set){
        try {
            id = set.getInt("id");
            name = set.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public String toString()  {
        return this.name;
    }
}
