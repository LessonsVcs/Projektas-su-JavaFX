package gui.controller.lecturer.courses;

import gui.manager.ViewManager;
import gui.model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

import static gui.Main.getPrimaryStage;
import static gui.utils.Utils.showLecturerEditProfile;
import static gui.utils.dbUtils.RelationDB.getUsersInCourse;
import static gui.utils.dbUtils.RelationDB.removeFromCourseDB;
import static gui.utils.dbUtils.UserDB.getUser;

public class LecturerShowMyCourse {


    @FXML
    private Button courseAddUser;

    @FXML
    private Button courseRemoveUser;

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
            viewManager.showLecturerMyCourses(username);
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

    @FXML
    void removeFromCourse(ActionEvent event) {
        if (event.getSource() == courseRemoveUser) {
            try {
                User user = userTable.getSelectionModel().getSelectedItem();
                removeFromCourseDB(Integer.parseInt(user.getID()), courseID);
                updateTable();
            } catch (Exception e) {
            }
        }
    }

    @FXML
    void showAddUserWindow(ActionEvent event) {
        if (event.getSource() == courseAddUser) {
            openAddUserWindow();
        }
    }

    private void openAddUserWindow() {
        Scene secondWindow = new Scene(new StackPane());
        ViewManager viewManager = new ViewManager(secondWindow);

        Stage secondStage = new Stage();
        secondStage.initModality(Modality.WINDOW_MODAL);
        secondStage.initOwner(getPrimaryStage().getScene().getWindow());
        secondStage.setScene(secondWindow);
        ViewManager.showLecturerAddUserToCourse(viewManager, secondStage, courseID);
        secondStage.showAndWait();
        updateTable();
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
