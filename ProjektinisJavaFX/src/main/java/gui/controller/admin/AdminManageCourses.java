package gui.controller.admin;

import gui.manager.ViewManager;
import gui.model.Course;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.*;

import static gui.Main.getPrimaryStage;
import static gui.utils.dbUtils.CourseDB.deleteCourseDB;
import static gui.utils.dbUtils.CourseDB.getCourses;

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
    void createCourse(ActionEvent event) {
        if(event.getSource()==createCourse){
            Scene secondWindow = new Scene(new StackPane());
            ViewManager viewManager = new ViewManager(secondWindow);

            Stage secondStage = new Stage();
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(getPrimaryStage().getScene().getWindow());
            secondStage.setScene(secondWindow);
            viewManager.showAdminEditCourse(secondStage);
            secondStage.showAndWait();
            updateTable();
        }
    }

    @FXML
    void deleteCourse(ActionEvent event) {
        if(event.getSource()==deleteCourse){
            Course course = courseTable.getSelectionModel().getSelectedItem();
            deleteCourseDB(Integer.parseInt(course.getID()));
            updateTable();

        }
    }

    @FXML
    void editCourse(ActionEvent event) {
        if(event.getSource()==editCourse){
            try {
                Course course = courseTable.getSelectionModel().getSelectedItem();
                Scene secondWindow = new Scene(new StackPane());
                ViewManager viewManager = new ViewManager(secondWindow);

                Stage secondStage = new Stage();
                secondStage.initModality(Modality.WINDOW_MODAL);
                secondStage.initOwner(getPrimaryStage().getScene().getWindow());
                secondStage.setScene(secondWindow);
                viewManager.showAdminEditCourse(secondStage,course.getID());
                secondStage.showAndWait();
                updateTable();
            } catch (Exception e){

            }
        }
    }

    @FXML
    void editProfile(ActionEvent event) {
        if(event.getSource()==editProfile){
//            viewManager.showAdminMenu();
        }
    }

    @FXML
    void logout(ActionEvent event) {
        if(event.getSource()==logout){
            viewManager.showLoginScreen();
        }
    }

    @FXML
    void showAdminMenu(ActionEvent event) {
        if(event.getSource()==goBack){
            viewManager.showAdminMenu(usernameToPass);
        }
    }


    public void initManager(ViewManager viewManager, String username) {
        this.viewManager = viewManager;
        this.username.setText(username);
        this.usernameToPass = username;

        updateTable();
    }

    public void updateTable() {
        HashMap<Integer, Course> courseHashMap = getCourses();
        List<Course> list = FXCollections.observableArrayList();
        courseID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        courseName.setCellValueFactory(new PropertyValueFactory<>("name"));
        courseDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        courseDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        courseCredits.setCellValueFactory(new PropertyValueFactory<>("credits"));

        for(Integer i : courseHashMap.keySet()){
            Course course = new Course();
            course.setID(courseHashMap.get(i).getID());
            course.setName(courseHashMap.get(i).getName());
            course.setDescription(courseHashMap.get(i).getDescription());
            course.setCredits(courseHashMap.get(i).getCredits());
            course.setStartDate(courseHashMap.get(i).getStartDate());
            list.add(course);

        }
        courseTable.setItems(FXCollections.observableArrayList(list));


    }

    @FXML
    void showSelectedCourse(ActionEvent event) {
        if(event.getSource()==showCourse){
            try {
                Course course = courseTable.getSelectionModel().getSelectedItem();
                viewManager.showAdminShowCourse(course.getID());
            } catch (Exception e){

            }
        }
    }

}