package com.visualiser.dsa_visualiser;

import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.LinkedList;

public class Tree {
    private class Node {
        Node left;
        Node right;

        StackPane data;

        Node(StackPane data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root = null;
    private int depth = 0;

    public int getDepth() {
        return depth;
    }

    public void incrementDepth() {
        depth++;
    }

    public void addNode(StackPane data) {
        if (root == null) {
            root = new Node(data);
            return;
        }

        LinkedList<Node> q = new LinkedList<>();

        q.add(root);

        while (!q.isEmpty()) {

            Node current =  q.poll();

            if (current.left == null) {
                current.left = new Node(data);
                break;
            } else if (current.right == null) {
                current.right = new Node(data);
                break;
            }
            else {
                q.add(current.left);
                q.add(current.right);
            }
        }
    }

    public void deleteNode(StackPane data) {

    }

}