package com.azor.utils;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

public class Configurator {
    public static Configurator getInstance(){
        return instance;
    }

    private Configurator() {
        configPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(""))
                .getPath() + "app.properties";

        try (InputStream input = new FileInputStream(configPath)) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value){
        properties.setProperty(key, value);
        try (OutputStream out = new FileOutputStream(configPath)){
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            properties.store(out, timestamp.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String configPath;
    private Properties properties;

    private static Configurator instance;

    static {
        instance = new Configurator();
    }
}
