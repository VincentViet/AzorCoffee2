package com.azor.home;

import com.azor.AzorCoffee;
import com.azor.model.*;
import com.azor.utils.Database;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.Command;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class HomeViewModel implements ViewModel {
    private ObservableList<Table> tables = FXCollections.observableArrayList();
    private ObservableList<Category> categories = FXCollections.observableArrayList();
    private ObservableList<Drink> drinks = FXCollections.observableArrayList();
    private ObservableList<BillInfo> infos = FXCollections.observableArrayList();

    private StringProperty total = new SimpleStringProperty();
    private StringProperty tableName = new SimpleStringProperty();

    private int tableCount;

    private Command addFoodDrinks;
    private Command addTables;
    private Command paidCommand;

    private Table table;
    private Drink drink;
    private int count;

    public HomeViewModel(){
        loadTable();
        loadCategories();

        addFoodDrinks = new DelegateCommand(() -> new Action() {
            @Override
            protected void action() throws Exception {
                String query = "INSERT INTO BillInfo(name, price, count, billID) VALUES (?, ?, ?, ?)";
                String billQuery = "select * from Bill where isPaid = false and table_id = ?";
                try {
                    PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(billQuery);
                    statement.setInt(1, table.getId());
                    ResultSet set = statement.executeQuery();

                    Bill bill = null;
                    while (set.next())
                        bill = new Bill(set);

                    assert bill != null;
                    statement = Database.getInstance().getConnection().prepareStatement(query);
                    statement.setString(1, drink.getName());
                    statement.setFloat(2, Float.parseFloat(drink.getPrice()));
                    statement.setInt(3, count);
                    statement.setInt(4, bill.getID());
                    statement.executeUpdate();

                    infos.add(new BillInfo(drink.getName(), drink.getPrice(), count + ""));
                    total.setValue("Total: $" + calcTotal());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        addTables = new DelegateCommand(() -> new Action() {
            @Override
            protected void action() throws Exception {
                String query = "insert into CoffeeTable(name) values (?)";
                PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);
                statement.setString(1, tableName.get());
                statement.executeUpdate();

                tables.add(new Table(tableCount, tableName.get()));
                tableName.set("");
            }
        });

        paidCommand = new DelegateCommand(() -> new Action() {
            @Override
            protected void action() throws Exception {
                String billQuery = "select * from Bill where isPaid = false and table_id = ?";
                PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(billQuery);
                statement.setInt(1, table.getId());
                ResultSet set = statement.executeQuery();

                Bill bill = null;
                while (set.next())
                    bill = new Bill(set);

                assert bill != null;
                String updateBill = "update Bill set isPaid = true, paymentTime = ? where id = ?";
                statement = Database.getInstance().getConnection().prepareStatement(updateBill);
                statement.setString(1, LocalTime.now().toString());
                statement.setInt(2, bill.getID());
                statement.executeUpdate();

                MvvmFX.getNotificationCenter().publish(AzorCoffee.message.UPDATE);
            }
        });
    }

    private void loadTable(){
        String query = "SELECT * FROM CoffeeTable";
        try {
            PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);
            ResultSet tableResult = statement.executeQuery();

            tables.clear();
            while (tableResult.next())
            {
                tables.add(new Table(tableResult));
                tableCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadCategories(){
        String query = "SELECT * FROM Categories";
        try{
            PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);
            ResultSet set = statement.executeQuery();

            while (set.next())
                categories.add(new Category(set));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void loadDrink(int index){
        String query = "SELECT * FROM FoodAndDrink WHERE categoryID = ?";
        try {
            PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, index);
            ResultSet set = statement.executeQuery();

            drinks.clear();
            while (set.next())
                drinks.add(new Drink(set));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void loadBillInfo(int index){
        String createBill = "insert into Bill (table_id) values (?);";
        String billQuery = "select * from Bill where isPaid = false and table_id = ?";
        String billInfoQuery = "select * from BillInfo\n" +
                "where billID = ?";
        try {
            PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(billQuery);
            statement.setInt(1, index);
            ResultSet set = statement.executeQuery();
            Bill bill = null;
            while (set.next())
                bill = new Bill(set);

            if (bill != null){
                statement = Database.getInstance().getConnection().prepareStatement(billInfoQuery);
                statement.setInt(1, bill.getID());
                set = statement.executeQuery();

                infos.clear();
                while (set.next()){
                    BillInfo info = new BillInfo(set);
                    infos.add(info);
                }
            }else {
                statement = Database.getInstance().getConnection().prepareStatement(createBill);
                statement.setInt(1, index);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        total.setValue("Total: $" + calcTotal());
    }

    ObservableList<Table> getTables() {
        return tables;
    }

    ObservableList<Category> getCategories() {
        return categories;
    }

    ObservableList<Drink> getDrinks() {
        return drinks;
    }

    ObservableList<BillInfo> getInfos() {
        return infos;
    }

    StringProperty totalProperty() {
        return total;
    }

    Command getAddFoodDrinks() {
        return addFoodDrinks;
    }

    Command getAddTables() {
        return addTables;
    }

    public String getTotal() {
        return total.get();
    }

    Command getPaidCommand() {
        return paidCommand;
    }

    void setTable(Table table) {
        this.table = table;
    }

    public String getTableName() {
        return tableName.get();
    }

    StringProperty tableNameProperty() {
        return tableName;
    }

    private float calcTotal(){
        float total = 0.0f;

        for (BillInfo info :
                infos) {
            total += Float.parseFloat(info.getPrice())
                    * Float.parseFloat(info.getCount());

        }
        return total;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    void setCount(int count) {
        this.count = count;
    }

    void deleteBillInfo(ObservableList<TreeItem<BillInfo>> list){
        try{
            String query = "DELETE FROM BillInfo WHERE id = ?";
            PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);
            for (TreeItem<BillInfo> item :
                    list) {
                infos.remove(item.getValue());
                statement.setInt(1, item.getValue().getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}