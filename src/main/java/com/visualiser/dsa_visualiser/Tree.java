package com.visualiser.dsa_visualiser;

import javafx.scene.Parent;
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

    public Boolean search(StackPane node, StackPane parentNode) {
        Boolean isLeft = null;

        LinkedList<Node> q = new LinkedList<>();

        q.add(root);

        while (!q.isEmpty()) {

            Node current =  q.poll();

            if (current.left.data == node) {
                parentNode = current.data;
                isLeft = true;
                break;
            } else if (current.right.data == node) {
                parentNode = current.data;
                isLeft = false;
                break;
            }
            else {
                q.add(current.left);
                q.add(current.right);
            }
        }

        return isLeft;
    }

    public void deleteNode(StackPane data) {

    }

}