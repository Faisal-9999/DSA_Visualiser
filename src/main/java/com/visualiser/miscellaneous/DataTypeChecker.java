package com.visualiser.miscellaneous;

public class DataTypeChecker {
    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isCharacter(String input) {
        return input.length() == 1;
    }
}
