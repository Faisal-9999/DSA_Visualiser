package com.visualiser.dsa_visualiser;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    //COMPLETED MAIN MENU SCREEN
    //Controller classes for options, choose screen
    //exit button is functioning

    //TODO: LEARN HOW TO SWITCH SCREENS THEN MAKE IT SO THAT THE OPTIONS AND START BUTTON WORK
    //TODO: START WORK ON THE LOGIC OF THE LINKED LIST

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main_menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("DSA Visualiser");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setFullScreenExitKeyCombination(KeyCombination.keyCombination("esc"));
        stage.setFullScreenExitHint("");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}