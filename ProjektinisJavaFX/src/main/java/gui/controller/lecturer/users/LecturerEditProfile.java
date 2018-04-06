package gui.controller.lecturer.users;

import gui.manager.ViewManager;
import gui.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LecturerEditProfile {

    @FXML
    private Button buttonBack;

    @FXML
    private MenuItem menuItemLogout;

    @FXML
    private Label usernameTitle;

    @FXML
    private TextField name;

    @FXML
    private TextField lastname;

    @FXML
    private TextField email;

    @FXML
    private TextField address;

    @FXML
    private Button buttonSave;

    @FXML
    private TextField password;
    private ViewManager viewManager;
    private Stage secondStage;
    private User user;

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

    public void initManager(ViewManager viewManager, Stage secondStage, User user) {
        this.viewManager = viewManager;
        this.secondStage = secondStage;
        this.usernameTitle.setText(user.getFirstName());
        this.user = user;
        preloadInfo(user);
    }

    private void preloadInfo(User user) {
        this.name.setText(user.getFirstName());
        this.lastname.setText(user.getLastName());
        this.address.setText(user.getAddress());
        this.email.setText(user.getEmail());
        this.password.setText(user.getPassword());
    }
}