package com.azor.user;

import com.azor.AzorCoffee;
import com.azor.model.Account;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationObserver;
import de.saxsys.mvvmfx.utils.notifications.WeakNotificationObserver;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserViewModel implements ViewModel {

    private StringProperty fullname = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();
    private StringProperty type = new SimpleStringProperty();

    public UserViewModel(){
        MvvmFX.getNotificationCenter()
                .subscribe(AzorCoffee.message.LOGIN, (key, payload) ->{
                    Account currAuth = AzorCoffee.getCurrentAuth();
                    fullname.setValue(String.format("Full name: %s", currAuth.getFullname()));
                    email.setValue(String.format("Email: %s", currAuth.getEmail()));
                    address.setValue(String.format("Address: %s", currAuth.getAddress()));
                    phone.setValue(String.format("Telephone number: %s", currAuth.getTelphone()));
                    type.setValue(String.format("Admin: %s", currAuth.getType() == 1 ? "No" : "Yes"));
                });
    }

    public String getFullname() {
        return fullname.get();
    }

    StringProperty fullnameProperty() {
        return fullname;
    }

    public String getEmail() {
        return email.get();
    }

    StringProperty emailProperty() {
        return email;
    }

    public String getAddress() {
        return address.get();
    }

    StringProperty addressProperty() {
        return address;
    }

    public String getPhone() {
        return phone.get();
    }

    StringProperty phoneProperty() {
        return phone;
    }

    public String getType() {
        return type.get();
    }

    StringProperty typeProperty() {
        return type;
    }
}