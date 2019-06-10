package com.azor.user;

import com.jfoenix.controls.JFXDrawer;
import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

@FxmlPath("/fxml/user.fxml")
public class UserView implements FxmlView<UserViewModel> {

    @InjectViewModel
    private UserViewModel viewModel;

    @FXML
    private JFXDrawer drawer;

    public void initialize() {

    }

    @FXML
    private void onEdit(ActionEvent event){
        if (drawer.isOpened())
            drawer.close();
        else
            drawer.open();
    }
}