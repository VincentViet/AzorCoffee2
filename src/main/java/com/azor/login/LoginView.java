package com.azor.login;

import com.azor.AzorCoffee;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

@FxmlPath("/fxml/login.fxml")
public class LoginView implements FxmlView<LoginViewModel> {

    @InjectViewModel
    private LoginViewModel viewModel;

    @FXML
    private JFXTextField identifier;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXCheckBox checkBox;

    @FXML
    private Label statusBar;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    void onLogin(ActionEvent event) {

    }

    @FXML
    private void onKeyPressed(KeyEvent event){
        if (event.getCode() == KeyCode.ESCAPE)
            AzorCoffee.close();
        else if (event.getCode() == KeyCode.ENTER)
            onLogin(null);
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


    public void initialize() {

    }
}