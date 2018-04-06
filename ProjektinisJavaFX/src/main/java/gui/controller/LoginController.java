package gui.controller;

import gui.manager.ViewManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static gui.utils.dbUtils.UserDB.checkUsername;
import static gui.utils.dbUtils.UserDB.getRole;

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
        if (event.getSource() == loginButton) {
            checkLogin();
        }
    }

    private void checkLogin() {
        String password = checkUsername(loginUsername.getText());
        if (password == null) {
            warningLabel.setText("Incorrect username");
        } else {
            if (password.equals(loginPassword.getText())) {
                switch (getRole(loginUsername.getText())) {
                    case ADMIN:
                        viewManager.showAdminMenu(loginUsername.getText());
                        break;
                    case LECTURER:
                        viewManager.showLecturerMenu(loginUsername.getText());
                        break;
                    case STUDENT:
                        break;
                }
            } else {
                warningLabel.setText("Incorrect password");
            }
        }
    }

    @FXML
    void enterPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            checkLogin();
        }
    }

    public void initManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}