package com.visualiser.dsa_visualiser;

import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    AnchorPane main_screen;

    Stage stage;

    @FXML
    private void onStartClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "data_structure_choose_screen.fxml");
    }

    @FXML
    private void onOptionsClick(ActionEvent e) {

    }

    @FXML
    private void onExitClick() {
        stage = (Stage) main_screen.getScene().getWindow();
        stage.close();
    }
}