package com.dylancanfield.calendarapp.controller;

import com.dylancanfield.calendarapp.model.CalendarCalculator;
import com.dylancanfield.calendarapp.view.CalendarRenderer;
import com.dylancanfield.calendarapp.util.Pair;
import java.util.Scanner;

public class CalendarCLIController {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        // Get the user's command
        String command = menuChoice(scanner);

        if (command.equals("e")) {
            // ... existing "e" option code ...
            System.out.print("\nEnter a date (mm/dd): ");
            String input = scanner.nextLine();
            String[] parts = input.split("/");

            if (parts.length < 2) {
                System.out.println("Invalid format. Please use mm/dd.");
                // In a loop you might prompt again here
                // For now, we exit this branch
                return;
            }

            int month = Integer.parseInt(parts[0].trim());
            int day = Integer.parseInt(parts[1].trim());

            Pair<Integer, String> firstDayPair = CalendarCalculator.calculateDate(1, month);
            Pair<Integer, String> monthInfo = CalendarCalculator.monthInfo(1, month);
            Pair<Integer, String> dayPair = CalendarCalculator.calculateDate(day, month);

            System.out.printf("\nDisplaying calendar for %s %d (Current day: %s)\n",
                    monthInfo.getValue(), day, dayPair.getValue());
            CalendarRenderer.renderMonth(firstDayPair.getKey(), monthInfo.getKey(), month, day);
        }
        else if (command.equals("t")) { // New option for today's date
            // Get today's date using java.time.LocalDate
            java.time.LocalDate today = java.time.LocalDate.now();
            int monthVal = today.getMonthValue();
            int dayVal = today.getDayOfMonth();

            // Use the model to compute the starting day for the month and month info
            Pair<Integer, String> firstDayPair = CalendarCalculator.calculateDate(1, monthVal);
            Pair<Integer, String> monthInfo = CalendarCalculator.monthInfo(1, monthVal);
            Pair<Integer, String> dayPair = CalendarCalculator.calculateDate(dayVal, monthVal);

            System.out.printf("\nDisplaying calendar for %s %d (Today: %s)\n",
                    monthInfo.getValue(), dayVal, dayPair.getValue());
            CalendarRenderer.renderMonth(firstDayPair.getKey(), monthInfo.getKey(), monthVal, dayVal);
        }
        // (You would add additional else-if blocks for "n", "p", "fp", etc.)
        else if (command.equals("q")) {
            System.out.println("Exiting...");
        }
        else {
            System.out.println("Invalid command.");
        }

        scanner.close();
    }


    private String menuChoice(Scanner scanner) {
        System.out.println("\nPlease type a command:");
        System.out.println(" \"e\" to enter a date and display the corresponding calendar");
        System.out.println(" \"t\" to get today's date and display the calendar");
        System.out.println(" \"n\" to display the next month");
        System.out.println(" \"p\" to display the previous month");
        System.out.println(" \"fp\" to print calendar to file");
        System.out.println(" \"q\" to quit the program");
        System.out.println();
        return scanner.nextLine();
    }

    private void newline() {
        System.out.println();
    }
}
