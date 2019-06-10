package com.azor.manager.add;

import com.azor.model.Account;
import com.azor.utils.Database;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;

import java.sql.PreparedStatement;

public class AddViewModel implements ViewModel {

    private Account account;

    private Command addCommand;

    public AddViewModel(){
        addCommand = new DelegateCommand(()-> new Action() {
            @Override
            protected void action() throws Exception {
                String query = "insert into account(username, email, password, fullname, address, telphone, type) " +
                        "values(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement temp = Database.getInstance().getConnection().prepareStatement(query);
                temp.setString(1, account.getUsername());
                temp.setString(2, account.getEmail());
                temp.setString(3, account.getPassword());
                temp.setString(4, account.getFullname());
                temp.setString(5, account.getAddress());
                temp.setString(6, account.getTelphone());
                temp.setInt(7, account.getType());
                temp.executeUpdate();
            }
        });
    }

    void setAccount(Account account) {
        this.account = account;
    }

    Command getAddCommand() {
        return addCommand;
    }
}