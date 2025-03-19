package com.visualiser.miscellaneous;

public class DataTypeChecker {
    public boolean isInteger(String input) {
        for (char c : input.toCharArray()) {
            if (c < '0' || c > '9') return false;
        }

        return true;
    }

    public boolean isCharacter(String input) {
        return input.length() == 1;
    }
}
