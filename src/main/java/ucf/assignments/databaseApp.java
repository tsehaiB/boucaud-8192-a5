/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Tsehai Boucaud
 */
package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.IOException;

public class databaseApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        //load parent FXML file
        Parent root = FXMLLoader.load(getClass().getResource("databaseContainer.fxml"));
        //create and initialize scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        //set scene attributes: Icon, title
        primaryStage.getIcons().add(createIcon());
        primaryStage.setTitle("Database");
        primaryStage.show();
    }
    private Image createIcon() throws IOException {
        FileInputStream inputStream = new FileInputStream(("src/images/DatabaseIcon.png"));
        return new Image(inputStream);
    }
}
