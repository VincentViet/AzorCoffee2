package com.azor.user.password;

import com.azor.AzorCoffee;
import com.azor.custom.ConfirmPasswordValidator;
import com.jfoenix.controls.JFXPasswordField;
import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.MvvmFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


@FxmlPath("/fxml/password.fxml")
public class PasswordView implements FxmlView<PasswordViewModel> {

    @InjectViewModel
    private PasswordViewModel viewModel;

    @FXML
    private JFXPasswordField oldPass;

    @FXML
    private JFXPasswordField newPass;

    @FXML
    private JFXPasswordField confirmPass;

    @FXML
    private Label status;

    public void initialize() {
        viewModel.oldPassProperty().bindBidirectional(oldPass.textProperty());
        viewModel.newPassProperty().bindBidirectional(newPass.textProperty());
        viewModel.confirmPassProperty().bindBidirectional(confirmPass.textProperty());

        MvvmFX.getNotificationCenter()
                .subscribe(AzorCoffee.message.LOGIN, (key, payload) ->{
                    oldPass
                            .getValidators()
                            .add(new ConfirmPasswordValidator(
                                    "Password is  incorrect.",
                                    AzorCoffee.getCurrentAuth().passwordProperty()));
                    oldPass.focusedProperty().addListener(((observable, oldValue, newValue) -> {
                        if (!newValue)
                            oldPass.validate();
                    }));
                });

        confirmPass
                .getValidators()
                .add(new ConfirmPasswordValidator(
                        "Your confirm password does not matches.",
                        newPass.textProperty()));
        confirmPass.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue)
                confirmPass.validate();
        });

        status.textProperty().bind(viewModel.statusProperty());
        status.styleProperty().bind(viewModel.statusStyleProperty());
    }

    @FXML
    private void onConfirm(ActionEvent event) {
        if (confirmPass.validate() &&
                oldPass.validate())
            viewModel.getConfirmCommand().execute();
    }

    @FXML
    private void onRefresh(ActionEvent event) {
        oldPass.setText("");
        newPass.setText("");
        confirmPass.setText("");
    }

    @FXML
    private void onKeyPressed(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER)
            onConfirm(null);
    }

}