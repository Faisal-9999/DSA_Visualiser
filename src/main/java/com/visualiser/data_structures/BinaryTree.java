package com.visualiser.data_structures;

import javafx.scene.layout.StackPane;
import java.util.LinkedList;
import java.util.Queue;

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
    private Boolean isBalanced = false;
    private int depth = 0;

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

    public int checkDepth(Node node) {
        if (node == null) return 0;

        int left = checkDepth(node.left);
        int right = checkDepth(node.right);

        return Math.max(left, right) + 1;
    }

    public void checkBalanced() {
        if (root == null) {
            isBalanced = true;
            return;
        }

        Queue<Node> q = new LinkedList<>();

        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                if (node.left != null && node.right == null) {
                    isBalanced = false;
                    return;
                }
                else if (node.left == null && node.right != null){
                    isBalanced = false;
                    return;
                }
                else {
                    if (node.left != null) q.add(node.left);
                    if (node.right != null) q.add(node.right);
                }
            }
        }

        isBalanced = true;
    }

    public Integer getDepth() {
        return depth;
    }

    public Boolean getIsBalanced() {
        return isBalanced;
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