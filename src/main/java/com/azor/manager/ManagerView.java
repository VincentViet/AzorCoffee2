package com.azor.manager;

import com.azor.model.Account;
import com.azor.model.Bill;
import com.azor.model.BillInfo;
import com.azor.model.Drink;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

@FxmlPath("/fxml/manager.fxml")
public class ManagerView implements FxmlView<ManagerViewModel> {

    @InjectViewModel
    private ManagerViewModel viewModel;

    @FXML
    private JFXTreeTableView<Account> treeviewAccount;

    @FXML
    private JFXTreeTableView<Drink> treeviewDrink;

    @FXML
    private JFXTreeTableView<Bill> treeviewBill;

    @FXML
    private JFXTreeTableView<BillInfo> treeviewBillInfo;

    @FXML
    private JFXDrawer drawerAddAccount;

    @FXML
    private JFXDrawer drawerAddFood;

    @FXML
    private JFXTextField tfSearchBarAccount;

    @FXML
    private JFXTextField tfSearchBarDrink;

    @FXML
    private JFXTextField tfSearchBarBill;

    public void initialize() {

    }

    @FXML
    private void deleteRowAccount(ActionEvent event){

    }

    @FXML
    private void toggleDrawerAccount(ActionEvent event){

    }

    @FXML
    private void deleteRowDrink(ActionEvent event){

    }

    @FXML
    private void toggleDrawerFood(ActionEvent event){

    }
}