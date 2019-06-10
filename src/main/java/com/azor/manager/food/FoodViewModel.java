package com.azor.manager.food;

import com.azor.model.Category;
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

public class FoodViewModel implements ViewModel {

    private Category category;
    private Drink drink;
    private ObservableList<Category> categories = FXCollections.observableArrayList();

    private Command addCategory;
    private Command addDrink;

    public FoodViewModel(){
        addCategory = new DelegateCommand(() -> new Action() {
            @Override
            protected void action() throws Exception {
                String query = "insert into Categories(name) values(?)";
                PreparedStatement temp = Database.getInstance().getConnection().prepareStatement(query);
                temp.setString(1, category.getName());
                temp.executeUpdate();
            }
        });

        addDrink = new DelegateCommand(() -> new Action() {
            @Override
            protected void action() throws Exception {
                String query = "Insert into main.FoodAndDrink(name, price, categoryID) " +
                        "values(?, ?, ?)";
                PreparedStatement
                temp = Database.getInstance().getConnection().prepareStatement(query);
                temp.setString(1, drink.getName());
                temp.setString(2, drink.getPrice());
                temp.setString(3, drink.getCategoryID());
                temp.executeUpdate();
            }
        });

        loadCategory();
    }

    void setCategory(Category category) {
        this.category = category;
    }

    void setDrink(Drink drink) {
        this.drink = drink;
    }

    Command getAddCategory() {
        return addCategory;
    }

    Command getAddDrink() {
        return addDrink;
    }

    public ObservableList<Category> getCategories() {
        return categories;
    }

    private void loadCategory() {
        String query = "Select * from Categories";
        try {
            PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                categories.add(new Category(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}