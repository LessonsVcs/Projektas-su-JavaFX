package gui.controller.lecturer.users;

import gui.manager.ViewManager;
import gui.model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

import static gui.utils.dbUtils.RelationDB.addToCourse;
import static gui.utils.dbUtils.RelationDB.getLecturerUsersNotInCourse;

public class LecturerAddUserToCourse {

    @FXML
    private Button back;

    @FXML
    private Button buttonAdd;

    private ViewManager viewManager;
    private Stage secondStage;
    private int courseID;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> userID;

    @FXML
    private TableColumn<User, String> userName;

    @FXML
    private TableColumn<User, String> userLastName;

    @FXML
    void addUser(ActionEvent event) {
        if (event.getSource() == buttonAdd) {
            try {
                User user = userTable.getSelectionModel().getSelectedItem();
                addToCourse(Integer.parseInt(user.getID()), courseID);
                updateTable();
            } catch (Exception e) {

            }

        }
    }

    @FXML
    void backToCourse(ActionEvent event) {
        secondStage.close();
    }


    public void initManager(ViewManager viewManager, Stage secondStage, int courseID) {

        this.viewManager = viewManager;

        this.secondStage = secondStage;
        this.courseID = courseID;
        updateTable();
    }

    public void updateTable() {

        List<User> list = getLecturerUsersNotInCourse(courseID);
        userID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        userName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        userLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        userTable.setItems(FXCollections.observableArrayList(list));
    }
}
