package com.visualiser.dsa_visualiser;

import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.LinkedHashMap;

//TODO: REDO THIS COMPLETELY

public class HashMapController {

    private final double NODE_WIDTH = 150;
    private final double NODE_HEIGHT = 75;

    private final double startX = 600;
    private final double startY = 550;

    private final LinkedHashMap<Integer, Integer> integerMap = new LinkedHashMap<>();

    private final int MAX_KEYS = 12;

    @FXML
    public TextField insertKeyField;
    @FXML
    public TextField insertValueField;

    @FXML
    public TextField incrementKeyField;

    @FXML
    public TextField decrementKeyField;

    @FXML
    public TextField removeKeyField;

    @FXML
    private Pane map_panel;

    @FXML
    private Stage stage;

    @FXML
    private AnchorPane map_screen;

    @FXML
    private void onInsertClick() {

    }

    @FXML
    private void onIncrementClick() {
        String incrementKey = incrementKeyField.getText();
    }

    @FXML
    private void onDecrementClick() {
        String decrementKey = decrementKeyField.getText();
    }

    @FXML
    private void onResetClick() {
        map_panel.getChildren().clear();
        integerMap.clear();
    }

    @FXML
    private void onRemoveClick() {

    }

    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.back(e, stage);
    }
}
