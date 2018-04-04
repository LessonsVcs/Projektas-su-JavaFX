package gui.controller.admin;

import gui.manager.ViewManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static gui.Main.getPrimaryStage;


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

    @FXML
    void backToCourses(ActionEvent event) {
        viewManager.showAdminManageCourse();
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

    public void initManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
