package gui.controller.lecturer.courses;

import gui.manager.ViewManager;
import gui.model.Course;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static gui.utils.dbUtils.CourseDB.*;
import static gui.utils.dbUtils.RelationDB.addToCourse;
import static gui.utils.dbUtils.UserDB.getUserID;

public class LecturerEditCourse {

    @FXML
    private Button buttonBack;

    @FXML
    private TextField name;

    @FXML
    private TextArea description;

    @FXML
    private TextField credits;

    @FXML
    private DatePicker startDate;

    @FXML
    private Button buttonSave;
    private ViewManager viewManager;
    private Stage secondStage;
    private String username;
    private int courseID = 0;
    private String oldName;


    @FXML
    void back(ActionEvent event) {
        if (event.getSource() == buttonBack) {
            secondStage.close();
        }
    }

    @FXML
    void save(ActionEvent event) {
        if (event.getSource() == buttonSave) {
            saveChanges();
        }


    }

    private void saveChanges() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Alert alertConfimation = new Alert(Alert.AlertType.CONFIRMATION);

        if (name.getText() == null || name.getText().isEmpty()) {
            alert.setTitle("Warning");
            alert.setContentText("Name cannot be blank!");
            alert.showAndWait();
        } else {
            if (startDate.getValue() == null) {
                alert.setTitle("Warning");
                alert.setContentText("Date can't be empty!");
                alert.showAndWait();
            } else {
                if (courseID == 0) {
                    createCourse(alert, alertConfimation);

                } else {
                    if (name.getText().equalsIgnoreCase(oldName)) {
                        updateCourse(alert, alertConfimation);
                    } else {
                        if (courseNameExist(name.getText())) {
                            alert.setTitle("Warning");
                            alert.setContentText("This name already exists!");
                            alert.showAndWait();
                        } else {
                            updateCourse(alert, alertConfimation);
                        }
                    }
                }
            }
        }
    }

    private void updateCourse(Alert alert, Alert alertConfimation) {
        alertConfimation.setTitle("Confirmation");
        alertConfimation.setHeaderText("Course will be modified");
        alertConfimation.setContentText("Are you ok with this?");
        Course course = new Course();
        course.setDescription(description.getText());
        course.setName(name.getText());
        course.setStartDate(startDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        if (credits.getText().isEmpty()) {
            Optional<ButtonType> result = alertConfimation.showAndWait();
            if (result.get() == ButtonType.OK) {
                course.setCredits("0");
                updateCourseValues(course, courseID);
            }
        } else {
            Optional<ButtonType> result = alertConfimation.showAndWait();
            if (result.get() == ButtonType.OK) {
                course.setCredits(credits.getText());
                updateCourseValues(course, courseID);
            }
        }
        try {
        } catch (Exception e) {
            alert.setTitle("Warning");
            alert.setContentText("Credit input must be numeric only!");
            alert.showAndWait();
        }
    }

    private void createCourse(Alert alert, Alert alertConfimation) {
        if (courseNameExist(name.getText())) {
            alert.setTitle("Warning");
            alert.setContentText("This name already exists!");
            alert.showAndWait();
        } else {
            Integer creditsINT;
            alertConfimation.setTitle("Confirmation");
            alertConfimation.setHeaderText("Course will be created");
            alertConfimation.setContentText("Are you ok with this?");
            if (credits.getText().isEmpty()) {

                Optional<ButtonType> result = alertConfimation.showAndWait();
                if (result.get() == ButtonType.OK) {
                    newCourseDB(name.getText(), description.getText(),
                            startDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), "");
                    addToCourse(getUserID(username), getCourseID(name.getText()));
                }
            } else {
                Optional<ButtonType> result = alertConfimation.showAndWait();
                if (result.get() == ButtonType.OK) {
                    creditsINT = Integer.parseInt(credits.getText());
                    newCourseDB(name.getText(), description.getText(),
                            startDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), creditsINT.toString());
                    addToCourse(getUserID(username), getCourseID(name.getText()));
                }
            }
            try {
            } catch (Exception e) {
                alert.setTitle("Warning");
                alert.setContentText("Credit input must be numeric only!");
                alert.showAndWait();
            }
        }
    }

    public void initManager(ViewManager viewManager, Stage secondStage, String username) {
        this.viewManager = viewManager;
        this.secondStage = secondStage;
        this.username = username;
    }

    public void initManager(ViewManager viewManager, Stage secondStage, String username, String courseID) {
        this.viewManager = viewManager;
        this.username = username;
        this.courseID = Integer.parseInt(courseID);
        this.secondStage = secondStage;
        setUpValues();


    }

    private void setUpValues() {
        Course course = getCourse(this.courseID);
        LocalDate date = LocalDate.parse(course.getStartDate(), (DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        name.setText(course.getName());
        this.oldName = course.getName();
        description.setText(course.getDescription());
        startDate.setValue(date);
        credits.setText(course.getCredits());
    }

}
