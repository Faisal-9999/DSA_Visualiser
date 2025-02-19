package com.visualiser.dsa_visualiser;

class List {
    private class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node head;

    public List() {

    }
}
