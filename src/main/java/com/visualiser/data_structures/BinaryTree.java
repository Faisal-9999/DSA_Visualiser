package com.visualiser.data_structures;

import javafx.scene.layout.StackPane;
import java.util.LinkedList;

public class BinaryTree {

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

    public void addNode(StackPane data) {
        if (root == null) {
            root = new Node(data);
            return;
        }

        LinkedList<Node> q = new LinkedList<>();

        q.add(root);

        while (!q.isEmpty()) {
            Node current = q.poll();
            if (current.left == null) {
                current.left = new Node(data);
                break;
            } else if (current.right == null) {
                current.right = new Node(data);
                break;
            } else {
                q.add(current.left);
                q.add(current.right);
            }
        }
    }

    public Integer getDepth() {
        //TODO: ADD WORKING TO CHECK DEPTH OF TREE AFTER LEARNING IT
        return null;
    }

    public Boolean getIsBalanced() {
        //TODO: ADD WORKING TO CHECK IF TREE IS BALANCED ACROSS ALL PATHS
        return null;
    }

    public class SearchResult {
        public StackPane parent;
        public Boolean isLeft;
        public SearchResult(StackPane parent, Boolean isLeft) {
            this.parent = parent;
            this.isLeft = isLeft;
        }
    }

    public SearchResult search(StackPane node) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node current = q.poll();
            if(current.left != null) {
                if(current.left.data == node) {
                    return new SearchResult(current.data, true);
                }
                q.add(current.left);
            }
            if(current.right != null) {
                if(current.right.data == node) {
                    return new SearchResult(current.data, false);
                }
                q.add(current.right);
            }
        }
        return null;
    }
}