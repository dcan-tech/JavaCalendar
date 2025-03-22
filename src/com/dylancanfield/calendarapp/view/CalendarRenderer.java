package com.dylancanfield.calendarapp.view;

import java.io.PrintStream;

public class CalendarRenderer {

    // General-purpose rendering method (console or file)
    public static void renderMonthTo(PrintStream out, int firstOfMonthIndex, int dayCount, int month, int currentDay) {
        out.println("Sun         Mon        Tues       Wed        Thu        Fri        Sat");
        lined(out);

        int dayCounter = firstRow(out, firstOfMonthIndex, currentDay, dayCount);
        drawRow(out);

        while (dayCounter <= dayCount) {
            dayCounter = dateRow(out, dayCounter, dayCount, currentDay);
            drawRow(out);
        }
    }

    // Shortcut for console output
    public static void renderMonth(int firstOfMonthIndex, int dayCount, int month, int currentDay) {
        renderMonthTo(System.out, firstOfMonthIndex, dayCount, month, currentDay);
    }

    private static void lined(PrintStream out) {
        for (int i = 0; i <= 77; i++) {
            out.print('─');
        }
        out.println();
    }

    private static int firstRow(PrintStream out, int startIndex, int currentDay, int dayCount) {
        int dayCounter = 1;
        for (int i = 0; i < 7; i++) {
            out.print("|");

            if (i >= startIndex && dayCounter <= dayCount) {
                out.printf("%d", dayCounter);
                if (dayCounter == currentDay) {
                    out.print("*");
                    printDoubleDigitCell(out);
                } else {
                    printSingleDigitCell(out);
                }
                dayCounter++;
            } else {
                printEmptyCell(out);
            }

            if (i == 6) out.print("|");
        }
        out.println();
        return dayCounter;
    }

    private static void drawRow(PrintStream out) {
        for (int box = 0; box < 4; box++) {
            for (int col = 0; col < 7; col++) {
                out.print("|");
                printEmptyCell(out);
                if (col == 6) out.print("|");
            }
            out.println();
        }
        lined(out);
    }

    private static int dateRow(PrintStream out, int dayCounter, int dayCount, int currentDay) {
        for (int i = 0; i < 7; i++) {
            out.print("|");

            if (dayCounter <= dayCount) {
                out.printf("%d", dayCounter);
                if (dayCounter == currentDay) {
                    out.print("*");
                    if (dayCounter >= 10) printHighlightedDoubleDigitCell(out);
                    else printDoubleDigitCell(out);
                } else {
                    if (dayCounter >= 10) printDoubleDigitCell(out);
                    else printSingleDigitCell(out);
                }
                dayCounter++;
            } else {
                printEmptyCell(out);
            }

            if (i == 6) out.print("|");
        }
        out.println();
        return dayCounter;
    }

    private static void printEmptyCell(PrintStream out) {
        out.print("          ");
    }

    private static void printSingleDigitCell(PrintStream out) {
        out.print("         ");
    }

    private static void printDoubleDigitCell(PrintStream out) {
        out.print("        ");
    }

    private static void printHighlightedDoubleDigitCell(PrintStream out) {
        out.print("       ");
    }

    public static void main(String[] args) {
        renderMonth(3, 31, 3, 15);
    }
}
