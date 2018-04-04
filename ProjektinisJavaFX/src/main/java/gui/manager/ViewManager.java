package gui.manager;

import gui.controller.admin.AdminAddUserToCourse;
import gui.controller.admin.AdminManageCourses;
import gui.controller.admin.AdminShowCourse;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import gui.controller.LoginController;
import javafx.stage.Stage;

public class ViewManager {
    Scene scene;

    public ViewManager(Scene scene){
        this.scene = scene;
    }
    public void showLoginScreen () {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loginView.fxml"));
        try {
            scene.setRoot(loader.load());
            LoginController controller = loader.getController();
            controller.initManager(this);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void showAdminManageCourse(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/adminManageCourses.fxml"));
        try {
            scene.setRoot(loader.load());
            AdminManageCourses controller = loader.getController();
            controller.initManager(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showAdminShowCourse(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/adminShowCourse.fxml"));
        try {
            scene.setRoot(loader.load());
            AdminShowCourse controller = loader.getController();
            controller.initManager(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showAdminAddUserToCourse(Stage secondStage){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/adminAddUserToCourse.fxml"));
        try {
            scene.setRoot(loader.load());
            AdminAddUserToCourse controller = loader.getController();
            controller.initManager(this,secondStage);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}