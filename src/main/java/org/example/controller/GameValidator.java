package org.example.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class GameValidator {

    public static double parsePositiveDouble(String input) throws IllegalArgumentException {
        double value = Double.parseDouble(input);
        if (value < 0 || value > 10) throw new IllegalArgumentException("Value must be between 0 and 10.");
        return value;
    }

    public static LocalDate parseDate(String input) throws DateTimeParseException {
        return LocalDate.parse(input);
    }

    public static String parseNonEmpty(String input) throws IllegalArgumentException {
        if (input == null || input.isBlank()) throw new IllegalArgumentException("Field cannot be empty.");
        return input;
    }
}


