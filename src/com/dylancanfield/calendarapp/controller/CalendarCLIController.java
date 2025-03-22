package com.dylancanfield.calendarapp.controller;

import com.dylancanfield.calendarapp.model.CalendarCalculator;
import com.dylancanfield.calendarapp.util.Pair;
import com.dylancanfield.calendarapp.view.CalendarRenderer;

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
                    System.out.print("\nEnter a date (mm/dd/yyyy): ");
                    String input = scanner.nextLine();
                    String[] parts = input.split("/");

                    if (parts.length < 3) {
                        System.out.println("Invalid format. Please use mm/dd/yyyy.");
                        break;
                    }

                    int enteredMonth = Integer.parseInt(parts[0].trim());
                    int enteredDay = Integer.parseInt(parts[1].trim());
                    int enteredYear = Integer.parseInt(parts[2].trim());

                    Pair<Integer, String> enteredFirstDay = CalendarCalculator.calculateDate(1, enteredMonth, enteredYear);
                    Pair<Integer, String> enteredMonthInfo = CalendarCalculator.monthInfo(enteredMonth, enteredYear);
                    Pair<Integer, String> enteredDayOfWeek = CalendarCalculator.calculateDate(enteredDay, enteredMonth, enteredYear);

                    System.out.printf("\nDisplaying calendar for %s %d, %d (Selected day: %s)\n",
                            enteredMonthInfo.getValue(), enteredDay, enteredYear, enteredDayOfWeek.getValue());

                    CalendarRenderer.renderMonth(
                            enteredFirstDay.getKey(), enteredMonthInfo.getKey(), enteredMonth, enteredDay);
                    break;

                case "t":
                    LocalDate today = LocalDate.now();
                    int currentMonth = today.getMonthValue();
                    int currentDay = today.getDayOfMonth();
                    int currentYear = today.getYear();

                    Pair<Integer, String> todayFirstDay = CalendarCalculator.calculateDate(1, currentMonth, currentYear);
                    Pair<Integer, String> todayMonthInfo = CalendarCalculator.monthInfo(currentMonth, currentYear);
                    Pair<Integer, String> todayDayOfWeek = CalendarCalculator.calculateDate(currentDay, currentMonth, currentYear);

                    System.out.printf("\nDisplaying calendar for %s %d, %d (Today: %s)\n",
                            todayMonthInfo.getValue(), currentDay, currentYear, todayDayOfWeek.getValue());

                    CalendarRenderer.renderMonth(
                            todayFirstDay.getKey(), todayMonthInfo.getKey(), currentMonth, currentDay);
                    break;

                case "fp":
                    System.out.print("\nEnter month to print (1-12): ");
                    int fileMonth = Integer.parseInt(scanner.nextLine().trim());

                    System.out.print("Enter year: ");
                    int fileYear = Integer.parseInt(scanner.nextLine().trim());

                    Pair<Integer, String> fileFirstDay = CalendarCalculator.calculateDate(1, fileMonth, fileYear);
                    Pair<Integer, String> fileMonthInfo = CalendarCalculator.monthInfo(fileMonth, fileYear);

                    System.out.print("Enter output filename: ");
                    String filename = scanner.nextLine().trim();

                    try (PrintStream out = new PrintStream(filename)) {
                        CalendarRenderer.renderMonthTo(
                                out, fileFirstDay.getKey(), fileMonthInfo.getKey(), fileMonth, 0);
                        System.out.println("Calendar written to " + filename);
                    } catch (java.io.FileNotFoundException e) {
                        System.out.println("Error writing to file: " + e.getMessage());
                    }
                    break;

                case "q":
                    System.out.println("Exiting program. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Unrecognized command. Please type one of the options listed.");
                    break;
            }
        }

        scanner.close();
    }

    private String menuChoice(Scanner scanner) {
        System.out.println("\n*** Java Calendar App ***");
        System.out.println("Choose a command:");
        System.out.println(" \"e\"  - Enter a specific date and view the calendar");
        System.out.println(" \"t\"  - View calendar for today's date");
        System.out.println(" \"n\"  - View next month (coming soon)");
        System.out.println(" \"p\"  - View previous month (coming soon)");
        System.out.println(" \"fp\" - Print calendar to file");
        System.out.println(" \"q\"  - Quit the program");
        System.out.print("Enter command: ");
        return scanner.nextLine().trim().toLowerCase();
    }
}
