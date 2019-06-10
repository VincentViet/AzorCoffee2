package com.azor.manager.add;

import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;

@FxmlPath("/fxml/add.fxml")
public class AddView implements FxmlView<AddViewModel> {

    @InjectViewModel
    private AddViewModel viewModel;

    public void initialize() {

    }
}