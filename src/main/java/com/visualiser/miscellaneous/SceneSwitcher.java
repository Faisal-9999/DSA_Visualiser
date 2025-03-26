package com.visualiser.miscellaneous;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

    public static void switch_scene(ActionEvent e, Stage stage, String file_name) throws IOException {
        Parent root = FXMLLoader.load(SceneSwitcher.class.getResource(file_name));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreenExitHint("");
        stage.setResizable(true);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.keyCombination("esc"));
        stage.show();
    }

    public static void backDataStructure(ActionEvent e, Stage stage) throws IOException {
        switch_scene(e, stage, "/com/visualiser/GUI/data_structure_choose_screen.fxml");
    }

    public static void backAlgorithms(ActionEvent e, Stage stage) throws IOException {
        switch_scene(e, stage, "/com/visualiser/GUI/algorithms_choose_screen.fxml");
    }

}
