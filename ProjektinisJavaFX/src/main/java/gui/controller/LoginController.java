package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import gui.manager.ViewManager;

public class LoginController {

    @FXML private Button loginButton;

    @FXML private TextField loginUsername;

    @FXML private PasswordField loginPassword;
    private ViewManager viewManager;

    @FXML
    void print(ActionEvent event) {
        //System.out.println("Username: " + loginUsername.getText());
        checkLogin();
    }
    @FXML
    void enterPressed(KeyEvent event) {
        checkLogin();
    }

    private void checkLogin() {
        if (loginUsername.getText().equalsIgnoreCase("admin") && loginPassword.getText().equals("admin")){
            //viewManager.showDisplayScreen(loginUsername.getText());
        } else {
            System.out.println("wrong password");
        }
    }

    public void initManager(ViewManager viewManager) {

        this.viewManager = viewManager;
    }
}