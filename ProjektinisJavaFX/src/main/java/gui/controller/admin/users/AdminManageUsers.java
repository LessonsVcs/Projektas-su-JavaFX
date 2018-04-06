package gui.controller.admin.users;

import gui.manager.ViewManager;
import gui.model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

import static gui.utils.Utils.showAdminEditProfile;
import static gui.utils.dbUtils.UserDB.*;

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
        if (event.getSource() == buttonCreate) {
            showAdminEditProfile();
            updateTable();
        }
    }

    @FXML
    void deleteUser(ActionEvent event) {
        if (event.getSource() == buttonDelete) {
            try {
                User user = userTable.getSelectionModel().getSelectedItem();
                deleteUserDB(Integer.parseInt(user.getID()));
                updateTable();
            } catch (Exception e) {
            }
        }

    }

    @FXML
    void editProfile(ActionEvent event) {
        if (event.getSource() == menuEdit) {
            try {
                User user = getUser(usernameToPass);
                showAdminEditProfile(user);
                updateTable();
            } catch (Exception e) {
            }
        }

    }

    @FXML
    void editUser(ActionEvent event) {
        if (event.getSource() == buttonEdit) {
            User user = userTable.getSelectionModel().getSelectedItem();
            showAdminEditProfile(user);
            updateTable();
        }

    }

    @FXML
    void goToAdminMenu(ActionEvent event) {
        if (event.getSource() == buttonBack) {
            viewManager.showAdminMenu(usernameToPass);
        }
    }

    @FXML
    void logout(ActionEvent event) {
        if (event.getSource() == menuLogout) {
            viewManager.showLoginScreen();
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
        userID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        userName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        userLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        userRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        userEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        userDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        userAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        userUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        userTable.setItems(FXCollections.observableArrayList(list));
    }

}
