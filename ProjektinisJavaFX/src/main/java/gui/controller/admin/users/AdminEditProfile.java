package gui.controller.admin.users;

import gui.manager.ViewManager;
import gui.model.User;
import gui.utils.Roles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static gui.utils.Utils.showWarning;
import static gui.utils.dbUtils.UserDB.checkUsername;
import static gui.utils.dbUtils.UserDB.createUserDB;
import static gui.utils.dbUtils.UserDB.updateUserDB;


public class AdminEditProfile {

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button buttonBack;

    @FXML
    private MenuItem menuItemLogout;

    @FXML
    private TextField username;

    @FXML
    private TextField name;

    @FXML
    private ToggleGroup userRoles;

    @FXML
    private TextField lastname;

    @FXML
    private TextField email;

    @FXML
    private RadioButton radioAdmin;

    @FXML
    private RadioButton radioLecturer;

    @FXML
    private RadioButton radioStudent;

    @FXML
    private TextField address;

    @FXML
    private Button buttonSave;

    @FXML
    private TextField password;
    private ViewManager viewManager;
    private Stage secondStage;
    private User user;
    private String oldName;

    @FXML
    void back(ActionEvent event) {
        if(event.getSource()==buttonBack){
            secondStage.close();
        }
    }

    @FXML
    void logout(ActionEvent event) {
        if(event.getSource()==menuItemLogout){
            viewManager.showLoginScreen();
        }
    }

    @FXML
    void save(ActionEvent event) {
        if(event.getSource()==buttonSave) {
            checkUserInputs();
        }
    }

    private void checkUserInputs() {
        Alert alertConfimation = new Alert(Alert.AlertType.CONFIRMATION);
        try {
            userRoles.getSelectedToggle().getUserData().toString();
            if (username.getText().isEmpty() || username.getText() == null) {
                showWarning("username can't be blank!");
            } else {
                User user = new User();
                if (password.getText().isEmpty() || password.getText() == null) {
                    showWarning("password can't be blank!");
                } else {
                    try {
                        this.user.getID();
                        if(username.getText().equalsIgnoreCase(oldName)){
                            updateUser(alertConfimation, user);
                        } else {
                            if (checkUsername(username.getText())==null){
                                updateUser(alertConfimation, user);
                            } else {
                                showWarning("This username already exists");
                            }
                        }
                    } catch (Exception e){
                        createNewUser(alertConfimation, user);
                    }
                }
            }
        } catch (Exception e){
            showWarning("role can't be unselected!");
        }
    }

    private void updateUser(Alert alertConfimation, User user) {
        alertConfimation.setTitle("Confirmation");
        alertConfimation.setHeaderText("User will be updated");
        alertConfimation.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alertConfimation.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                user.setDateOfBirth(datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            } catch (Exception e) {
                user.setDateOfBirth(null);
            }
            user.setUsername(username.getText());
            user.setPassword(password.getText());
            user.setEmail(email.getText());
            user.setAddress(address.getText());
            user.setFirstName(name.getText());
            user.setLastName(lastname.getText());
            user.setRole(Roles.valueOf(userRoles.getSelectedToggle().getUserData().toString()));
            updateUserDB(user);
        }
    }

    private void createNewUser(Alert alertConfimation, User user) {
        alertConfimation.setTitle("Confirmation");
        alertConfimation.setHeaderText("User will be created");
        alertConfimation.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alertConfimation.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                user.setDateOfBirth(datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            } catch (Exception e) {
                user.setDateOfBirth(null);
            }
            user.setUsername(username.getText());
            user.setPassword(password.getText());
            user.setEmail(email.getText());
            user.setAddress(address.getText());
            user.setFirstName(name.getText());
            user.setLastName(lastname.getText());
            user.setRole(Roles.valueOf(userRoles.getSelectedToggle().getUserData().toString()));
            createUserDB(user);
        }
    }


    public void initManager(ViewManager viewManager, Stage secondStage) {
        this.viewManager = viewManager;
        this.secondStage = secondStage;
        initRadioButtons();
    }

    private void initRadioButtons() {
        radioAdmin.setUserData("ADMIN");
        radioLecturer.setUserData("LECTURER");
        radioStudent.setUserData("STUDENT");
    }

    public void initManager(ViewManager viewManager, Stage secondStage, User user) {
        this.viewManager = viewManager;
        this.secondStage = secondStage;
        this.user = user;
        initRadioButtons();
        preloadInfo(user);
    }

    private void preloadInfo(User user) {
        this.oldName = user.getFirstName();
        this.name.setText(user.getFirstName());
        this.username.setText(user.getUsername());
        this.lastname.setText(user.getLastName());
        this.address.setText(user.getAddress());
        this.email.setText(user.getEmail());
        this.password.setText(user.getPassword());
        try {
            LocalDate date = LocalDate.parse(user.getDateOfBirth(),(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            this.datePicker.setValue(date);
        } catch (Exception e){ }
        switch (user.getRole()){
            case ADMIN:
                radioAdmin.fire();
                break;
            case LECTURER:
                radioLecturer.fire();
                break;
            case STUDENT:
                radioStudent.fire();
                break;
        }
    }
}
