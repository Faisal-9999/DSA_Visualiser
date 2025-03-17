package com.visualiser.dsa_visualiser;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

import java.util.HashMap;

import java.util.ArrayList;

enum MapType {
    IntegerHashMap,
    StringHashMap,
    CharacterHashMap
}

//TODO: RECHECK NODE CREATION FUNCTIONS AND THEN CONTINUE WORK
//TODO: ADD GUI FOR THE HASHMAP AND CUSTOM ERROR MESSAGES IF FOR EXAMPLE THE CURRENT
//TODO: HASHMAP IS OF STRING TYPE AND THE USER tries to add an integer as key

public class HashMapController {

    private final double NODE_WIDTH = 150;
    private final double NODE_HEIGHT = 75;

    ArrayList<StackPane> HashMapNodes = new ArrayList<>();

    private MapType currentMap = null;

    private final int MAX_KEYS = 12;

    @FXML
    public TextField insertKeyField;
    @FXML
    public  TextField insertValueField;

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

    private StackPane createNode() {
        Rectangle rectangle = new Rectangle(NODE_WIDTH, NODE_HEIGHT);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        Line dividerLine = new Line();
        dividerLine.setStartX(0);
        dividerLine.setEndX(0);
        dividerLine.setStartY(0);
        dividerLine.setEndY(NODE_HEIGHT);

        dividerLine.setTranslateX(NODE_WIDTH / 2);
        dividerLine.setStroke(Color.BLACK);

        StackPane node = new StackPane();
        node.getChildren().addAll(rectangle, dividerLine);

        return node;
    }

    private StackPane createIntMapNode(int value, int key) {
        if (currentMap == MapType.StringHashMap || currentMap == MapType.CharacterHashMap) {
            return null;
        }

        if (currentMap == null)
            currentMap = MapType.IntegerHashMap;

        StackPane IntegerNode = createNode();

        Label nodeKey = new Label("" + value);
        Label nodeValue = new Label("" + key);

        StackPane.setAlignment(nodeKey, Pos.CENTER_LEFT);
        StackPane.setAlignment(nodeValue, Pos.CENTER_RIGHT);

        return IntegerNode;
    }

    private StackPane createStringMapNode(String word, int key) {
        if (currentMap == MapType.IntegerHashMap || currentMap == MapType.CharacterHashMap) {
            return null;
        }

        if (currentMap == null)
            currentMap = MapType.StringHashMap;

        StackPane StringNode = createNode();

        Label nodeKey = new Label(word);
        Label nodeValue = new Label("" + key);

        StackPane.setAlignment(nodeKey, Pos.CENTER_LEFT);
        StackPane.setAlignment(nodeValue, Pos.CENTER_RIGHT);

        return StringNode;
    }

    private StackPane createCharacterMapNode(char c, int key) {
        if (currentMap == MapType.IntegerHashMap || currentMap == MapType.StringHashMap) {
            return null;
        }

        if (currentMap == null)
            currentMap = MapType.StringHashMap;

        StackPane CharacterNode = createNode();

        Label nodeKey = new Label("" + c);
        Label nodeValue = new Label("" + key);

        StackPane.setAlignment(nodeKey, Pos.CENTER_LEFT);
        StackPane.setAlignment(nodeValue, Pos.CENTER_RIGHT);

        return CharacterNode;
    }

    @FXML
    private void onIncrementClick() {

    }

    @FXML
    private void onDecrementClick() {

    }

    @FXML
    private void onResetClick() {

    }

    @FXML
    private void onRemoveClick() {

    }

    @FXML
    private void onBackClick() {

    }
}
