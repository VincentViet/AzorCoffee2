package com.azor.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Database {
    private static Database ourInstance;

    static {
        try {
            ourInstance = new Database();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connection;

    public static Database getInstance() {
        return ourInstance;
    }

    private Database() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        String sqlitePath = "jdbc:sqlite:" + Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(""))
                .getPath() + "app.sqlite3";
        connection = DriverManager.getConnection(sqlitePath);
    }
    
    public Connection getConnection(){
        return connection;
    }
}
