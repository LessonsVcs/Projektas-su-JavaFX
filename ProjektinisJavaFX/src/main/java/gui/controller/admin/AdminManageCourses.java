package gui.controller.admin;

import gui.manager.ViewManager;
import gui.model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.annotation.PostConstruct;

public class AdminManageCourses {

    @FXML
    private Button editCourse;

    @FXML
    private Button createCourse;

    @FXML
    private Button goBack;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem editProfile;

    @FXML
    private Label username;

    @FXML
    private Button deleteCourse;

    @FXML
    private Button showCourse;
    private ViewManager viewManager;


    @FXML
    void createCourse(ActionEvent event) {

    }

    @FXML
    void deleteCourse(ActionEvent event) {

    }

    @FXML
    void editCourse(ActionEvent event) {

    }

    @FXML
    void editProfile(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {
        viewManager.showLoginScreen();
    }

    @FXML
    void showAdminMenu(ActionEvent event) {

    }


    public void initManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    @FXML
    void buttonClicked(ActionEvent event) {
        if(event.getSource()==showCourse){
            viewManager.showAdminShowCourse();
        }
    }

}