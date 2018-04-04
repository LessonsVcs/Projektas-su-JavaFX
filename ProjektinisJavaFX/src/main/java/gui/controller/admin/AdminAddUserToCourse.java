package gui.controller.admin;

import gui.manager.ViewManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminAddUserToCourse {

    @FXML
    private Button back;
    private ViewManager viewManager;
    private Stage secondStage;

    @FXML
    void backToCourse(ActionEvent event) {
        secondStage.close();
    }


    public void initManager(ViewManager viewManager, Stage secondStage) {

        this.viewManager = viewManager;

        this.secondStage = secondStage;
    }
}
