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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTreeController {
    private final int NODE_RADIUS = 35;
    private Stage stage;
    private final double startY = 50;
    BinaryTree tree = new BinaryTree();
    ArrayList<Integer> treeNodes = new ArrayList<>();
    @FXML
    Pane Tree_panel;
    @FXML
    AnchorPane Tree_screen;
    @FXML
    TextField delete_field, add_field;

    @FXML
    private void initialize() {
        Tree_panel.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> obs, Number oldVal, Number newVal) {
                updatePositions();
            }
        });
    }

    @FXML
    private void onAddClick() {

        Integer value = null;

        try {
            value = Integer.parseInt(add_field.getText());
        }
        catch (Exception e) {
            ErrorMessage.showErrorMessage(Tree_screen, stage, "Invalid Argument Type", "Node argument can only be an integer");
            return;
        }

        if (treeNodes.contains(value)) {
            ErrorMessage.showErrorMessage(Tree_screen, stage, "Duplicate Elements", "Binary Search Tree can't contain duplicate elements");
            return;
        }

        treeNodes.add(value);
        StackPane node = createNode(value);
        tree.addSearchNode(node, value);


        if (tree.getDepth(value) > 4) {
            ErrorMessage.showErrorMessage(Tree_screen, stage, "Max Depth Reached", "Tree can't exceed a depth of 5");
            treeNodes.remove(value);

            tree = new BinaryTree();
            Tree_panel.getChildren().clear();

            for (Integer val : treeNodes) {
                StackPane newNode = createNode(val);
                tree.addSearchNode(newNode, val);
                Tree_panel.getChildren().add(newNode);
            }

            updatePositions();

            return;
        }

        Tree_panel.getChildren().add(node);

        updatePositions();
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
        Integer val;
        try {
            val = Integer.parseInt(delete_field.getText());
        } catch (Exception e) {
            ErrorMessage.showErrorMessage(Tree_screen, stage, "Invalid Argument Type", "Argument needs to be an integer");
            return;
        }
        if (!treeNodes.contains(val)) {
            ErrorMessage.showErrorMessage(Tree_screen, stage, "Can't Delete Argument", "Argument doesn't exist in tree");
            return;
        }
        treeNodes.remove(val);
        tree = new BinaryTree();
        Tree_panel.getChildren().clear();
        for (Integer value : treeNodes) {
            StackPane node = createNode(value);
            tree.addSearchNode(node, value);
            Tree_panel.getChildren().add(node);
        }
        updatePositions();
    }

    @FXML
    private void onResetClick() {
        Tree_panel.getChildren().clear();
        tree = new BinaryTree();
        treeNodes.clear();
    }

    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.backDataStructure(e, stage);
    }

    private void updatePositions() {
        double availableWidth = Tree_panel.getWidth();
        if (availableWidth <= 0) {
            availableWidth = 1024;
        }

        double verticalGap = 100;
        Queue<BinaryTree.Node> queue = new LinkedList<>();

        if (tree.getRootNode() == null) return;

        queue.add(tree.getRootNode());
        int level = 0;

        while (!queue.isEmpty()) {
            int count = queue.size();

            ArrayList<BinaryTree.Node> levelNodes = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                BinaryTree.Node current = queue.poll();
                levelNodes.add(current);
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }

            double spacing = availableWidth / (count + 1);

            for (int i = 0; i < levelNodes.size(); i++) {
                BinaryTree.Node current = levelNodes.get(i);
                double x = spacing * (i + 1);
                double y = startY + level * verticalGap;
                current.data.setLayoutX(x);
                current.data.setLayoutY(y);
            }
            level++;
        }
        Tree_panel.getChildren().removeIf(child -> child instanceof TreeNodeArrow);
        drawArrows(tree.getRootNode());
    }

    private void drawArrows(BinaryTree.Node node) {
        if (node == null) return;
        if (node.left != null) {
            TreeNodeArrow arrowLeft = new TreeNodeArrow(node.data, node.left.data, true, NODE_RADIUS);
            Tree_panel.getChildren().add(arrowLeft);
            drawArrows(node.left);
        }
        if (node.right != null) {
            TreeNodeArrow arrowRight = new TreeNodeArrow(node.data, node.right.data, false, NODE_RADIUS);
            Tree_panel.getChildren().add(arrowRight);
            drawArrows(node.right);
        }
    }
}
