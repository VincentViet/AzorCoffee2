package com.azor.main;

import com.azor.AzorCoffee;
import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;

@FxmlPath("/fxml/main.fxml")
public class MainView implements FxmlView<MainViewModel> {

    @InjectViewModel
    private MainViewModel viewModel;

    //region control
    @FXML
    private Tab homeTab;
    @FXML
    private Tab userTab;
    @FXML
    private Tab tasksTab;
    @FXML
    private Tab chartTab;
    //endregion
    public void initialize() {
        homeTab.setContent(AzorCoffee.getView(AzorCoffee.layout.HOME));
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