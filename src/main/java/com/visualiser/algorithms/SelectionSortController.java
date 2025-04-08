package com.visualiser.algorithms;

import com.visualiser.miscellaneous.ErrorMessage;
import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SelectionSortController {

    private ArrayList<Integer> elements = null;
    private final double NODE_WIDTH = 75;
    private final double NODE_HEIGHT = 75;

    @FXML
    private Stage stage;

    @FXML
    private AnchorPane selection_screen;

    @FXML
    private Pane selection_panel;

    @FXML
    private TextField elementsNumberField;

    @FXML
    private Label writeableArea;

    @FXML
    private void onResetClick() {
        selection_panel.getChildren().clear();
        elements = new ArrayList<>();
        writeableArea.setText("");
    }


    @FXML
    private void onBackClick(ActionEvent e) {
        try {
            SceneSwitcher.backAlgorithms(e, stage);
        } catch (IOException ex) {
            ErrorMessage.showErrorMessage(selection_screen, stage, "Error Backing", String.valueOf(ex));
        }
    }

}
