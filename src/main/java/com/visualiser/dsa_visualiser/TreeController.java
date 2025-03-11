package com.visualiser.dsa_visualiser;

import com.visualiser.miscellaneous.SceneSwitcher;
import com.visualiser.miscellaneous.TreeNodeArrow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class TreeController {
    private final int NODE_RADIUS = 35;
    private Stage stage;
    private Boolean isFirst = true;
    private final double startX = 625;
    private final double startY = 50;
    Tree tree = new Tree();

    @FXML
    Pane Tree_panel;

    @FXML
    AnchorPane Tree_screen;

    @FXML
    TextField delete_field, add_field;

    @FXML
    private void onAddClick() {
        int value = Integer.parseInt(add_field.getText());
        StackPane node = createNode(value);
        if (isFirst) {
            node.setLayoutX(startX);
            node.setLayoutY(startY);
            tree.addNode(node);
            Tree_panel.getChildren().add(node);
            isFirst = false;
            return;
        }
        tree.addNode(node);
        Tree.SearchResult result = tree.search(node);
        if (result == null || result.parent == null) return;
        StackPane parent = result.parent;
        Boolean isLeft = result.isLeft;

        double parentX = parent.getLayoutX();
        double parentY = parent.getLayoutY();
        double verticalOffset = 100;
        double horizontalOffset = 100;

        if (isLeft) {
            node.setLayoutX(parentX - horizontalOffset);
            node.setLayoutY(parentY + verticalOffset);
        } else {
            node.setLayoutX(parentX + horizontalOffset);
            node.setLayoutY(parentY + verticalOffset);
        }

        // Draw the new arrow
        TreeNodeArrow arrow = new TreeNodeArrow(parent, node, isLeft, NODE_RADIUS);
        Tree_panel.getChildren().add(arrow);

        Tree_panel.getChildren().add(node);
    }

    private StackPane createNode(int value) {
        Circle circle = new Circle(NODE_RADIUS);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text label = new Text(String.valueOf(value));
        label.setFont(Font.font("Sans", 14));
        return new StackPane(circle, label);
    }

    @FXML
    private void onDeleteClick() {
    }

    @FXML
    private void onResetClick() {
        Tree_panel.getChildren().clear();
        isFirst = true;
        tree = new Tree();
    }

    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/dsa_visualiser/data_structure_choose_screen.fxml");
    }
}
