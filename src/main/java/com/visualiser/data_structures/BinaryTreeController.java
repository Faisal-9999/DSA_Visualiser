package com.visualiser.data_structures;

import com.visualiser.miscellaneous.ErrorMessage;
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
import java.util.ArrayList;

public class BinaryTreeController {
    private final int NODE_RADIUS = 35;
    private Stage stage;
    private Boolean isFirst = true;
    private final double startX = 625;
    private final double startY = 50;

    BinaryTree tree = new BinaryTree();

    ArrayList<Integer> treeNodes = new ArrayList<>();

    @FXML
    Pane Tree_panel;

    @FXML
    AnchorPane Tree_screen;

    @FXML
    TextField delete_field, add_field;

    //TODO: ADD A FUNCTION FOR PRINTING THE WHOLE TREE AGAIN AFTER REMOVING ONE NODE
    //TODO:

    @FXML
    private void onAddClick() {
        try {
            int value = Integer.parseInt(add_field.getText());
            StackPane node = createNode(value);

            treeNodes.add(value);

            if (isFirst) {
                node.setLayoutX(startX);
                node.setLayoutY(startY);
                tree.addNode(node);
                Tree_panel.getChildren().add(node);
                isFirst = false;
                return;
            }
            tree.addNode(node);
            BinaryTree.SearchResult result = tree.search(node);
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

            TreeNodeArrow arrow = new TreeNodeArrow(parent, node, isLeft, NODE_RADIUS);
            Tree_panel.getChildren().add(arrow);

            Tree_panel.getChildren().add(node);
        }
        catch (Exception e) {
            ErrorMessage.showErrorMessage(Tree_screen, stage, "Invalid Argument Type", "Node argument can only be an integer");
        }
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
        Integer val = null;

        try {
            val = Integer.parseInt(delete_field.getText());
        }
        catch (Exception e) {
            ErrorMessage.showErrorMessage(Tree_screen, stage, "Invalid Argument Type", "Argument needs to be an integer");
            return;
        }

        if (!treeNodes.contains(val)) {
            ErrorMessage.showErrorMessage(Tree_screen, stage, "Can't Delete Argument", "Argument doesn't exist in tree");
            return;
        }

        treeNodes.remove(val);

        tree = new BinaryTree();
        isFirst = true;
        Tree_panel.getChildren().clear();

        for (Integer value : treeNodes) {

            StackPane node = createNode(value);

            if (isFirst) {
                node.setLayoutX(startX);
                node.setLayoutY(startY);
                tree.addNode(node);
                Tree_panel.getChildren().add(node);
                isFirst = false;
                continue;
            }

            tree.addNode(node);
            BinaryTree.SearchResult result = tree.search(node);
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

            TreeNodeArrow arrow = new TreeNodeArrow(parent, node, isLeft, NODE_RADIUS);
            Tree_panel.getChildren().add(arrow);

            Tree_panel.getChildren().add(node);
        }

    }

    @FXML
    private void onResetClick() {
        Tree_panel.getChildren().clear();
        isFirst = true;
        tree = new BinaryTree();
        treeNodes.clear();
    }

    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.backDataStructure(e, stage);
    }
}
