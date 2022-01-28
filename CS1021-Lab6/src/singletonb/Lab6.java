/*
 * Course: CS1021 - 021
 * Winter 2021
 * Lab 6 - Exceptions
 * Name: Benjamin Singleton
 * Created: 01/19/2022
 * Modified: 01/24/2022
 */
package singletonb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Creates the Website Tester applications
 * @author singletonb
 */
public class Lab6 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        final Parent parent;
        parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("lab6.fxml")));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Website Tester");
        stage.show();
    }
}