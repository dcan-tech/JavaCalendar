package com.dylancanfield.calendarapp.view;

public class CalendarRenderer {

    /**
     * Renders a calendar for the month.
     * @param firstOfMonthIndex the starting index of the first day of the month (0-based)
     * @param dayCount the total number of days in the month
     * @param month the month number (1-12)
     * @param currentDay the day to highlight (e.g., today's day or a chosen day)
     */
    public static void renderMonth(int firstOfMonthIndex, int dayCount, int month, int currentDay) {
        // Print header
        System.out.println("Sun         Mon         Tues        Wed         Thu         Fri         Sat");
        lined();

        // Draw the first row
        int dayCounter = firstRow(firstOfMonthIndex, currentDay, dayCount);
        lined();

        // Draw subsequent rows until all days are printed
        while (dayCounter <= dayCount) {
            dayCounter = drawDateRow(dayCounter, dayCount, currentDay);
            lined();
        }
    }

    // Prints a horizontal separator line
    private static void lined() {
        for (int i = 0; i < 77; i++) {
            System.out.print('─');
        }
        System.out.println();
    }

    // Helper method to draw the first week
    private static int firstRow(int startIndex, int currentDay, int dayCount) {
        int dayCounter = 1;
        for (int i = 0; i < 7; i++) {
            if (i >= startIndex && dayCounter <= dayCount) {
                printDayCell(dayCounter, currentDay);
                dayCounter++;
            } else {
                System.out.print("|          "); // empty cell
            }
        }
        System.out.println("|");
        return dayCounter;
    }

    // Helper method to draw the rest of the date rows
    private static int drawDateRow(int dayCounter, int dayCount, int currentDay) {
        for (int i = 0; i < 7; i++) {
            if (dayCounter <= dayCount) {
                printDayCell(dayCounter, currentDay);
                dayCounter++;
            } else {
                System.out.print("|          ");
            }
        }
        System.out.println("|");
        return dayCounter;
    }

    // Utility to print a properly formatted cell
    private static void printDayCell(int day, int currentDay) {
        String content = (day == currentDay) ? String.format("%2d*", day) : String.format("%2d ", day);
        while (content.length() < 10) {
            content += " ";
        }
        System.out.print("|" + content);
    }

    // Main for test/demo
    public static void main(String[] args) {
        renderMonth(3, 31, 3, 15); // Example: March with current day 15
    }
}
