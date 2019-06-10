package com.azor.manager;

import com.azor.AzorCoffee;
import com.azor.model.Account;
import com.azor.model.Bill;
import com.azor.model.BillInfo;
import com.azor.model.Drink;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.effects.JFXDepthManager;
import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;

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

//    static final String LOAD_BILL_INFO = "LOAD_BILL_INFO";

    public void initialize() {
        drawerAddAccount.setSidePane(AzorCoffee.getView(AzorCoffee.layout.ADD));
        drawerAddAccount.close();

        drawerAddFood.setSidePane(AzorCoffee.getView(AzorCoffee.layout.FOOD));
        drawerAddFood.close();

        initAccountTable();
        initDrinkTable();
        initBillTable();
        initBillInfoTable();

        JFXDepthManager.setDepth(drawerAddAccount, 4);
        JFXDepthManager.setDepth(drawerAddFood, 4);

        tfSearchBarAccount.textProperty().addListener(setupSearchFieldAccount(treeviewAccount));
        tfSearchBarDrink.textProperty().addListener(setupSearchFieldDrink(treeviewDrink));
        tfSearchBarBill.textProperty().addListener(setupSearchFieldBill(treeviewBill));
    }

    @FXML
    private void deleteRowAccount(ActionEvent event){
        Account currentSelected = treeviewAccount.getSelectionModel().selectedItemProperty().get().getValue();
        viewModel.setAccount(currentSelected);
        viewModel.getDeleteAccountCommand().execute();
//        presenter.deleteAccountInDatabase(currentSelected);
//        listAccount.remove(currentSelected);
        final IntegerProperty currCountProp = treeviewAccount.currentItemsCountProperty();
        currCountProp.set(currCountProp.get() - 1);
    }

    @FXML
    private void toggleDrawerAccount(ActionEvent event){
        drawerAddAccount.toggle();
    }

    @FXML
    private void deleteRowDrink(ActionEvent event){
        Drink currentSelected = treeviewDrink.getSelectionModel().selectedItemProperty().get().getValue();
        viewModel.setDrink(currentSelected);
        viewModel.getDeleteDrinkCommand().execute();
//        presenter.deleteDrinkInDatabase(currentSelected);
//        listDrink.remove(currentSelected);
        final IntegerProperty currCountProp = treeviewDrink.currentItemsCountProperty();
        currCountProp.set(currCountProp.get() - 1);
    }

    @FXML
    private void toggleDrawerFood(ActionEvent event){
        drawerAddFood.toggle();
    }

    //region initialize controls
    private void initAccountTable() {
        // Initialize tree table view collumns
        JFXTreeTableColumn<Account, String> username = new JFXTreeTableColumn<>("Username");
        username.setPrefWidth(200);
        username.setCellValueFactory(param -> param.getValue().getValue().usernameProperty());
        username.setStyle("-fx-alignment: center");

//        JFXTreeTableColumn<Account, String> password = new JFXTreeTableColumn<>("Password");
//        password.setPrefWidth(125);
//        password.setCellValueFactory(param -> param.getValue().getValue().passwordProperty());
//        password.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Account, String> email = new JFXTreeTableColumn<>("Email");
        email.setPrefWidth(200);
        email.setCellValueFactory(param -> param.getValue().getValue().emailProperty());
        email.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Account, String> fullname = new JFXTreeTableColumn<>("Full name");
        fullname.setPrefWidth(200);
        fullname.setCellValueFactory(param -> param.getValue().getValue().fullnameProperty());
        fullname.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Account, String> address = new JFXTreeTableColumn<>("Address");
        address.setPrefWidth(200);
        address.setCellValueFactory(param -> param.getValue().getValue().addressProperty());
        address.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Account, String> telphone = new JFXTreeTableColumn<>("Telephone Number");
        telphone.setPrefWidth(200);
        telphone.setCellValueFactory(param -> param.getValue().getValue().telphoneProperty());
        telphone.setStyle("-fx-alignment: center");

        // allow multiple select
        treeviewAccount.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Create tree
        final TreeItem<Account> root = new RecursiveTreeItem<Account>(viewModel.getListAccount(), RecursiveTreeObject::getChildren);
        treeviewAccount.getColumns().setAll(username, /*password,*/ email, fullname, address, telphone);
        treeviewAccount.setRoot(root);
        treeviewAccount.setShowRoot(false);
    }

    private void initDrinkTable() {
        JFXTreeTableColumn<Drink, String> drinkName = new JFXTreeTableColumn<>("Name");
        drinkName.setPrefWidth(325);
        drinkName.setCellValueFactory(param -> param.getValue().getValue().nameProperty());
        drinkName.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Drink, String> drinkPrice = new JFXTreeTableColumn<>("Price");
        drinkPrice.setPrefWidth(180);
        drinkPrice.setCellValueFactory(param -> param.getValue().getValue().priceProperty());
        drinkPrice.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Drink, String> categoryID = new JFXTreeTableColumn<>("Category ID");
        categoryID.setPrefWidth(175);
        categoryID.setCellValueFactory(param -> param.getValue().getValue().categoryIDProperty());
        categoryID.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Drink, String> categoryName = new JFXTreeTableColumn<>("Category Name");
        categoryName.setPrefWidth(325);
        categoryName.setCellValueFactory(param -> param.getValue().getValue().categoryNameProperty());
        categoryName.setStyle("-fx-alignment: center");

        // allow multiple select
        treeviewAccount.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        final TreeItem<Drink> root = new RecursiveTreeItem<Drink>(viewModel.getListDrink(), RecursiveTreeObject::getChildren);
        treeviewDrink.getColumns().setAll(drinkName, drinkPrice, categoryID, categoryName);
        treeviewDrink.setRoot(root);
        treeviewDrink.setShowRoot(false);
    }

    private void initBillTable() {
        JFXTreeTableColumn<Bill, String> billID = new JFXTreeTableColumn<>("ID");
        billID.setPrefWidth(125);
        billID.setCellValueFactory(param -> param.getValue().getValue().stringIDProperty());
        billID.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<Bill, String> billDate = new JFXTreeTableColumn<>("Date");
        billDate.setPrefWidth(350);
        billDate.setCellValueFactory(param -> param.getValue().getValue().dateProperty());
        billDate.setStyle("-fx-alignment: center");

        // allow multiple select
        treeviewBill.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Create tree
        final TreeItem<Bill> root = new RecursiveTreeItem<Bill>(viewModel.getListBill(), RecursiveTreeObject::getChildren);
        treeviewBill.getColumns().setAll(billID, billDate);
        treeviewBill.setRoot(root);
        treeviewBill.setShowRoot(false);

        treeviewBill.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            listBillInfo= presenter.loadBillInfo(treeviewBill.getSelectionModel().getSelectedItem().getValue());

//            viewModel.publish(LOAD_BILL_INFO, newValue.getValue());
            viewModel.setBill(newValue.getValue());
            viewModel.getLoadBillInfoCommand().execute();
            final TreeItem<BillInfo> rootBillInfo = new RecursiveTreeItem<BillInfo>(viewModel.getListBillInfo(), RecursiveTreeObject::getChildren);
            treeviewBillInfo.setRoot(rootBillInfo);
        });
    }

    private void initBillInfoTable() {
        JFXTreeTableColumn<BillInfo, String> billInfoName = new JFXTreeTableColumn<>("Name");
        billInfoName.setPrefWidth(305);
        billInfoName.setCellValueFactory(param -> param.getValue().getValue().nameProperty());
        billInfoName.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<BillInfo, String> billInfoPrice = new JFXTreeTableColumn<>("Price");
        billInfoPrice.setPrefWidth(125);
        billInfoPrice.setCellValueFactory(param -> param.getValue().getValue().priceProperty());
        billInfoPrice.setStyle("-fx-alignment: center");

        JFXTreeTableColumn<BillInfo, String> billInfoCount = new JFXTreeTableColumn<>("Count");
        billInfoCount.setPrefWidth(125);
        billInfoCount.setCellValueFactory(param -> param.getValue().getValue().countProperty());
        billInfoName.setStyle("-fx-alignment: center");

        // load data from database
        // allow multiple select

        // Create tree

        treeviewBillInfo.getColumns().setAll(billInfoName, billInfoPrice, billInfoCount);
        treeviewBillInfo.setShowRoot(false);
    }
    //endregion

    private ChangeListener<String> setupSearchFieldAccount(final JFXTreeTableView<Account> treeTableView) {
        return (o, oldVal, newVal) ->
                treeTableView.setPredicate(personProp -> {
                    final Account account = personProp.getValue();
                    return account.getUsername().contains(newVal)
                            || account.getFullname().contains(newVal)
                            || account.getEmail().contains(newVal)
                            || account.getTelphone().contains(newVal)
                            || account.getAddress().contains(newVal);
                });
    }

    private ChangeListener<String> setupSearchFieldDrink(final JFXTreeTableView<Drink> treeTableView) {
        return (o, oldVal, newVal) ->
                treeTableView.setPredicate(personProp -> {
                    final Drink drink = personProp.getValue();
                    return drink.getName().contains(newVal)
                            || drink.getCategoryName().contains(newVal);
                });
    }

    private ChangeListener<String> setupSearchFieldBill(final JFXTreeTableView<Bill> treeTableView) {
        return (o, oldVal, newVal) ->
                treeTableView.setPredicate(personProp -> {
                    final Bill bill = personProp.getValue();
                    return bill.getDate().contains(newVal);
                });
    }
}