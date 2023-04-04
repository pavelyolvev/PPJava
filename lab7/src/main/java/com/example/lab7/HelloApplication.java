package com.example.lab7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final String STYLE_FILE = "style.css";
    @Override
    public void start(Stage stage) throws IOException {

        Controller controller = new Controller();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        controller.setScene(scene);
        stage.setTitle("Bigger or Less game");
        stage.setScene(scene);


        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}