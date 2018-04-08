package gui.controller.student.courses;

import gui.manager.ViewManager;
import gui.model.Course;
import gui.model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import static gui.utils.Utils.showLecturerEditProfile;
import static gui.utils.dbUtils.RelationDB.getUserCourses;
import static gui.utils.dbUtils.UserDB.*;

public class StudentMyCourses {


    @FXML
    private Label labelCredits;

    @FXML
    private Button goBack;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem editProfile;

    @FXML
    private Label username;

    @FXML
    private Button showCourse;
    private ViewManager viewManager;
    private String usernameToPass;

    @FXML
    private TableView<Course> courseTable;

    @FXML
    private TableColumn<Course, String> courseID;

    @FXML
    private TableColumn<Course, String> courseName;

    @FXML
    private TableColumn<Course, String> courseDescription;

    @FXML
    private TableColumn<Course, String> courseDate;

    @FXML
    private TableColumn<Course, String> courseCredits;

    @FXML
    void editProfile(ActionEvent event) {
        if (event.getSource() == editProfile) {
            try {
                User user = getUser(usernameToPass);
                showLecturerEditProfile(user);
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

    @FXML
    void showStudentMenu(ActionEvent event) {
        if (event.getSource() == goBack) {
            viewManager.showLecturerMenu(usernameToPass);
        }
    }

    public void initManager(ViewManager viewManager, String username) {
        this.viewManager = viewManager;
        this.username.setText(username);
        this.usernameToPass = username;
        updateTable();

    }

    public void updateTable() {
        this.labelCredits.setText(String.valueOf(getUserCredits(getUserID(usernameToPass))));
        List<Course> list = getUserCourses(getUserID(usernameToPass));
        courseID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        courseName.setCellValueFactory(new PropertyValueFactory<>("name"));
        courseDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        courseDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        courseCredits.setCellValueFactory(new PropertyValueFactory<>("credits"));
        courseTable.setItems(FXCollections.observableArrayList(list));
    }

    @FXML
    void showSelectedCourse(ActionEvent event) {
        if (event.getSource() == showCourse) {
            try {
                Course course = courseTable.getSelectionModel().getSelectedItem();
                viewManager.showStudentShowMyCourse(course.getID(), usernameToPass, course.getName());
            } catch (Exception e) {
            }
        }
    }
}
