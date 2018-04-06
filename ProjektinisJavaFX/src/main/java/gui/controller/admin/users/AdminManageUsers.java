package gui.controller.admin.users;

import gui.manager.ViewManager;
import gui.model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import static gui.utils.dbUtils.RelationDB.getUsersInCourse;
import static gui.utils.dbUtils.UserDB.getUsers;

public class AdminManageUsers {

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonCreate;

    @FXML
    private Button buttonBack;

    @FXML
    private MenuItem menuLogout;

    @FXML
    private MenuItem menuEdit;

    @FXML
    private Label username;

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

    @FXML
    private Button buttonDelete;
    private String usernameToPass;
    private ViewManager viewManager;

    @FXML
    void createUser(ActionEvent event) {

    }

    @FXML
    void deleteUser(ActionEvent event) {

    }

    @FXML
    void editProfile(ActionEvent event) {

    }

    @FXML
    void editUser(ActionEvent event) {

    }

    @FXML
    void goToAdminMenu(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {
        if(event.getSource()==menuLogout){

        }

    }
    public void initManager(ViewManager viewManager, String username) {
        this.viewManager = viewManager;
        this.usernameToPass = username;
        this.username.setText(username);
        updateTable();

    }
    public void updateTable() {
        List<User> list = getUsers();
        try {

            userID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            userName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            userLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            userRole.setCellValueFactory(new PropertyValueFactory<>("role"));
            userEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            userDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
            userAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            userUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
            userTable.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}
