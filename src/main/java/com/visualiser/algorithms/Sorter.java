package com.visualiser.algorithms;

public abstract class Sorter extends Thread {

    protected int[] elements = null;

    protected Sorter(int[] elements) {

    }

    protected Sorter() {}

    public int[] getElements() {
        return elements.clone();
    }
}
