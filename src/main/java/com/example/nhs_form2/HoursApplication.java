package com.example.nhs_form2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HoursApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HoursApplication.class.getResource("hours-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 590, 390);
        stage.setTitle("Hours-Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}