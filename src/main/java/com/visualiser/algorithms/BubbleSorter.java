package com.visualiser.algorithms;

public class BubbleSorter extends Sorter {

    //TODO: MAKE A COMPLETE ROADMAP ON HOW THE CODE SHOULD WORK BEFORE IMPLEMENTING ANUTHING THIS IS BECOMING CONFUSING
    //TODO: LIKE DO YOU STOP THE THREAD AFTER EACH ITERATION AND RETURN THE STATE OR WHAT

    boolean isSorted = true;
    int counter = 0;

    private BubbleSorter(int[] elements) {
        super(elements);
    }

    private void sort() {
        for (int i = 0; i < elements.length - 1; i++) {
            for (int j = 0; j < elements.length - 1 - i; j++) {
                if (elements[j] > elements[j + 1]) {
                    int temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
        }
    }

    private BubbleSorter() {
        super();
    }

    public static BubbleSorter bubbleSorterBuilder(int[] arr) throws RuntimeException {
        if (arr.length == 0) {
            throw new RuntimeException("Entered Empty Array");
        }

        return new BubbleSorter(arr);
    }

    @Override
    public void run() {
        for (int i = 0; i < elements.length - 1; i++) {
            if (elements[i + 1] < elements[i]) {
                isSorted = false;
                break;
            }
        }

        if (isSorted) {
            return;
        }

    }
}
