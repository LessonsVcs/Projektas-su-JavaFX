package gui.controller.lecturer.users;

import gui.manager.ViewManager;
import gui.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.Optional;

import static gui.utils.dbUtils.UserDB.updateUserProfileDB;

public class LecturerEditProfile {

    @FXML
    private Button buttonBack;

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
    private String oldpassword;

    @FXML
    void back(ActionEvent event) {
        if(event.getSource()==buttonBack){
            secondStage.close();
        }
    }


    @FXML
    void save(ActionEvent event) {
        if(event.getSource()==buttonSave){
            updateProfile();
        }
    }

    private void updateProfile() {
        Alert alertConfimation = new Alert(Alert.AlertType.CONFIRMATION);
        User user = new User();

        if (password.getText().isEmpty() || password.getText() == null) {
            user.setPassword(this.user.getPassword());
        } else {
            if(oldpassword.equals(password.getText())){
                user.setPassword(this.user.getPassword());
            } else {
                user.setPassword(password.getText());
            }
        }
        user.setID(this.user.getID());
        user.setFirstName(name.getText());
        user.setLastName(lastname.getText());
        user.setAddress(address.getText());
        user.setEmail(email.getText());
        alertConfimation.setTitle("Confirmation");
        alertConfimation.setHeaderText("Profile will be updated");
        alertConfimation.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alertConfimation.showAndWait();
        if (result.get() == ButtonType.OK) {
            updateUserProfileDB(user);
        }
    }

    public void initManager(ViewManager viewManager, Stage secondStage, User user) {
        this.viewManager = viewManager;
        this.secondStage = secondStage;
        this.usernameTitle.setText(user.getFirstName());
        this.user = user;
        this.oldpassword = user.getPassword();
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