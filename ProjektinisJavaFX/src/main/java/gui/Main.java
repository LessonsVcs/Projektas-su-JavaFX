package gui;

import gui.manager.ViewManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

import static gui.utils.InitLogger.initLogger;


public class Main extends Application {
    private static Stage stage;

    public static Stage getPrimaryStage() {
        return stage;
    }



    @Override
    public void start(Stage primaryStage){

        Scene scene = new Scene(new StackPane());
        ViewManager viewManager = new ViewManager(scene);
        viewManager.showAdminManageCourse();
        stage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            initLogger();
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch(args);
    }
}