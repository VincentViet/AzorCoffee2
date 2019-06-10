package com.azor.user.password;

import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


@FxmlPath("/fxml/password.fxml")
public class PasswordView implements FxmlView<PasswordViewModel> {

    @InjectViewModel
    private PasswordViewModel viewModel;

    public void initialize() {

    }

    @FXML
    private void onConfirm(ActionEvent event){

    }

    @FXML
    private void onRefresh(ActionEvent event){

    }
}