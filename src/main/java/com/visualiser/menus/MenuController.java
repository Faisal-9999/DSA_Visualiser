package com.visualiser.menus;

import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private AnchorPane main_screen;
    private Stage stage;

    @FXML
    private void onStartClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/GUI/data_structureAndalgorithmChooseScreen.fxml");
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