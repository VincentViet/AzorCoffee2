package com.azor.home;

import com.azor.model.BillInfo;
import com.azor.model.Category;
import com.azor.model.Drink;
import com.azor.model.Table;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.validation.RequiredFieldValidator;
import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

@FxmlPath("/fxml/home.fxml")
public class HomeView implements FxmlView<HomeViewModel> {

    @InjectViewModel
    private HomeViewModel viewModel;

    @FXML
    private JFXListView<Table> tables;

    @FXML
    private JFXTreeTableView<BillInfo> billInfo;

    @FXML
    private JFXComboBox<Category> categories;

    @FXML
    private JFXComboBox<Drink> drinks;

    @FXML
    private JFXComboBox<Integer> counts;

    @FXML
    private JFXButton addButton;

    @FXML
    private Label total;

    @FXML
    private JFXTextField tableName;

    public void initialize() {
        tables.setItems(viewModel.getTables());
        tables.getSelectionModel().selectedIndexProperty()
                .addListener((observable, oldValue, newValue) -> {
                    viewModel.loadBillInfo(newValue.intValue() + 1);
                    final TreeItem<BillInfo> root = new RecursiveTreeItem<>(viewModel.getInfos(), RecursiveTreeObject::getChildren);
                    billInfo.setRoot(root);
                });
        tables.getSelectionModel().selectedItemProperty()
                .addListener(((observable, oldValue, newValue) -> {
                    viewModel.setTable(newValue);
                }));
        tables.getSelectionModel().select(0);

        categories.setItems(viewModel.getCategories());
        categories.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> viewModel.loadDrink(newValue.getId()));

        drinks.setItems(viewModel.getDrinks());
        total.textProperty().bind(viewModel.totalProperty());

        JFXTreeTableColumn<BillInfo, String> name = new JFXTreeTableColumn<>("Name");
        name.setCellValueFactory(param -> param.getValue().getValue().nameProperty());
        name.setPrefWidth(500);
        name.setResizable(false);

        JFXTreeTableColumn<BillInfo, String> count = new JFXTreeTableColumn<>("Count");
        count.setCellValueFactory(param -> param.getValue().getValue().countProperty());
        count.setResizable(false);
        count.setPrefWidth(200);
        count.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<BillInfo, String> price = new JFXTreeTableColumn<>("Price");
        price.setCellValueFactory(param -> param.getValue().getValue().priceProperty());
        price.setResizable(false);
        price.setPrefWidth(204);
        price.setStyle("-fx-alignment: center");


        billInfo.getColumns().add(name);
        billInfo.getColumns().add(count);
        billInfo.getColumns().add(price);
        billInfo.setShowRoot(false);
        billInfo.setPlaceholder(new Label("Nothing to show."));
        billInfo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ContextMenu billInfoContextMenu = new ContextMenu();
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(event -> {
            viewModel.deleteBillInfo(billInfo.getSelectionModel().getSelectedItems());
        });
        billInfoContextMenu.getItems().add(deleteItem);
        billInfo.setContextMenu(billInfoContextMenu);

        for (int i = 0; i <= 50; i++) {
            counts.getItems().add(i);
        }

        RequiredFieldValidator validator = new RequiredFieldValidator("This field is required.");
        tableName.textProperty().bindBidirectional(viewModel.tableNameProperty());
        tableName.getValidators().add(validator);
        categories.getValidators().add(validator);
        drinks.getValidators().add(validator);
        counts.getValidators().add(validator);

        addButton.setOnAction(event -> {
            if (categories.validate() &&
            drinks.validate() &&
            counts.validate())
            {
                viewModel.setDrink(drinks.getValue());
                viewModel.setCount(counts.getValue());
                viewModel.getAddFoodDrinks().execute();
            }
        });
    }

    @FXML
    private void onAddTable(ActionEvent event){
        if (tableName.validate())
            viewModel.getAddTables().execute();
    }

    @FXML
    private void onPaid(ActionEvent event){
        viewModel.getPaidCommand().execute();
    }
}