package com.azor.manager;

import com.azor.model.Account;
import com.azor.model.Bill;
import com.azor.model.BillInfo;
import com.azor.model.Drink;
import com.azor.utils.Database;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerViewModel implements ViewModel {
    private ObservableList<Account> listAccount = FXCollections.observableArrayList();
    private ObservableList<Drink> listDrink = FXCollections.observableArrayList();
    private ObservableList<Bill> listBill = FXCollections.observableArrayList();
    private ObservableList<BillInfo> listBillInfo = FXCollections.observableArrayList();

    private Command deleteAccountCommand;
    private Command deleteDrinkCommand;
    private Command loadBillInfoCommand;
    private Command createCategory;

    private Bill bill;
    private Drink drink;
    private Account account;

    public ManagerViewModel(){
        loadAccount();
        loadDrink();
        loadBill();

//        subscribe(ManagerView.LOAD_BILL_INFO, (key, payload) -> loadBillInfo((Bill)payload[0]));

        deleteAccountCommand = new DelegateCommand(() -> new Action() {
            @Override
            protected void action() throws Exception {
                String query = "Delete from account where username = ?";
                PreparedStatement temp = Database.getInstance().getConnection().prepareStatement(query);
                temp.setString(1, account.getUsername());
                temp.executeUpdate();

                listAccount.remove(account);
            }
        });

        deleteDrinkCommand = new DelegateCommand(() -> new Action() {
            @Override
            protected void action() throws Exception {
                String query = "Delete from main.FoodAndDrink where name = ?";
                PreparedStatement temp = Database.getInstance().getConnection().prepareStatement(query);
                temp.setString(1, drink.getName());
                temp.executeUpdate();
                
                listDrink.remove(drink);
            }
        });
        
        loadBillInfoCommand = new DelegateCommand(() -> new Action() {
            @Override
            protected void action(){
                loadBillInfo(bill);
            }
        });
    }

    ObservableList<Account> getListAccount() {
        return listAccount;
    }

    ObservableList<Drink> getListDrink() {
        return listDrink;
    }

    ObservableList<Bill> getListBill() {
        return listBill;
    }

    ObservableList<BillInfo> getListBillInfo() {
        return listBillInfo;
    }

    void setBill(Bill bill) {
        this.bill = bill;
    }

    void setAccount(Account account) {
        this.account = account;
    }

    void setDrink(Drink drink) {
        this.drink = drink;
    }

    Command getDeleteAccountCommand() {
        return deleteAccountCommand;
    }

    Command getLoadBillInfoCommand() {
        return loadBillInfoCommand;
    }

    Command getDeleteDrinkCommand() {
        return deleteDrinkCommand;
    }

    //region load data
    private void loadAccount() {
        String query = "select * from account where type = 1";
        try {
            PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            listAccount.clear();
            while (result.next()) {
                Account account = new Account(result);
                listAccount.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void loadDrink() {
        String query = "Select name, price, categoryID from main.FoodAndDrink";
        try {
            PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            listDrink.clear();
            while (result.next()) {
                Drink drink = new Drink(result);
                String tempQuery = "Select name from Categories where id = ?";
                try {
                    PreparedStatement tempStatement = Database.getInstance().getConnection().prepareStatement(tempQuery);
                    tempStatement.setString(1, drink.getCategoryID());
                    ResultSet tempResult = tempStatement.executeQuery();
                    drink.setCategoryName(tempResult.getString(1));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                listDrink.add(drink);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadBill() {
        String query = "Select id, creationTime from bill";
        try {
            PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            listBill.clear();
            while (result.next()) {
                Bill bill = new Bill(result);
                listBill.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadBillInfo(Bill selectedBill) {
        String query = "Select name, price, count from BillInfo where billID = ?";
        try {
            PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);
            statement.setString(1, selectedBill.getID());
            ResultSet result = statement.executeQuery();
            listBillInfo.clear();
            while (result.next()) {
                BillInfo bill = new BillInfo(result);
                listBillInfo.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //endregion
}