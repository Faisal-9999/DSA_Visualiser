package com.visualiser.dsa_visualiser;

import com.visualiser.miscellaneous.DataTypeChecker;
import com.visualiser.miscellaneous.ErrorMessage;
import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

import java.io.IOException;

import java.util.LinkedHashMap;
import java.util.Map;

enum MapType {
    IntegerHashMap,
    StringHashMap,
    CharacterHashMap
}

//TODO: FIX INCREMENT LOGIC AND FIX INSERTION LOGIC CANT INSERT THE SAME KEY TWICE IF INSERTED THEN PREVIOUS KEY IS REPLACED WITH NEW ONE
//TODO: FIX SWAPPED POSITIONS OF KEY AND VALUE

public class HashMapController {

    private final double NODE_WIDTH = 150;
    private final double NODE_HEIGHT = 75;

    private final double startX = 600;
    private final double startY = 550;

    private final LinkedHashMap<String, Integer> stringMap = new LinkedHashMap<>();
    private final LinkedHashMap<Character, Integer> characterMap = new LinkedHashMap<>();
    private final LinkedHashMap<Integer, Integer> integerMap = new LinkedHashMap<>();

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
            createIntMapNode(Integer.parseInt(input), Integer.parseInt(value));
        } else if (checker.isCharacter(input)) {
            char c = input.charAt(0);
            createCharacterMapNode(c, Integer.parseInt(value));
        } else {
            createStringMapNode(input, Integer.parseInt(value));
        }

        printMap();
    }

    private void printMap() {
        map_panel.getChildren().clear();
        int currentNodePosition = 0;

        switch (currentMap) {
            case StringHashMap -> {
                for (Map.Entry<String, Integer> entry : stringMap.entrySet()) {
                    StackPane node = createStringMapNode(entry.getKey(), entry.getValue());

                    node.setLayoutX(startX);
                    node.setLayoutY(startY - (currentNodePosition * NODE_HEIGHT));

                    map_panel.getChildren().add(node);
                    currentNodePosition++;
                }
            }
            case IntegerHashMap -> {
                for (Map.Entry<Integer, Integer> entry : integerMap.entrySet()) {
                    StackPane node = createIntMapNode(entry.getKey(), entry.getValue());

                    node.setLayoutX(startX);
                    node.setLayoutY(startY - (currentNodePosition * NODE_HEIGHT));

                    map_panel.getChildren().add(node);
                    currentNodePosition++;
                }
            }
            case CharacterHashMap -> {
                for (Map.Entry<Character, Integer> entry : characterMap.entrySet()) {
                    StackPane node = createCharacterMapNode(entry.getKey(), entry.getValue());

                    node.setLayoutX(startX);
                    node.setLayoutY(startY - (currentNodePosition * NODE_HEIGHT));

                    map_panel.getChildren().add(node);
                    currentNodePosition++;
                }
            }
        }
    }

    private StackPane createIntMapNode(int value, int key) {
        if (currentMap == MapType.StringHashMap || currentMap == MapType.CharacterHashMap) {
            return null;
        }

        if (currentMap == null)
            currentMap = MapType.IntegerHashMap;

        StackPane node = new StackPane();
        node.setPrefSize(NODE_WIDTH, NODE_HEIGHT);


        Rectangle rectangle = new Rectangle(NODE_WIDTH, NODE_HEIGHT);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);


        HBox hBox = new HBox();
        hBox.setPrefSize(NODE_WIDTH, NODE_HEIGHT);
        hBox.setAlignment(Pos.CENTER);


        Label keyLabel = new Label(String.valueOf(key));
        Label valueLabel = new Label(String.valueOf(value));
        keyLabel.setPrefWidth(NODE_WIDTH / 2);
        keyLabel.setAlignment(Pos.CENTER);
        valueLabel.setPrefWidth(NODE_WIDTH / 2);
        valueLabel.setAlignment(Pos.CENTER);

        hBox.getChildren().addAll(valueLabel, keyLabel);


        Line dividerLine = new Line(0, -NODE_HEIGHT / 2, 0, NODE_HEIGHT / 2);
        dividerLine.setStroke(Color.BLACK);
        dividerLine.setStrokeWidth(1);


        node.getChildren().addAll(rectangle, hBox, dividerLine);

        integerMap.put(key, value);

        return node;
    }

    private StackPane createStringMapNode(String word, int key) {
        if (currentMap == MapType.IntegerHashMap || currentMap == MapType.CharacterHashMap) {
            return null;
        }

        if (currentMap == null)
            currentMap = MapType.StringHashMap;

        StackPane node = new StackPane();
        node.setPrefSize(NODE_WIDTH, NODE_HEIGHT);


        Rectangle rectangle = new Rectangle(NODE_WIDTH, NODE_HEIGHT);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);


        HBox hBox = new HBox();
        hBox.setPrefSize(NODE_WIDTH, NODE_HEIGHT);
        hBox.setAlignment(Pos.CENTER);


        Label keyLabel = new Label(String.valueOf(key));
        Label valueLabel = new Label(word);
        keyLabel.setPrefWidth(NODE_WIDTH / 2);
        keyLabel.setAlignment(Pos.CENTER);
        valueLabel.setPrefWidth(NODE_WIDTH / 2);
        valueLabel.setAlignment(Pos.CENTER);

        hBox.getChildren().addAll(valueLabel, keyLabel);


        Line dividerLine = new Line(0, -NODE_HEIGHT / 2, 0, NODE_HEIGHT / 2);
        dividerLine.setStroke(Color.BLACK);
        dividerLine.setStrokeWidth(1);


        node.getChildren().addAll(rectangle, hBox, dividerLine);

        stringMap.put(word, key);

        return node;
    }

    private StackPane createCharacterMapNode(char c, int key) {
        if (currentMap == MapType.IntegerHashMap || currentMap == MapType.StringHashMap) {
            return null;
        }

        if (currentMap == null)
            currentMap = MapType.CharacterHashMap;

        StackPane node = new StackPane();
        node.setPrefSize(NODE_WIDTH, NODE_HEIGHT);


        Rectangle rectangle = new Rectangle(NODE_WIDTH, NODE_HEIGHT);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);


        HBox hBox = new HBox();
        hBox.setPrefSize(NODE_WIDTH, NODE_HEIGHT);
        hBox.setAlignment(Pos.CENTER);


        Label keyLabel = new Label(String.valueOf(key));
        Label valueLabel = new Label("" + c);
        keyLabel.setPrefWidth(NODE_WIDTH / 2);
        keyLabel.setAlignment(Pos.CENTER);
        valueLabel.setPrefWidth(NODE_WIDTH / 2);
        valueLabel.setAlignment(Pos.CENTER);

        hBox.getChildren().addAll(valueLabel, keyLabel);


        Line dividerLine = new Line(0, -NODE_HEIGHT / 2, 0, NODE_HEIGHT / 2);
        dividerLine.setStroke(Color.BLACK);
        dividerLine.setStrokeWidth(1);


        node.getChildren().addAll(rectangle, hBox, dividerLine);

        characterMap.put(c, key);

        return node;
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
                    ErrorMessage.showErrorMessage(map_screen, stage, "Key Doesn't Exist", "Key " + incrementKey + " doesn't exist");
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
        String decrementKey = decrementKeyField.getText();

        switch (currentMap) {
            case StringHashMap -> {
                if (stringMap.containsKey(decrementKey)) {
                    stringMap.put(decrementKey, stringMap.get(decrementKey) - 1);
                }
                else {
                    ErrorMessage.showErrorMessage(map_screen, stage, "Key Doesn't Exist", "Kay " + decrementKey + " doesn't exist");
                    return;
                }
            }
            case IntegerHashMap -> {

                if (!checker.isInteger(decrementKey)) {
                    ErrorMessage.showErrorMessage(map_screen, stage, "Invalid Key Type", "Key type isn't an integer");
                    return;
                }

                int val = Integer.parseInt(decrementKey);

                if (integerMap.containsKey(val)) {
                    integerMap.put(val, integerMap.get(val) - 1);
                }
                else {
                    ErrorMessage.showErrorMessage(map_screen, stage, "Key Doesn't Exist", "Key called " + decrementKey + " doesn't exist in the hashmap");
                    return;
                }
            }
            case CharacterHashMap -> {
                if (!checker.isCharacter(decrementKey)) {
                    ErrorMessage.showErrorMessage(map_screen, stage, "Invalid Key Type", "Key of this type doesn't exist in a character hashmap");
                    return;
                }

                char character = decrementKey.charAt(0);

                if (characterMap.containsKey(character)) {
                    characterMap.put(character, characterMap.get(character) - 1);
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
    private void onResetClick() {
        map_panel.getChildren().clear();
        currentMap = null;
        stringMap.clear();
        characterMap.clear();
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
