package gui.manager;

import gui.controller.LoginController;
import gui.controller.admin.AdminMenu;
import gui.controller.admin.courses.AdminAddUserToCourse;
import gui.controller.admin.courses.AdminEditCourse;
import gui.controller.admin.courses.AdminManageCourses;
import gui.controller.admin.courses.AdminShowCourse;
import gui.controller.admin.users.AdminEditProfile;
import gui.controller.admin.users.AdminManageUsers;
import gui.controller.lecturer.LecturerMenu;
import gui.controller.lecturer.users.LecturerEditProfile;
import gui.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewManager {
    Scene scene;

    public ViewManager(Scene scene) {
        this.scene = scene;
    }

    public void showLoginScreen() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loginView.fxml"));
        try {
            scene.setRoot(loader.load());
            LoginController controller = loader.getController();
            controller.initManager(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showAdminMenu(String username) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/adminMenu.fxml"));
        try {
            scene.setRoot(loader.load());
            AdminMenu controller = loader.getController();
            controller.initManager(this, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAdminManageCourse(String username) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/courses/adminManageCourses.fxml"));
        try {
            scene.setRoot(loader.load());
            AdminManageCourses controller = loader.getController();
            controller.initManager(this, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAdminShowCourse(String courseID, String username, String courseName) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/courses/adminShowCourse.fxml"));
        try {
            scene.setRoot(loader.load());
            AdminShowCourse controller = loader.getController();
            controller.initManager(this, Integer.parseInt(courseID), username, courseName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAdminAddUserToCourse(ViewManager viewManager, Stage secondStage, int courseID) {
        FXMLLoader loader = new FXMLLoader(viewManager.getClass().getResource("/view/admin/courses/adminAddUserToCourse.fxml"));
        try {
            viewManager.scene.setRoot(loader.load());
            AdminAddUserToCourse controller = loader.getController();
            controller.initManager(viewManager, secondStage, courseID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAdminEditProfile(Stage secondStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/users/adminEditProfile.fxml"));
        try {
            scene.setRoot(loader.load());
            AdminEditProfile controller = loader.getController();
            controller.initManager(this, secondStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAdminEditProfile(Stage secondStage, User user) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/users/adminEditProfile.fxml"));
        try {
            scene.setRoot(loader.load());
            AdminEditProfile controller = loader.getController();
            controller.initManager(this, secondStage, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAdminEditCourse(Stage secondStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/courses/adminEditCourse.fxml"));
        try {
            scene.setRoot(loader.load());
            AdminEditCourse controller = loader.getController();
            controller.initManager(this, secondStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAdminEditCourse(Stage secondStage, String courseID) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/courses/adminEditCourse.fxml"));
        try {
            scene.setRoot(loader.load());
            AdminEditCourse controller = loader.getController();
            controller.initManager(this, secondStage, courseID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAdminManageUsers(String username) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/admin/users/adminManageUsers.fxml"));
        try {
            scene.setRoot(loader.load());
            AdminManageUsers controller = loader.getController();
            controller.initManager(this, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLecturerMenu(String username) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/lecturer/lecturerMenu.fxml"));
        try {
            scene.setRoot(loader.load());
            LecturerMenu controller = loader.getController();
            controller.initManager(this, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLecturerEditProfile(Stage secondStage, User user) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/lecturer/users/lecturerEditProfile.fxml"));
        try {
            scene.setRoot(loader.load());
            LecturerEditProfile controller = loader.getController();
            controller.initManager(this, secondStage, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}