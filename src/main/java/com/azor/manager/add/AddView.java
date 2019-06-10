package com.azor.manager.add;

import com.azor.model.Account;
import com.azor.utils.CodeGenerator;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import de.saxsys.mvvmfx.FxmlPath;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

@FxmlPath("/fxml/add.fxml")
public class AddView implements FxmlView<AddViewModel> {

    @InjectViewModel
    private AddViewModel viewModel;

    @FXML
    private JFXTextField tfUsername;

    @FXML
    private JFXTextField tfFullname;

    @FXML
    private JFXTextField tfPassword;

    @FXML
    private JFXTextField tfPhoneNumber;

    @FXML
    private JFXTextField tfEmail;

    @FXML
    private JFXTextField tfAddress;

    public void initialize() {
        RequiredFieldValidator validator = new RequiredFieldValidator("This field is required.");
        RegexValidator regexValidator = new RegexValidator("Your email is incorrect pattern.");
        regexValidator.setRegexPattern("^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$");

        tfFullname.getValidators().add(validator);
        tfEmail.getValidators().add(validator);
        tfEmail.getValidators().add(regexValidator);
        regexValidator.setRegexPattern("0[0-9\\s.-]{9,13}");
        regexValidator.setMessage("Your telephone number is incorrect pattern.");
        tfPhoneNumber.getValidators().add(regexValidator);

        JFXTextField[] jfxTextFields = new JFXTextField[]{
                tfUsername, tfFullname, tfPassword, tfPhoneNumber, tfEmail, tfAddress
        };
        for (JFXTextField t :
                jfxTextFields) {
            t.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue)
                    t.validate();
            });
        }
    }

    @FXML
    private void resetTextField(ActionEvent event){
        tfUsername.setText("");
        tfEmail.setText("");
        tfPassword.setText("");
        tfFullname.setText("");
        tfAddress.setText("");
        tfPhoneNumber.setText("");
    }

    @FXML
    private void addData(ActionEvent event){
        if (tfUsername.validate() &&
                tfEmail.validate() &&
                tfPassword.validate() &&
                tfFullname.validate() &&
                tfAddress.validate() &&
                tfPhoneNumber.validate())
        {
            String username = tfUsername.getText();
            String email = tfEmail.getText();
            String password = tfPassword.getText();
            String fullname = tfFullname.getText();
            String address = tfAddress.getText();
            String telphone = tfPhoneNumber.getText();
            viewModel.setAccount(new Account(username, email, password, fullname, address, telphone));
            viewModel.getAddCommand().execute();
        }
    }

    @FXML
    private void generateIDPassword(ActionEvent event){
        tfUsername.setText(CodeGenerator.getInstance().nextCode());
        tfPassword.setText("AzorCoffee");
    }
    //endregion
}