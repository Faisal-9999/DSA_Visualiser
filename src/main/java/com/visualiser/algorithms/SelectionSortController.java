package com.visualiser.algorithms;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SelectionSortController {

    private ArrayList<Integer> elements = null;
    private final double NODE_WIDTH = 75;
    private final double NODE_HEIGHT = 75;

    @FXML
    private Stage stage;

    @FXML
    private AnchorPane bubble_screen;

    @FXML
    private Pane bubble_panel;

    @FXML
    private TextField elementsNumberField;

    @FXML
    private Label writeableArea;

}
