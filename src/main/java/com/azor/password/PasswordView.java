package com.azor.password;

import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;

@FxmlPath("/fxml/password.fxml")
public class PasswordView implements FxmlView<PasswordViewModel> {

    @InjectViewModel
    private PasswordViewModel viewModel;

    public void initialize() {

    }
}