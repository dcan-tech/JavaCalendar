package com.dylancanfield.calendarapp.controller;

import com.dylancanfield.calendarapp.model.CalendarCalculator;
import com.dylancanfield.calendarapp.util.Pair;
import com.dylancanfield.calendarapp.view.CalendarRenderer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

public class CalendarCLIController {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            String command = menuChoice(scanner);

            switch (command) {
                case "e":
                    System.out.print("\nEnter a date (mm/dd): ");
                    String input = scanner.nextLine();
                    String[] parts = input.split("/");

                    if (parts.length < 2) {
                        System.out.println("Invalid format. Please use mm/dd.");
                        break;
                    }

                    int month = Integer.parseInt(parts[0].trim());
                    int day = Integer.parseInt(parts[1].trim());

                    var firstDayPair = CalendarCalculator.calculateDate(1, month);
                    var monthInfo = CalendarCalculator.monthInfo(1, month);
                    var dayPair = CalendarCalculator.calculateDate(day, month);

                    System.out.printf("\nDisplaying calendar for %s %d (Current day: %s)\n",
                            monthInfo.getValue(), day, dayPair.getValue());
                    CalendarRenderer.renderMonth(firstDayPair.getKey(), monthInfo.getKey(), month, day);
                    break;

                case "t":
                    var today = java.time.LocalDate.now();
                    int monthVal = today.getMonthValue();
                    int dayVal = today.getDayOfMonth();

                    var firstOfMonth = CalendarCalculator.calculateDate(1, monthVal);
                    var thisMonthInfo = CalendarCalculator.monthInfo(1, monthVal);
                    var todayPair = CalendarCalculator.calculateDate(dayVal, monthVal);

                    System.out.printf("\nDisplaying calendar for %s %d (Today: %s)\n",
                            thisMonthInfo.getValue(), dayVal, todayPair.getValue());
                    CalendarRenderer.renderMonth(firstOfMonth.getKey(), thisMonthInfo.getKey(), monthVal, dayVal);
                    break;

                case "fp":
                    System.out.print("\nEnter month to print (1-12): ");
                    int fileMonth = Integer.parseInt(scanner.nextLine().trim());

                    var fileFirstDay = CalendarCalculator.calculateDate(1, fileMonth);
                    var fileMonthInfo = CalendarCalculator.monthInfo(1, fileMonth);

                    System.out.print("Enter output filename: ");
                    String filename = scanner.nextLine().trim();

                    try (PrintStream out = new PrintStream(filename)) {
                        CalendarRenderer.renderMonthTo(out, fileFirstDay.getKey(), fileMonthInfo.getKey(), fileMonth, 0);
                        System.out.println("Calendar written to " + filename);
                    } catch (java.io.FileNotFoundException e) {
                        System.out.println("Error writing to file: " + e.getMessage());
                    }
                    break;

                case "q":
                    System.out.println("Exiting...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid command.");
                    break;
            }
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
}
