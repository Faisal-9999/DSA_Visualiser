package com.visualiser.dsa_visualiser;

import com.visualiser.miscellaneous.DataTypeChecker;
import com.visualiser.miscellaneous.ErrorMessage;
import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

enum MapType {
    IntegerHashMap,
    StringHashMap,
    CharacterHashMap
}

//THE PREVIOUS ARRAYLIST HAS BEEN ABANDONED NOW DIFFERENT HASHMAPS WILL BE USED TO GET THE STACK NODES
//BASICALLY WE JUST KEEP ADDING ON THE LOCATION OF EACH NODE BASED ON WHAT THEIR POSITION IS IN THE HASHMAP
//THE PRINT MAP FUNCTION AND INSERT NODE FUNCTION WILL BE MAINLY UTILISING THIS METHOD

public class HashMapController {

    private final double NODE_WIDTH = 150;
    private final double NODE_HEIGHT = 75;

    private final double startX = 550;
    private final double startY = 400;
    private double currentNodePosition = 0;

    private LinkedHashMap<String, Integer> stringMap = new LinkedHashMap<>();
    private LinkedHashMap<Character, Integer> characterMap = new LinkedHashMap<>();
    private LinkedHashMap<Integer, Integer> integerMap = new LinkedHashMap<>();

    private MapType currentMap = null;

    private DataTypeChecker checker = new DataTypeChecker();

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

        StackPane node = null;

        String input = insertKeyField.getText();
        String value = insertValueField.getText();

        if (!checker.isInteger(value)) {
            ErrorMessage.showErrorMessage(map_screen, stage, "Invalid Key Type", "Key can only be an integer");
            return;
        }

        if (checker.isInteger(input)) {
            int val = Integer.parseInt(input);
            node = createIntMapNode(Integer.parseInt(input), Integer.parseInt(value));
        } else if (checker.isCharacter(input)) {
            char c = input.charAt(0);
            node = createCharacterMapNode(c, Integer.parseInt(value));
        } else {
            node = createStringMapNode(input, Integer.parseInt(value));
        }

        //TODO: ADD CODE HERE FOR ASSIGNING THE STARTING COORDINATES FOR X AND Y

        node.setLayoutX(startX);
        node.setLayoutY(startY - (currentNodePosition * NODE_HEIGHT));

        map_panel.getChildren().add(node);


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

    //This function will be used to print all the nodes again
    //FOR EXAMPLE WHEN THE USER REMOVES A KEY THAT IS IN BETWEEN THE HASHMAP
    private void printMap() {
        //TODO: ADD CODE HERE NEXT
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
            currentMap = MapType.CharacterHashMap;

        StackPane CharacterNode = createNode();

        Label nodeKey = new Label("" + c);
        Label nodeValue = new Label("" + key);

        StackPane.setAlignment(nodeKey, Pos.CENTER_LEFT);
        StackPane.setAlignment(nodeValue, Pos.CENTER_RIGHT);

        return CharacterNode;
    }

    @FXML
    private void onIncrementClick() {
        String incrementKey = incrementKeyField.getText();

        switch (currentMap) {
            case StringHashMap -> {
                if (stringMap.containsKey(incrementKey)) {
                    stringMap.put(incrementKey, stringMap.get(incrementKey) + 1);
                }
                else {
                    ErrorMessage.showErrorMessage(map_screen, stage, "Key Doesn't Exist", "Kay " + incrementKey + " doesn't exist");
                    return;
                }
            }
            case IntegerHashMap -> {

                if (!checker.isInteger(incrementKey)) {
                    ErrorMessage.showErrorMessage(map_screen, stage, "Invalid Key Type", "Can't add a non integer key into an integer hashmap");
                    return;
                }

                int val = Integer.parseInt(incrementKey);

                if (integerMap.containsKey(val)) {
                    integerMap.put(val, integerMap.get(val) + 1);
                }
                else {
                    ErrorMessage.showErrorMessage(map_screen, stage, "Key Doesn't Exist", "Key called " + incrementKey + " doesn't exist in the hashmap");
                    return;
                }
            }
            case CharacterHashMap -> {
                if (!checker.isCharacter(incrementKey)) {
                    ErrorMessage.showErrorMessage(map_screen, stage, "Invalid Key Type", "Key of this type doesn't exist in a character hashmap");
                    return;
                }

                char character = incrementKey.charAt(0);

                if (characterMap.containsKey(character)) {
                    characterMap.put(character, characterMap.get(character) + 1);
                }
                else {
                    ErrorMessage.showErrorMessage(map_screen, stage, "Key Doesn't Exist", "Key called " + character + " doesn't exist in the hashmap");
                    return;
                }
            }
            default -> {
                ErrorMessage.showErrorMessage(map_screen, stage, "Hashmap Doesn't Exist", "Can't increment in a empty hashmap");
                return;
            }
        }

        printMap();
    }

    @FXML
    private void onDecrementClick() {

    }

    @FXML
    private void onResetClick() {
        map_panel.getChildren().clear();
        currentMap = null;
        currentNodePosition = 0;
    }

    @FXML
    private void onRemoveClick() {

    }

    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.back(e, stage);
    }
}
