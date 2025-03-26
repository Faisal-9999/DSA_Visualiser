package com.visualiser.data_structures;

import com.visualiser.miscellaneous.Arrow;
import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;

import static com.visualiser.miscellaneous.ErrorMessage.showErrorMessage;
import static java.lang.Integer.parseInt;

public class LinkedListController {

    private final double NODE_HEIGHT = 75;
    private final double NODE_WIDTH = 75;
    private final double HORIZONTAL_GAP = 135;
    private final double VERTICAL_GAP = 125;
    private final double START_X = 100;
    private final double START_Y = 100;
    private final int ROW_LENGTH = 9;
    private final int MAX_NODES = 36;

    private Stage stage;
    private final ArrayList<StackPane> nodesList = new ArrayList<>();

    @FXML
    private Pane link_panel;

    @FXML
    private TextField position_field, add_field, insert_field, insert_value_field;

    @FXML
    private AnchorPane linked_list_screen;

    @FXML
    private void onAddClick() throws NumberFormatException {
        if (nodesList.size() >= MAX_NODES) {
            showErrorMessage(linked_list_screen, stage,"Node Limit Reached", "Can't add more than " + MAX_NODES + " nodes");
            return;
        }
        int value = parseInt(add_field.getText());
        StackPane node = createNode(value);
        nodesList.add(node);
        recalcPositions();
    }

    @FXML
    private void onDeleteClick() throws NumberFormatException {
        if (nodesList.isEmpty()) {
            showErrorMessage(linked_list_screen, stage, "List Already Empty", "Can't delete nodes in an empty list");
            return;
        }
        int pos = parseInt(position_field.getText());
        if (pos < 1 || pos > nodesList.size()) {
            showErrorMessage(linked_list_screen, stage,"Invalid Position", "Please enter a valid node position.");
            return;
        }
        nodesList.remove(pos - 1);
        recalcPositions();
    }

    @FXML
    private void onInsertClick() throws NumberFormatException {
        int value = parseInt(insert_value_field.getText());
        int position = parseInt(insert_field.getText());
        if (position < 1 || position > nodesList.size() + 1) {
            showErrorMessage(linked_list_screen, stage,"Invalid Position", "Please enter a valid position to insert the node.");
            return;
        }
        StackPane node = createNode(value);
        nodesList.add(position - 1, node);
        recalcPositions();
    }

    @FXML
    private void onResetClick() {
        link_panel.getChildren().clear();
        nodesList.clear();
    }

    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
       SceneSwitcher.backDataStructure(e, stage);
    }

    private StackPane createNode(int value) {
        Rectangle rectangle = new Rectangle(NODE_WIDTH, NODE_HEIGHT);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        Text label = new Text(String.valueOf(value));
        label.setFont(Font.font("Sans", 14));
        return new StackPane(rectangle, label);
    }

    private void recalcPositions() {
        link_panel.getChildren().clear();
        for (int i = 0; i < nodesList.size(); i++) {
            StackPane node = nodesList.get(i);
            int row = i / ROW_LENGTH;
            int col = i % ROW_LENGTH;
            double x, y;
            y = START_Y + row * VERTICAL_GAP;
            if (row % 2 == 0) {
                x = START_X + col * HORIZONTAL_GAP;
            } else {
                x = START_X + (ROW_LENGTH - 1 - col) * HORIZONTAL_GAP;
            }
            node.setLayoutX(x);
            node.setLayoutY(y);
            link_panel.getChildren().add(node);
            if (i > 0) {
                StackPane prevNode = nodesList.get(i - 1);
                String direction;
                int prevRow = (i - 1) / ROW_LENGTH;
                if (prevRow == row) {
                    direction = (row % 2 == 0) ? "right" : "left";
                } else {
                    direction = "down";
                }
                addArrowBetweenNodes(prevNode, node, direction);
            }
        }
    }

    private void addArrowBetweenNodes(StackPane nodeA, StackPane nodeB, String direction) {
        double xA, yA, xB, yB;
        switch (direction) {
            case "right" -> {
                xA = nodeA.getLayoutX() + NODE_WIDTH;
                yA = nodeA.getLayoutY() + NODE_HEIGHT / 2;
                xB = nodeB.getLayoutX();
                yB = nodeB.getLayoutY() + NODE_HEIGHT / 2;
            }
            case "left" -> {
                xA = nodeA.getLayoutX();
                yA = nodeA.getLayoutY() + NODE_HEIGHT / 2;
                xB = nodeB.getLayoutX() + NODE_WIDTH;
                yB = nodeB.getLayoutY() + NODE_HEIGHT / 2;
            }
            case "down" -> {
                xA = nodeA.getLayoutX() + NODE_WIDTH / 2;
                yA = nodeA.getLayoutY() + NODE_HEIGHT;
                xB = nodeB.getLayoutX() + NODE_WIDTH / 2;
                yB = nodeB.getLayoutY();
            }
            default -> {
                System.out.println("Incorrect Parameter");
                return;
            }
        }
        Arrow arrow = new Arrow(xA, yA, xB, yB);
        link_panel.getChildren().add(arrow);
    }
}
