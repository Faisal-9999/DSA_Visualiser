package com.visualiser.data_structures;

import javafx.scene.layout.StackPane;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    public class Node {
        public Node left;
        public Node right;
        public StackPane data;
        public int value;

        public Node(StackPane data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }


        //THIS CONSTRUCTOR WILL BE USED FOR SEARCH TREE NODES
        public Node(StackPane data, int value) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.value = value;
        }
    }

    private Node root = null;

    //WILL BE USED TO CHECK DEPTH OF THE NODE FOR EVERY NODE ADDED IN THE BST
    public int getDepth(int value) {
        return calcDepth(root, value, 0);
    }

    private int calcDepth(Node node, int value, int currentDepth) {
        if (node == null) {
            return -1;
        }

        if (node.value == value) {
            return currentDepth;
        }
        else if (node.value < value) {
            return calcDepth(node.right, value, currentDepth + 1);
        }
        else {
            return calcDepth(node.left, value, currentDepth + 1);
        }
    }

    //WILL BE USED FOR ADDING NODES INTO THE BINARY SEARCH TREE
    public void addSearchNode(StackPane data, int value) {
        if (root == null) {
            root = new Node(data, value);
            return;
        }

        Node current = root;

        while (true) {
            if (current.value == value) return;

            if (current.value < value) {
                if (current.right == null) {
                    current.right = new Node(data, value);
                    return;
                }

                current = current.right;
            }
            else {
                if (current.left == null) {
                    current.left = new Node(data, value);
                    return;
                }

                current = current.left;
            }
        }
    }

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