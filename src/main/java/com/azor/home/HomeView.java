package com.azor.home;

import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;

@FxmlPath("/fxml/home.fxml")
public class HomeView implements FxmlView<HomeViewModel> {

    @InjectViewModel
    private HomeViewModel viewModel;

    public void initialize() {

    }
}