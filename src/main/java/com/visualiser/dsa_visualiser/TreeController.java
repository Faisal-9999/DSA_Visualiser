package com.visualiser.dsa_visualiser;

import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

//TODO: ADD GUI ELEMENTS TO tree.fxml and connect with this backend class also add the BFS search below
//TODO: FIND A WAY TO DISPLAY THE NODES PROPERLY WITH NO COLLISIONS

class Tree<T> {
    private class Node<T> {
        Node<T> left;
        Node<T> right;

        T data;

        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node<T> root = null;
    private int depth = 0;

    public int getDepth() {
        return depth;
    }

    public void addNode(T data) {
        if (root == null) {
            root = new Node<>(data);
            return;
        }

        //TODO: ADD BREADTH FIRST SEARCH LOGIC TO ADD NODE BREADTH WISE
    }

    public void deleteNode(int data) {

    }
}

public class TreeController {

    private int NODE_RADIUS = 50;
    private int currentNodes = 0;

    private Stage stage;

    Tree<StackPane> tree = new Tree<>();

    @FXML
    Pane Tree_panel;

    @FXML
    AnchorPane Tree_screen;

    @FXML
    private void onAddClick() {

    }

    @FXML
    private void onDeleteClick() {

    }

    @FXML
    private void onResetClick() {

    }

    @FXML
    private void onBackClick(ActionEvent e) throws IOException {
        SceneSwitcher.switch_scene(e, stage, "/com/visualiser/dsa_visualiser/data_structure_choose_screen.fxml");
    }
}
