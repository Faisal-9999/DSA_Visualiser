package com.visualiser.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    //COMPLETED MAIN MENU SCREEN
    //Controller classes for options, choose screen
    //exit button is functioning
    //COMPLETED LINKED LIST LOGIC
    //COMPLETED STACK AND QUEUE
    //COMPLETED HASHMAP

    //COMPLETED DATA STRUCTURE SECTION

    @Override
    public void start(Stage stage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/com/visualiser/GUI/main_menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("DSA Visualiser");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setResizable(true);
            stage.setFullScreenExitKeyCombination(KeyCombination.keyCombination("esc"));
            stage.setFullScreenExitHint("");
            stage.show();
        }
        catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        launch();
    }
}