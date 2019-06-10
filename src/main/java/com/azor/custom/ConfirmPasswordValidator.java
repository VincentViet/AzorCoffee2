package com.azor.custom;

import com.jfoenix.validation.base.ValidatorBase;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextInputControl;

public class ConfirmPasswordValidator extends ValidatorBase {

    private StringProperty pass = new SimpleStringProperty();

    public ConfirmPasswordValidator(String message, StringProperty pass){
        this.message.set(message);
        this.pass.bind(pass);
    }

    @Override
    protected void eval() {
        if (this.srcControl.get() instanceof TextInputControl) {
            this.evalTextInputField();
        }
    }

    private void evalTextInputField(){
        TextInputControl textField = (TextInputControl)this.srcControl.get();

        if (textField.getText().equals(pass.get())){
            hasErrors.set(false);
        }else {
            hasErrors.set(true);
        }
    }
}
