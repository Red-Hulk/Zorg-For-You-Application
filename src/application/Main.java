package application;

import application.GUIClasses.GUIHome;
import application.GUIClasses.GUILogin;
import application.GUIClasses.GUIMaintenance;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static String userAdmin = "admin";
    public static String passwdAdmin = "admin";

    @Override
    public void start(Stage primaryStage) throws IOException {
        new GUIHome(primaryStage);
        //new GUIMaintenance(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
