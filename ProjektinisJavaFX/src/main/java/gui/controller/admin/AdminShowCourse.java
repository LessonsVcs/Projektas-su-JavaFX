package gui.controller.admin;

import gui.manager.ViewManager;
import gui.model.Course;
import gui.model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;

import static gui.Main.getPrimaryStage;
import static gui.utils.dbUtils.CourseDB.getCourses;


public class AdminShowCourse {


    @FXML
    private Button courseAddUser;

    @FXML
    private Button courseRemoveCourse;

    @FXML
    private Button courseBack;

    @FXML
    private MenuItem logout;

    @FXML
    private MenuItem editProfie;

    @FXML
    private Label adminCoursesUsername;

    @FXML
    private Label selectedCourseName;
    private int id;

    @FXML
    void backToCourses(ActionEvent event) {
//        viewManager.showAdminManageCourse();
    }

    @FXML
    void editProfie(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {
        viewManager.showLoginScreen();
    }

    @FXML
    void removeFromCourse(ActionEvent event) {

    }

    @FXML
    void showAddUserWindow(ActionEvent event) {

        Scene secondWindow = new Scene(new StackPane());
        ViewManager viewManager = new ViewManager(secondWindow);

        Stage secondStage = new Stage();
        secondStage.initModality(Modality.WINDOW_MODAL);
        secondStage.initOwner(getPrimaryStage().getScene().getWindow());
        secondStage.setScene(secondWindow);
        viewManager.showAdminAddUserToCourse(secondStage);
        secondStage.show();
    }
    private ViewManager viewManager;

    public void initManager(ViewManager viewManager, int courseID) {
        this.viewManager = viewManager;
        this.id = courseID;
    }
}
//    public void updateTable() {
//        HashMap<Integer, User> courseHashMap = getCourses();
//        List<Course> list = FXCollections.observableArrayList();
//        courseID.setCellValueFactory(new PropertyValueFactory<>("ID"));
//        courseName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        courseDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
//        courseDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
//        courseCredits.setCellValueFactory(new PropertyValueFactory<>("credits"));
//
//        for(Integer i : courseHashMap.keySet()){
//            Course course = new Course();
//            course.setID(courseHashMap.get(i).getID());
//            course.setName(courseHashMap.get(i).getName());
//            course.setDescription(courseHashMap.get(i).getDescription());
//            course.setCredits(courseHashMap.get(i).getCredits());
//            course.setStartDate(courseHashMap.get(i).getStartDate());
//            list.add(course);
//
//        }
//        courseTable.setItems(FXCollections.observableArrayList(list));
//}
