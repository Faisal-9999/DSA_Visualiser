package com.visualiser.data_structures;

public class LinkedList {

    private class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node head;

    private int length = 0;

    public LinkedList() {
        head = null;
    }

    public void reverse() {
        Node prev = null;
        Node curr = head;
        Node NEXT = head.next;

        while (NEXT != null) {
            curr.next = prev;
            prev = curr;
            curr = NEXT;
            NEXT = NEXT.next;
        }

        curr.next = prev;
        head = curr;
    }

    public void insert(int value, int position) throws Exception {
        if (head == null) {
            throw new Exception("Can't Insert Node When Head Is Null");
        }

        if (position > length) {
            throw new Exception("Out Of Bounds Insert");
        }

        if (position == 1) {
            Node temp = new Node(value);
            temp.next = head;
            head = temp;
            return;
        }

        int curr = 1;

        Node current = head;

        while (curr < position) {
            current = current.next;
            curr++;
        }

        Node temp = new Node(value);
        temp.next = current.next;
        current.next = temp;

        length++;
    }

    public void addNode(int value) {
        if (head == null) {
            head = new Node(value);
            length++;
            return;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = new Node(value);
        length++;
    }

    public void delete(int position) throws Exception {
        if (head == null) {
            throw new Exception("Can't Delete When Head Is Null");
        }
        if (position > length) {
            throw new Exception("Out Of Bounds Deletion");
        }

        if (position == 1) {
            head = head.next;
            length--;
            return;
        }

        int curr = 1;
        Node current = head;

        while (curr < position - 1) {
            current = current.next;
            curr++;
        }

        current.next = current.next.next;
        length--;
    }
}
