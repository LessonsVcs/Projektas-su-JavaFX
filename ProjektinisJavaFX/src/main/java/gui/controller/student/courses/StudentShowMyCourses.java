package gui.controller.student.courses;

import gui.manager.ViewManager;
import gui.model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import static gui.utils.Utils.showLecturerEditProfile;
import static gui.utils.dbUtils.RelationDB.getUsersInCourse;
import static gui.utils.dbUtils.UserDB.getUser;

public class StudentShowMyCourses {

    @FXML
    private Button courseBack;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem editProfile;

    @FXML
    private Label adminCoursesUsername;

    @FXML
    private Label selectedCourseName;


    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> userID;

    @FXML
    private TableColumn<User, String> userName;

    @FXML
    private TableColumn<User, String> userLastName;

    @FXML
    private TableColumn<User, String> userUsername;

    @FXML
    private TableColumn<User, String> userRole;

    @FXML
    private TableColumn<User, String> userEmail;

    @FXML
    private TableColumn<User, String> userDateOfBirth;

    @FXML
    private TableColumn<User, String> userAddress;

    private int courseID;
    private String username;
    private ViewManager viewManager;

    @FXML
    void backToCourses(ActionEvent event) {
        if (event.getSource() == courseBack) {
            viewManager.showStudentMyCourses(username);
        }
    }

    @FXML
    void editProfile(ActionEvent event) {
        if (event.getSource() == editProfile) {
            try {
                User user = getUser(username);
                showLecturerEditProfile(user);
                updateTable();
            } catch (Exception e) {
            }
        }
    }

    @FXML
    void logout(ActionEvent event) {
        if (event.getSource() == logout) {
            viewManager.showLoginScreen();
        }
    }

    public void initManager(ViewManager viewManager, int courseID, String username, String courseName) {
        this.viewManager = viewManager;
        this.courseID = courseID;
        this.username = username;
        this.adminCoursesUsername.setText(username);
        this.selectedCourseName.setText(courseName);
        updateTable();
    }

    public void updateTable() {
        List<User> list = getUsersInCourse(courseID);
        userID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        userName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        userLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        userUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        userRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        userEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        userDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        userAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        userTable.setItems(FXCollections.observableArrayList(list));
    }
}
