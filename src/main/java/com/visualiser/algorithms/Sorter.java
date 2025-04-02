package com.visualiser.algorithms;

import java.util.ArrayList;

public abstract class Sorter extends Thread {

    protected ArrayList<Integer> elements = null;

    protected Sorter(ArrayList<Integer> elements) {
        this.elements = elements;
    }

    protected Sorter() {}

    public ArrayList<Integer> getElements() {
        return elements;
    }
}
