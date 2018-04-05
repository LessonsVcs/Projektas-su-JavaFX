package gui.controller.admin;

import gui.manager.ViewManager;
import gui.model.Course;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import static gui.utils.dbUtils.CourseDB.getCourse;

public class AdminEditProfile {

    @FXML
    private Button buttonBack;

    @FXML
    private MenuItem menuItemLogout;

    @FXML
    private MenuItem editProfile;

    @FXML
    private Label usernameTitle;

    @FXML
    private TextField username;

    @FXML
    private TextField name;

    @FXML
    private TextField lastname;

    @FXML
    private TextField email;

    @FXML
    private RadioButton radioAdmin;

    @FXML
    private ToggleGroup Roles;

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
        if(event.getSource()==buttonSave){

        }
    }


    public void initManager(ViewManager viewManager, Stage secondStage) {

        this.viewManager = viewManager;

        this.secondStage = secondStage;
    }

    public void initManager(ViewManager viewManager, Stage secondStage, int courseID) {

        this.viewManager = viewManager;

        this.secondStage = secondStage;


    }
}
