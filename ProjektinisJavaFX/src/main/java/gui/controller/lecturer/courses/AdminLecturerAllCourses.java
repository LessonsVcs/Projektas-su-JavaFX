package gui.controller.lecturer.courses;

import gui.model.Course;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminLecturerAllCourses {

    @FXML
    private Button goBack;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem editProfile;

    @FXML
    private Label username;

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
    private Button showCourse;

    @FXML
    void editProfile(ActionEvent event) {
        if(event.getSource()==editProfile){

        }

    }

    @FXML
    void logout(ActionEvent event) {
        if(event.getSource()==logout){

        }

    }

    @FXML
    void showLecturerMenu(ActionEvent event) {
        if(event.getSource()==goBack){

        }

    }

    @FXML
    void showSelectedCourse(ActionEvent event) {
        if(event.getSource()==showCourse){

        }

    }

}
