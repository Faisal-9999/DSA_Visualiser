package com.visualiser.dsa_visualiser;

import com.visualiser.miscellaneous.ErrorMessage;
import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.LinkedHashMap;
import java.util.Map;

//TODO: Fix the remove function not finding the key

public class HashMapController {

    private final double NODE_WIDTH = 75;
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
        String incrementKey = insertKeyField.getText();
        String valueKey = insertValueField.getText();

        try {
            integerMap.put(Integer.parseInt(incrementKey), Integer.parseInt(valueKey));
        }
        catch (Exception e) {
            ErrorMessage.showErrorMessage(map_screen, stage, "Invalid Type Detected", "Enter an integer in the increment and value field");
            return;
        }

        printMap();
    }

    @FXML
    private void onIncrementClick() {
        try {
            int key = Integer.parseInt(incrementKeyField.getText());

            if (integerMap.containsKey(key))
                integerMap.put(key, integerMap.get(key) + 1);
            else {
                ErrorMessage.showErrorMessage(map_screen, stage, "Key Not Found", key + " not found in the hashmap");
                return;
            }
        }
        catch (Exception e) {
            ErrorMessage.showErrorMessage(map_screen, stage, "Invalid Type Detected", "Key type can only be an integer");
            return;
        }

        printMap();
    }

    @FXML
    private void onDecrementClick() {
        try {
            int key = Integer.parseInt(decrementKeyField.getText());

            if (integerMap.containsKey(key))
                integerMap.put(key, integerMap.get(key) - 1);
            else {
                ErrorMessage.showErrorMessage(map_screen, stage, "Key Not Found", key + " not found in the hashmap");
                return;
            }
        }
        catch (Exception e) {
            ErrorMessage.showErrorMessage(map_screen, stage, "Invalid Type Detected", "Key type can only be an integer");
            return;
        }

        printMap();
    }

    @FXML
    private void onResetClick() {
        map_panel.getChildren().clear();
        integerMap.clear();
    }

    @FXML
    private void onRemoveClick() {

        try {
            int key = Integer.parseInt(removeKeyField.getText());

            if (integerMap.containsKey(key))
                integerMap.remove(key);
            else {
                ErrorMessage.showErrorMessage(map_screen, stage, "Key Not Found", key + " not found in the hashmap");
                return;
            }
        }
        catch (Exception e) {
            ErrorMessage.showErrorMessage(map_screen, stage, "Invalid Type Detected", "Enter an integer in the remove key field");
            return;
        }

        printMap();
    }

    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.back(e, stage);
    }

    private StackPane[] createNode(int key, int value) {
        StackPane[] node = new StackPane[2];

        Rectangle rectangle = new Rectangle(NODE_WIDTH, NODE_HEIGHT);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        Text label = new Text(String.valueOf(key));
        label.setFont(Font.font("Sans", 14));
        node[0] = new StackPane(rectangle, label);

        Rectangle rectangle2 = new Rectangle(NODE_WIDTH, NODE_HEIGHT);
        rectangle2.setFill(Color.WHITE);
        rectangle2.setStroke(Color.BLACK);
        Text label2 = new Text(String.valueOf(value));
        label.setFont(Font.font("Sans", 14));
        node[1] = new StackPane(rectangle2, label2);

        return node;
    }

    private void printMap() {

        map_panel.getChildren().clear();

        int position = 0;

        for (Map.Entry<Integer, Integer> set : integerMap.entrySet()) {
            StackPane[] node = createNode(set.getKey(), set.getValue());

            node[0].setLayoutX(startX);
            node[0].setLayoutY(startY - (NODE_HEIGHT * position));

            node[1].setLayoutX(startX + NODE_WIDTH);
            node[1].setLayoutY(startY - (NODE_HEIGHT * position));

            map_panel.getChildren().add(node[0]);
            map_panel.getChildren().add(node[1]);

            position++;
        }
    }
}
