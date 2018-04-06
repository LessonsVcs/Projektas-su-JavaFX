package gui.controller.admin;

import gui.manager.ViewManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static gui.Main.getPrimaryStage;

public class AdminMenu {


    private ViewManager viewManager;
    private String usernameToPass;

    @FXML
    private Button manageUsers;

    @FXML
    private Button manageCourses;

    @FXML
    private Button logout;

    @FXML
    private MenuItem menuItemLogout;

    @FXML
    private MenuItem editProfile;

    @FXML
    private Label username;

    @FXML
    void logout(ActionEvent event) {
        if (event.getSource() == logout || event.getSource() == menuItemLogout) {
            viewManager.showLoginScreen();
        }
    }

    @FXML
    void showEditProfile(ActionEvent event) {
        if (event.getSource() == editProfile) {
            Scene secondWindow = new Scene(new StackPane());
            ViewManager viewManager = new ViewManager(secondWindow);

            Stage secondStage = new Stage();
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(getPrimaryStage().getScene().getWindow());
            secondStage.setScene(secondWindow);
            viewManager.showAdminEditProfile(secondStage);
            secondStage.show();
        }
    }

    @FXML
    void showManageCourses(ActionEvent event) {
        if (event.getSource() == manageCourses) {
            viewManager.showAdminManageCourse(usernameToPass);
        }
    }

    @FXML
    void showManageUsers(ActionEvent event) {
        if (event.getSource() == manageUsers) {
            viewManager.showAdminManageUsers(usernameToPass);
        }
    }

    public void initManager(ViewManager viewManager, String username) {
        this.viewManager = viewManager;
        this.usernameToPass = username;
        this.username.setText(username);

    }
}
