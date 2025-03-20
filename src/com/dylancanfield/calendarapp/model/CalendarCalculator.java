package com.dylancanfield.calendarapp.model;

import com.dylancanfield.calendarapp.util.Pair;

public class CalendarCalculator {

    // Existing methods, e.g., calculateDate and monthInfo

    public static Pair<Integer, String> calculateDate(int dd, int mm) {
        String[] days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        int[] m_no = { 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5 };

        int total = (2022 % 100) + ((2022 % 100) / 4) + dd + m_no[mm - 1] + 6;
        String dow = days[total % 7];
        int nod = total % 7;
        return new Pair<>(nod, dow);
    }

    public static Pair<Integer, String> monthInfo(int dd, int mm) {
        int dayCount = 0;
        String monthString;
        switch (mm) {
            case 1:  monthString = "January";   dayCount = 31; break;
            case 2:  monthString = "February";  dayCount = 28; break;
            case 3:  monthString = "March";     dayCount = 31; break;
            case 4:  monthString = "April";     dayCount = 30; break;
            case 5:  monthString = "May";       dayCount = 31; break;
            case 6:  monthString = "June";      dayCount = 30; break;
            case 7:  monthString = "July";      dayCount = 31; break;
            case 8:  monthString = "August";    dayCount = 31; break;
            case 9:  monthString = "September"; dayCount = 30; break;
            case 10: monthString = "October";   dayCount = 31; break;
            case 11: monthString = "November";  dayCount = 30; break;
            case 12: monthString = "December";  dayCount = 31; break;
            default: monthString = "Invalid month"; break;
        }
        return new Pair<>(dayCount, monthString);
    }

    // Testing main method
    public static void main(String[] args) {
        // Test calculateDate
        Pair<Integer, String> result = calculateDate(15, 3);
        System.out.println("calculateDate(15, 3): " + result);

        // Test monthInfo
        Pair<Integer, String> monthResult = monthInfo(1, 3);
        System.out.println("monthInfo(1, 3): " + monthResult);
    }
} // End of CalendarCalculator class
