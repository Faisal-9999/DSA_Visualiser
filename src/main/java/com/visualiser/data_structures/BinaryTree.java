package com.visualiser.data_structures;

import javafx.scene.layout.StackPane;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    public class Node {
        public Node left;
        public Node right;
        public StackPane data;

        public Node(StackPane data) {
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
        Queue<Node> q = new LinkedList<>();
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

    public Node getRootNode() {
        return root;
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
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node current = q.poll();
            if (current.left != null) {
                if (current.left.data == node) {
                    return new SearchResult(current.data, true);
                }
                q.add(current.left);
            }
            if (current.right != null) {
                if (current.right.data == node) {
                    return new SearchResult(current.data, false);
                }
                q.add(current.right);
            }
        }
        return null;
    }
}