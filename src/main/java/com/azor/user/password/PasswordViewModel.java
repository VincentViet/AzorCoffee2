package com.azor.user.password;

import com.azor.AzorCoffee;
import com.azor.utils.Database;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PasswordViewModel implements ViewModel {

    private StringProperty oldPass = new SimpleStringProperty();
    private StringProperty newPass = new SimpleStringProperty();
    private StringProperty confirmPass = new SimpleStringProperty();

    private StringProperty status = new SimpleStringProperty();
    private StringProperty statusStyle = new SimpleStringProperty();

    private Command confirmCommand;

    private static String UPDATE_PASSWORD = "update account set password = ? where id = ?";

    public PasswordViewModel(){

        confirmCommand = new DelegateCommand(() -> new Action() {
            @Override
            protected void action() {
                try {
                    PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(UPDATE_PASSWORD);
                    statement.setString(1, newPass.get());
                    statement.setInt(2, AzorCoffee.getCurrentAuth().getId());
                    statement.executeUpdate();

                    statusStyle.set("-fx-background-color: #00a54d; -fx-text-fill: white;");
                    status.set("Password has been changed successfully.");
                    oldPass.set("");
                    newPass.set("");
                    confirmPass.set("");
                } catch (SQLException e) {
                    statusStyle.set("-fx-background-color: #e4384a; -fx-text-fill: white;");
                    status.set("An error occurred !");
                }
            }
        });

    }

    String getOldPass() {
        return oldPass.get();
    }

    StringProperty oldPassProperty() {
        return oldPass;
    }

    String getNewPass() {
        return newPass.get();
    }

    StringProperty newPassProperty() {
        return newPass;
    }

    Command getConfirmCommand() {
        return confirmCommand;
    }

    public String getConfirmPass() {
        return confirmPass.get();
    }

    StringProperty confirmPassProperty() {
        return confirmPass;
    }

    public String getStatus() {
        return status.get();
    }

    StringProperty statusProperty() {
        return status;
    }

    public String getStatusStyle() {
        return statusStyle.get();
    }

    StringProperty statusStyleProperty() {
        return statusStyle;
    }
}