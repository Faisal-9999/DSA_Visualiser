package com.visualiser.dsa_visualiser;

import com.visualiser.miscellaneous.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

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

    public void incrementDepth() {
        depth++;
    }

    public void addNode(T data) {
        if (root == null) {
            root = new Node<>(data);
            return;
        }

        LinkedList<Node> q = new LinkedList<>();

        q.add(root);

        while (!q.isEmpty()) {

            Node<T> current =  q.poll();

            if (current.left == null) {
                current.left = new Node<>(data);
                break;
            } else if (current.right == null) {
                current.right = new Node<>(data);
                break;
            }
            else {
                q.add(current.left);
                q.add(current.right);
            }
        }
    }

    public void level_traversal() {
        if (root == null) {
            return;
        }

        ArrayList<ArrayList<T>> nodes = new ArrayList<>();

        LinkedList<Node<T>> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {

            ArrayList<T> list = new ArrayList<>();

            int length = q.size();

            for (int i = 0; i < length; i++) {
                if (q.peekFirst().left != null) q.add(q.peekFirst().left);
                if (q.peekFirst().right != null) q.add(q.peekFirst().right);
                list.add(q.poll().data);
            }

            nodes.add(list);
        }
    }

    public void deleteNode(int data) {

    }

}

public class TreeController {

    private final int NODE_RADIUS = 50;

    private Stage stage;

    Tree<StackPane> tree = new Tree<>();

    @FXML
    Pane Tree_panel;

    @FXML
    AnchorPane Tree_screen;

    @FXML
    TextField delete_field, add_field;

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
