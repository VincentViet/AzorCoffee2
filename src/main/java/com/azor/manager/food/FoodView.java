package com.azor.manager.food;

import com.azor.model.Category;
import com.azor.model.Drink;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

@FxmlPath("/fxml/food.fxml")
public class FoodView implements FxmlView<FoodViewModel> {

    @InjectViewModel
    private FoodViewModel viewModel;

    @FXML
    private JFXTextField tfDrinkName;

    @FXML
    private JFXTextField tfPrice;

    @FXML
    private JFXTextField tfCategoryName;

    @FXML
    private JFXComboBox<Category> comboBoxCategory;

    public void initialize() {
        RequiredFieldValidator validator = new RequiredFieldValidator("This field is required.");
        tfDrinkName.getValidators().add(validator);
        tfPrice.getValidators().add(validator);
        tfCategoryName.getValidators().add(validator);
        comboBoxCategory.getValidators().add(validator);
        tfPrice.getValidators().add(new NumberValidator("Only support number."));

        JFXTextField [] textFields = new JFXTextField[]{tfPrice, tfDrinkName, tfCategoryName};
        for (JFXTextField t :
                textFields) {
            t.focusedProperty().addListener(((observable, oldValue, newValue) -> {
                if (!newValue)
                    t.validate();
            }));
        }

        comboBoxCategory.setItems(viewModel.getCategories());
    }

    @FXML
    private void addDrink(ActionEvent event){

        if (tfDrinkName.validate() &&
        tfPrice.validate() &&
        comboBoxCategory.validate()){
            String name = tfDrinkName.getText();
            String price = tfPrice.getText();
            int categoryID = comboBoxCategory.getSelectionModel().getSelectedItem().getId();
            viewModel.setDrink(new Drink(name, price, categoryID));
            viewModel.getAddDrink().execute();
        }
    }

    @FXML
    private void resetTextField(ActionEvent event){
        tfDrinkName.setText("");
        tfPrice.setText("");
    }

    @FXML
    private void addCategory(ActionEvent event){
        if (tfCategoryName.validate()){
            String name = tfCategoryName.getText();
            viewModel.setCategory(new Category(name));
            viewModel.getAddCategory().execute();
        }
    }
}