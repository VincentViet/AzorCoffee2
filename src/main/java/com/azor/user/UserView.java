package com.azor.user;

import com.azor.AzorCoffee;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

@FxmlPath("/fxml/user.fxml")
public class UserView implements FxmlView<UserViewModel> {

    @InjectViewModel
    private UserViewModel viewModel;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private FontAwesomeIconView pencil;

    @FXML
    private JFXButton editButton;

    public void initialize() {
        drawer.setSidePane(AzorCoffee.getView(AzorCoffee.layout.PASSWORD));
        drawer.open();

        drawer.setOnDrawerOpening(event -> {
            pencil.setGlyphName("PENCIL");
            editButton.getStyleClass().removeAll("close-button");
            editButton.getStyleClass().add("edit-button");
        });

        drawer.setOnDrawerClosing(event -> {
            pencil.setGlyphName("CLOSE");
            editButton.getStyleClass().removeAll("edit-button");
            editButton.getStyleClass().add("close-button");
        });
    }

    @FXML
    private void onEdit(ActionEvent event){
        drawer.toggle();
    }

    @FXML
    private void onSlideIn(MouseEvent event){
        if (drawer.isOpened())
            drawer.close();
    }

    @FXML
    private void onSlideOut(MouseEvent event){
        if (drawer.isClosed())
            drawer.open();
    }
}