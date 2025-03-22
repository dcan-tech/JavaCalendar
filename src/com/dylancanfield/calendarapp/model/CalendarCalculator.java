package com.dylancanfield.calendarapp.model;

import com.dylancanfield.calendarapp.util.Pair;

public class CalendarCalculator {

    /**
     * Calculates the day of the week using Zeller’s Congruence.
     * @param day the day of the month
     * @param month the month number (1–12)
     * @param year the full year (e.g., 2025)
     * @return Pair containing (index of day [0–6], name of day)
     */
    public static Pair<Integer, String> calculateDate(int day, int month, int year) {
        String[] dayNames = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        int[] monthOffsets = { 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5 };  // Jan–Dec

        // Adjust for leap year (only affects dates in March or later)
        if (isLeapYear(year)) {
            monthOffsets[1] = 2;  // February has offset 2 in leap years
        }

        int yearPart = year % 100;
        int centuryPart = year / 100;

        // Zeller-like calculation (simplified)
        int total = yearPart + (yearPart / 4) + day + monthOffsets[month - 1] + (6 - 2 * (centuryPart % 4));
        int dayIndex = total % 7;
        String dayName = dayNames[dayIndex];

        return new Pair<>(dayIndex, dayName);
    }

    /**
     * Returns number of days and name of the given month.
     * @param month the month number (1–12)
     * @param year the full year (for leap year evaluation)
     * @return Pair containing (days in month, month name)
     */
    public static Pair<Integer, String> monthInfo(int month, int year) {
        int dayCount;
        String monthName;

        switch (month) {
            case 1:  monthName = "January";   dayCount = 31; break;
            case 2:  monthName = "February";
                dayCount = (isLeapYear(year)) ? 29 : 28;
                break;
            case 3:  monthName = "March";     dayCount = 31; break;
            case 4:  monthName = "April";     dayCount = 30; break;
            case 5:  monthName = "May";       dayCount = 31; break;
            case 6:  monthName = "June";      dayCount = 30; break;
            case 7:  monthName = "July";      dayCount = 31; break;
            case 8:  monthName = "August";    dayCount = 31; break;
            case 9:  monthName = "September"; dayCount = 30; break;
            case 10: monthName = "October";   dayCount = 31; break;
            case 11: monthName = "November";  dayCount = 30; break;
            case 12: monthName = "December";  dayCount = 31; break;
            default: monthName = "Invalid";   dayCount = 0;  break;
        }

        return new Pair<>(dayCount, monthName);
    }


    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

}
