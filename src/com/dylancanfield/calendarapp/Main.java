package com.dylancanfield.calendarapp;

import com.dylancanfield.calendarapp.controller.CalendarCLIController;

public class Main {
    public static void main(String[] args) {
        CalendarCLIController controller = new CalendarCLIController();
        controller.run();
    }
}
