package com.visualiser.dsa_visualiser;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    AnchorPane main_screen;

    Stage stage;

    @FXML
    private void onStartClick() {

    }

    @FXML
    private void onOptionsClick() {

    }

    @FXML
    private void onExitClick() {
        stage = (Stage) main_screen.getScene().getWindow();
        stage.close();
    }
}