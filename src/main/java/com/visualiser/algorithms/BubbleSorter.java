package com.visualiser.algorithms;

import java.util.ArrayList;

public class BubbleSorter extends Sorter {

    //TODO: TEST POSITIONING AND MAKE SHIT APPEAR ON SCREEN NOW

    boolean isSorted = true;
    int counter = 0;

    private BubbleSorter(ArrayList<Integer> elements) {
        super(elements);
    }

    private BubbleSorter() {
        super();
    }

    public static BubbleSorter bubbleSorterBuilder(ArrayList<Integer> arr) throws RuntimeException {
        if (arr.size() == 0) {
            throw new RuntimeException("Entered Empty Array");
        }

        return new BubbleSorter(arr);
    }

    @Override
    public void run() {
        for (int i = 0; i < elements.size() - 1; i++) {
            for (int j = 0; j < elements.size() - 1 - i; j++) {
                if (elements.indexOf(j + 1) < elements.indexOf(j)) {
                    int temp = elements.indexOf(j);
                    elements.set(j, elements.indexOf(j + 1));
                    elements.set(j + 1, temp);
                }
                try {
                    Thread.sleep(500);
                }
                catch (Exception e) {
                    throw new RuntimeException("Error putting thread to sleep");
                }
            }
        }
    }
}
