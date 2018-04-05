package gui.controller;

import gui.manager.ViewManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import static gui.utils.dbUtils.UserDB.checkUsername;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Label warningLabel;

    private ViewManager viewManager;


    @FXML
    void print(ActionEvent event) {
        if(event.getSource()==loginButton) {
            checkLogin();
        }
    }

    private void checkLogin() {
        String password = checkUsername(loginUsername.getText());
        if (password == null) {
            warningLabel.setText("Incorrect username");
        } else {
            if (password.equals(loginPassword.getText())){
                viewManager.showAdminMenu(loginUsername.getText());
            } else {
                warningLabel.setText("Incorrect password");
            }

        }
    }

    public void initManager(ViewManager viewManager) {

        this.viewManager = viewManager;
    }
}