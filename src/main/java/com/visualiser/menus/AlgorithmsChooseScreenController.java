package com.visualiser.menus;

import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;

public class AlgorithmsChooseScreenController {

    Stage stage;

    @FXML
    private void onBubbleSortClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/GUI/bubblesort.fxml");
    }

    @FXML
    private void onInsertionSortClick(ActionEvent e) {

    }

    @FXML
    private void onSelectionSortClick(ActionEvent e) {

    }

    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/GUI/data_structureAndalgorithmChooseScreen.fxml");
    }
}
