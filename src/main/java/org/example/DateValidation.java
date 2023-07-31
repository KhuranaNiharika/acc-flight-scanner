package org.example;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import io.ous.jtoml.ParseException;

public class DateValidation {

    // Date validation using SimpleDateFormat class
    public static boolean isDateValid(String input) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(input.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    // Method to convert date from "yyyyMMdd" to "yyyy-MM-dd" format using
    // DateTimeFormatter and LocalDate class
    public static String dateFormatterf(String originalDateString) {

        DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate parsedDate = LocalDate.parse(originalDateString, originalFormatter);

        DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateString = parsedDate.format(targetFormatter);

        return formattedDateString;
    }

}
