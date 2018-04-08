package gui.controller.student.courses;

import gui.manager.ViewManager;
import gui.model.Course;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Calendar;
import java.util.List;

import static gui.utils.FormatedDate.SIMPLE_DATE_FORMAT;
import static gui.utils.Utils.CREDIT_LIMIT;
import static gui.utils.Utils.showLecturerEditProfile;
import static gui.utils.Utils.showWarning;
import static gui.utils.dbUtils.CourseDB.getCourses;
import static gui.utils.dbUtils.RelationDB.addToCourse;
import static gui.utils.dbUtils.RelationDB.lecturerInCourse;
import static gui.utils.dbUtils.UserDB.getUser;
import static gui.utils.dbUtils.UserDB.getUserCredits;
import static gui.utils.dbUtils.UserDB.getUserID;

public class StudentAllCourses {

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
    private Button register;


    @FXML
    private Button showCourse;
    private String usernameToPass;
    private ViewManager viewManager;

    @FXML
    void editProfile(ActionEvent event) {
        if(event.getSource()==editProfile){
            showLecturerEditProfile(getUser(usernameToPass));
        }
    }

    @FXML
    void logout(ActionEvent event) {
        if(event.getSource()==logout){
            viewManager.showLoginScreen();
        }

    }

    @FXML
    void showStudentMenu(ActionEvent event) {
        if(event.getSource()==goBack){
            viewManager.showStudentMenu(usernameToPass);
        }
    }

    @FXML
    void registerToCourse(ActionEvent event) {
        if (event.getSource()==register){
            register();
        }
    }

    private void register() {
        try {
            Course course = courseTable.getSelectionModel().getSelectedItem();
            if(SIMPLE_DATE_FORMAT.parse(course.getStartDate()).after(Calendar.getInstance().getTime())) {
                int course_id =Integer.parseInt(course.getID());
                if(lecturerInCourse(course_id)) {
                    int student_ID = getUserID(usernameToPass);
                    if (getUserCredits(student_ID) + Integer.parseInt(course.getCredits()) <= CREDIT_LIMIT) {
                        addToCourse(student_ID, Integer.parseInt(course.getID()));
                    } else {
                        showWarning("You have to many credits to enroll to this course");
                    }
                } else {
                    showWarning("Can't register to course without lecturer");
                }
            } else {
                showWarning("Can't register to course after start date");
            }
        } catch (Exception e){}
    }

    public void initManager(ViewManager viewManager, String username) {
        this.viewManager = viewManager;
        this.username.setText(username);
        this.usernameToPass = username;
        updateTable();
    }

    public void updateTable() {
        List<Course> list = getCourses();
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
                viewManager.showStudentShowCourse(course.getID(), usernameToPass, course.getName());
            } catch (Exception e) {
            }
        }
    }
}
