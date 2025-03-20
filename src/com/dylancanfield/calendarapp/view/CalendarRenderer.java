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


    // Helper method to print a horizontal line
    private static void lined() {
        for (int i = 0; i < 78; i++) {
            System.out.print('─'); // Unicode HORIZONTAL LINE character
        }
        System.out.println();
    }

    // Helper method to draw the first row of the calendar
    private static int firstRow(int startIndex, int currentDay, int dayCount) {
        int dayCounter = 1;
        for (int i = 0; i < 7; i++) {
            System.out.print("|");
            if (i >= startIndex && dayCounter <= dayCount) {
                System.out.printf("%2d", dayCounter);
                if (dayCounter == currentDay) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
                dayCounter++;
            } else {
                System.out.print("    ");
            }
        }
        System.out.println("|");
        return dayCounter;
    }


    // Draws a row of dates and returns the updated dayCounter.
    private static int drawDateRow(int dayCounter, int dayCount, int currentDay) {
        for (int i = 0; i < 7; i++) {
            System.out.print("|");
            if (dayCounter <= dayCount) {
                // Print the day number in a fixed width (e.g., 2 digits)
                System.out.printf("%2d", dayCounter);
                // If this is the current day, print a star; else a space.
                if (dayCounter == currentDay) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
                dayCounter++;
            } else {
                // If there are no more days, print empty space
                System.out.print("    ");
            }
        }
        System.out.println("|");
        return dayCounter;
    }


    public static void main(String[] args) {
        // Test with: firstOfMonthIndex = 3, dayCount = 31, month = 3, currentDay = 15
        renderMonth(3, 31, 3, 15);
    }

}
