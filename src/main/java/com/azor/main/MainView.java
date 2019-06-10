package com.azor.main;

import com.azor.AzorCoffee;
import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

@FxmlPath("/fxml/main.fxml")
public class MainView implements FxmlView<MainViewModel> {

    @InjectViewModel
    private MainViewModel viewModel;

    //region control
    @FXML
    private VBox homeTab;
    @FXML
    private VBox userTab;
    @FXML
    private VBox tasksTab;
    @FXML
    private VBox chartTab;
    //endregion
    public void initialize() {
        homeTab.getChildren().add(AzorCoffee.getView(AzorCoffee.layout.HOME));
        userTab.getChildren().add(AzorCoffee.getView(AzorCoffee.layout.USER));
    }

    @FXML
    private void onClose(MouseEvent event){
        AzorCoffee.close();
    }

    @FXML
    private void onMinimize(MouseEvent event){
        AzorCoffee.minimize();
    }
}