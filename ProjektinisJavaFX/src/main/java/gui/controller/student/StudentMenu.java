package gui.controller.student;

import gui.manager.ViewManager;
import gui.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

import static gui.utils.Utils.showLecturerEditProfile;
import static gui.utils.dbUtils.UserDB.getUser;

public class StudentMenu {

    @FXML
    private Button buttonViewUsers;

    @FXML
    private Button buttonAllCourses;

    @FXML
    private Button logout;

    @FXML
    private MenuItem menuItemLogout;

    @FXML
    private MenuItem editProfile;

    @FXML
    private Label username;

    @FXML
    private Button buttonViewCourses;
    private ViewManager viewManager;
    private String usernameToPass;

    @FXML
    void logout(ActionEvent event) {
        if (event.getSource()==logout || event.getSource()== menuItemLogout){
            viewManager.showLoginScreen();
        }
    }

    @FXML
    void showCourses(ActionEvent event) {
        if (event.getSource()==buttonAllCourses){
            viewManager.showStudentAllCourses(usernameToPass);
        }
    }

    @FXML
    void showEditProfile(ActionEvent event) {
        if(event.getSource()==editProfile){
            try {
                User user = getUser(usernameToPass);
                showLecturerEditProfile(user);
            } catch (Exception e) {
            }
        }

    }

    @FXML
    void showMyCourses(ActionEvent event) {
        if(event.getSource()==buttonViewCourses){
            viewManager.showStudentMyCourses(usernameToPass);
        }
    }

    @FXML
    void showUsers(ActionEvent event) {
        if(event.getSource()==buttonViewUsers){
            viewManager.showStudentUserList(this.usernameToPass);
        }
    }

    public void initManager(ViewManager viewManager, String username) {
        this.viewManager = viewManager;
        this.usernameToPass = username;
        this.username.setText(username);
    }
}
