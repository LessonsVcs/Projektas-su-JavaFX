package gui.utils;

import gui.manager.ViewManager;
import gui.model.User;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static gui.Main.getPrimaryStage;

public class Utils {
    private static final Alert ALERT = new Alert(Alert.AlertType.WARNING);
    public static final int CREDIT_LIMIT = 30;

    public static void showAdminEditProfile() {
        Scene secondWindow = new Scene(new StackPane());
        ViewManager viewManager = new ViewManager(secondWindow);

        Stage secondStage = new Stage();
        secondStage.initModality(Modality.WINDOW_MODAL);
        secondStage.initOwner(getPrimaryStage().getScene().getWindow());
        secondStage.setScene(secondWindow);
        viewManager.showAdminEditProfile(secondStage);
        secondStage.showAndWait();
    }

    public static void showAdminEditProfile(User user) {
        Scene secondWindow = new Scene(new StackPane());
        ViewManager viewManager = new ViewManager(secondWindow);

        Stage secondStage = new Stage();
        secondStage.initModality(Modality.WINDOW_MODAL);
        secondStage.initOwner(getPrimaryStage().getScene().getWindow());
        secondStage.setScene(secondWindow);
        viewManager.showAdminEditProfile(secondStage, user);
        secondStage.showAndWait();
    }

    public static void showLecturerEditProfile(User user) {
        Scene secondWindow = new Scene(new StackPane());
        ViewManager viewManager = new ViewManager(secondWindow);

        Stage secondStage = new Stage();
        secondStage.initModality(Modality.WINDOW_MODAL);
        secondStage.initOwner(getPrimaryStage().getScene().getWindow());
        secondStage.setScene(secondWindow);
        viewManager.showLecturerEditProfile(secondStage, user);
        secondStage.showAndWait();
    }

    public static void showWarning(String warningMsg) {
        ALERT.setTitle("Warning");
        ALERT.setContentText(warningMsg);
        ALERT.showAndWait();
    }
}
