package com.lexkom.controll;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DiplomaFX extends Application {
    private Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DiplomaFX.class.getResource("../view/NewLoginView.fxml"));
            AnchorPane welcomeLayout = (AnchorPane) loader.load();


            // Show the scene containing the root layout.
            Scene scene = new Scene(welcomeLayout);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setTitle("MyDiploma");
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

