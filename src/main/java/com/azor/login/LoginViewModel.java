package com.azor.login;

import com.azor.AzorCoffee;
import com.azor.model.Account;
import com.azor.utils.Configurator;
import com.azor.utils.Database;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginViewModel implements ViewModel {

    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty status = new SimpleStringProperty();
    private boolean remember;
    private Command loginCommand;

    private static final String GET_AUTH =
            "select * from Account where username = ? or email = ? or telphone = ? and password = ?;";

    private Configurator configurator;

    public LoginViewModel(){

        configurator = Configurator.getInstance();
        remember = Boolean.parseBoolean(configurator.getProperty("remember"));
        username.setValue(configurator.getProperty("lastCode"));
        password.setValue(configurator.getProperty("lastPass"));

        loginCommand = new DelegateCommand(() -> new Action() {
            @Override
            protected void action() throws Exception {
                login();
            }
        });
    }


    public String getUsername() {
        return username.get();
    }

    StringProperty usernameProperty() {
        return username;
    }

    public String getPassword() {
        return password.get();
    }

    StringProperty passwordProperty() {
        return password;
    }

    public String getStatus() {
        return status.get();
    }

    StringProperty statusProperty() {
        return status;
    }

    Command getLoginCommand() {
        return loginCommand;
    }

    private void login() throws SQLException {
        if (remember){
            configurator.setProperty("remember", "true");
            configurator.setProperty("lastCode", username.get());
            configurator.setProperty("lastPass", password.get());
        }else {
            configurator.setProperty("remember", "false");
            configurator.setProperty("lastCode", "");
            configurator.setProperty("lastPass", "");
        }

            PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(GET_AUTH);
            statement.setString(1, username.get());
            statement.setString(2, username.get());
        statement.setString(3, username.get());
            statement.setString(4, password.get());
            statement.execute();
            ResultSet set = statement.getResultSet();
            if (set.next()){
                AzorCoffee.setScene(AzorCoffee.layout.MAIN);
                AzorCoffee.centerOnScreen();
                AzorCoffee.setCurrentAuth(new Account(set));
                status.setValue("");
            }else
                status.setValue("Employee code or password is incorrect.");
    }

    boolean isRemember() {
        return remember;
    }

    void setRemember(boolean remember) {
        this.remember = remember;
    }
}