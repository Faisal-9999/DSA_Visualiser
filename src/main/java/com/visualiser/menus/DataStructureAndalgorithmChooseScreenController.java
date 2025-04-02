package com.visualiser.menus;

import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

//TODO: ADD WORKING FOR THIS MAKE SURE WHEN THE START BUTTON IS PRESSED ON THE MENU THEN THE USER HAS TO CHOOSE BETWEEN
//TODO: DATA STRUCTURES OR ALGORITHMS
//TODO: FIX THE MENU SYSTEM

public class DataStructureAndalgorithmChooseScreenController {
    Stage stage;

    @FXML
    private void onAlgorithmsClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/GUI/algorithms_choose_screen.fxml");
    }

    @FXML
    private void onDataStructuresClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/GUI/data_structure_choose_screen.fxml");
    }

    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/GUI/main_menu.fxml");
    }
}

