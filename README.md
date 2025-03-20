# Java Calendar App

## Refactoring a School Project into an Industry-Standard MVC Application

### Project Overview

This project began as a school assignment, originally written as a single monolithic Java script that handled user input, calendar calculations, and terminal rendering. The goal of this refactoring effort is to apply industry best practices, including:

-   **MVC Architecture:** Separating logic into Model, View, and Controller.
-   **Improved Code Readability:** Breaking down large methods into modular functions.
-   **Scalability:** Preparing the application for potential enhancements, such as year-based calculations and graphical interface integration.

### Technology Stack

-   Java 22

### Installation/Usage

1.  Clone the repository: `git clone [repository URL]`
2.  Navigate to the project directory: `cd [project directory]`
3.  Compile the application: `javac src/com/calendar/Main.java` or `mvn compile` or `gradle build`
4.  Run the application: `java com.calendar.Main [month] [day]` or `java -jar target/calendar.jar [month] [day]`

Example: `java com.calendar.Main 12 25`

### Current Features

-   Accepts user input to display a calendar for a given month and day.
-   Determines the correct starting position of the month.
-   Highlights the selected day within the calendar.
-   Supports displaying the current month dynamically based on the system date.

### Planned Reintegrations and Improvements

-   **Year Support:**
    -   Implement year input for accurate day-of-week calculations.
    -   Validate year input to ensure valid ranges.
-   **Month Navigation:**
    -   Add commands to navigate to the previous and next months.
    -   Implement month navigation with year adjustments.
-   **Enhanced Output Formatting:**
    -   Improve the visual presentation of the calendar in the terminal.
    -   Add options for different calendar styles.
-   **Graphical User Interface (GUI):**
    -   Integrate a GUI using (Specify Library, e.g. Swing or JavaFX).
    -   Provide a user-friendly interface for calendar navigation and date selection.

### Contributing

Contributions are welcome! Please follow these guidelines:

1.  Fork the repository.
2.  Create a new branch for your feature or bug fix.
3.  Submit a pull request with a clear description of your changes.

