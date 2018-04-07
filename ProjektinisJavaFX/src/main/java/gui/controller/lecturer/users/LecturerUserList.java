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
import java.util.List;

import static gui.utils.dbUtils.UserDB.getUsers;

public class LecturerUserList {

    @FXML
    private Button back;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> userID;

    @FXML
    private TableColumn<User, String> userName;
    @FXML
    private TableColumn<User, String> userEmail;

    @FXML
    private TableColumn<User, String> userLastName;
    private ViewManager viewManager;
    private String username;

    @FXML
    void backToCourse(ActionEvent event) {
        if(event.getSource()==back){
            viewManager.showLecturerMenu(username);
        }

    }
    public void initManager(ViewManager viewManager, String username) {

        this.viewManager = viewManager;
        this.username = username;
        updateTable();
    }

    public void updateTable() {

        List<User> list = getUsers();
        userID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        userName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        userLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        userEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        userTable.setItems(FXCollections.observableArrayList(list));
    }

}
