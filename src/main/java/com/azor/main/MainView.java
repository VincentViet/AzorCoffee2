package com.azor.main;

import com.azor.AzorCoffee;
import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
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

    private double xOffset = 0;
    private double yOffset = 0;

    public void initialize() {
        homeTab.getChildren().add(AzorCoffee.getView(AzorCoffee.layout.HOME));
        userTab.getChildren().add(AzorCoffee.getView(AzorCoffee.layout.USER));
        tasksTab.getChildren().add(AzorCoffee.getView(AzorCoffee.layout.MANAGER));
        chartTab.getChildren().add(AzorCoffee.getView(AzorCoffee.layout.CHART));
    }

    @FXML
    private void onClose(MouseEvent event){
        AzorCoffee.close();
    }

    @FXML
    private void onMinimize(MouseEvent event){
        AzorCoffee.minimize();
    }

    @FXML
    private void onMouseDragged(MouseEvent event){
        AzorCoffee.setStagePosition(
                event.getScreenX() - xOffset,
                event.getScreenY() - yOffset);
    }

    @FXML
    private void onMousePressed(MouseEvent event){
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }
}