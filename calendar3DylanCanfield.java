// Programmer: Dylan Canfield
// Class: CS &141
// Date 5/**/2022
// Assignment: Calendar 2
// Take user input choices and display calendar
// Print calendar to file option added
// Manually enter date and mark day on display
// Use Current date and mark day on display
// Display next or previous month/loop if 12 or 1
// Calendar displays correct first day without .LocalDate
// displays correct number of days
// displays name/number of day on current and manual months
// Used key/value pairs for method return
import java.io.*;
import java.util.Scanner; 
import java.time.LocalDate; // current date
import javafx.util.Pair; // key-value pair from method


public class calendar3DylanCanfield {

public static void main(String[] args) { // main method
  // Set variables
  Scanner myObj = new Scanner(System.in);
  
  int monthCounter = 0;
  boolean stop = false; // boolean value for stop is false
  LocalDate now = LocalDate.now();
  
  System.out.print("\n\n**** Welcome to the Java Calendar ****\n"); // Welcome msg.
   
  while(!stop) { // start menu for calendar
   String mc = menuChoice();
   switch (mc) {
     case "e": // enter date
      System.out.print("\nWhat date would you like to see? (Use format of mm/dd) :  ");
      String date = myObj.nextLine();
      String[] values = date.split("/");
      newline();
         
      int day=dayFromDate(values[1]);
      int month=monthFromDate(values[0]);
      
      Pair<Integer, String> pairA = calculateDate(day, month); // name of day
      Pair<Integer, String> pairB = calculateDate(1, month); // first of month index
      Pair<Integer, String> pairC = monthInfo(1, month); // number of days
      int fom = pairB.getKey(); // first of month index
      String dow = pairA.getValue(); // dow = day of week string
      String monthString = pairC.getValue(); // name of month
      int  nom = pairC.getKey(); // number of days

      System.out.printf("%n                             %s - %s  %s%n", dow, monthString, day);
      newline();
      monthCounter = drawMonth(fom, nom, month, day);
      
      newline();
      break;

   case "t": // today's date
     newline();
     LocalDate today = LocalDate.now();
     int monthVal = today.getMonthValue();
     day = today.getDayOfMonth();
     Pair<Integer, String> pairF = calculateDate(day, monthVal);
     String dayName = pairF.getValue(); // name of day
     Pair<Integer, String> pairD = calculateDate(1, monthVal);
     int iod = pairD.getKey(); // index position of day 1
     Pair<Integer, String> pairE = monthInfo(1, monthVal);
     int dayTotal = pairE.getKey(); // number of days in month
     String thisMonth = pairE.getValue(); // name of month
   
     System.out.printf("%n                             %s - %s  %s%n", dayName, thisMonth, day);
     monthCounter = drawMonth(iod, dayTotal, monthVal, day);
      
     newline();
     break;
     
   case "n": // next month
      if (monthCounter <= 0) {
      System.out.print("\nPlease view a calendar before selecting this option.\n");
      mc = menuChoice(); }
      else if (monthCounter == 12) {
      monthCounter = 1; }
      else monthCounter++; 
      Pair<Integer, String> pairG = calculateDate(1, monthCounter);
      iod = pairG.getKey(); // index position of day 1
      Pair<Integer, String> pairH = monthInfo(1, monthCounter);
      dayTotal = pairH.getKey(); // number of days in month
      thisMonth = pairH.getValue(); // name of month
      day = 0;
      
      System.out.printf("%n                                  %s    %n", thisMonth);
      drawMonth(iod, dayTotal, monthCounter, day);
      newline();
      break;

   case "p": // previous month
      if (monthCounter <= 0) {
      System.out.print("\nPlease view a calendar before selecting this option.\n");
      mc = menuChoice(); }
      else if (monthCounter == 1) {
      monthCounter = 12; }
      else monthCounter--; 
      Pair<Integer, String> pairI = calculateDate(1, monthCounter);
      iod = pairI.getKey(); // index position of day 1
      Pair<Integer, String> pairJ = monthInfo(1, monthCounter);
      dayTotal = pairJ.getKey(); // number of days in month
      thisMonth = pairJ.getValue(); // name of month
      day = 0;
      
      System.out.printf("%n                                  %s    %n", thisMonth);
      drawMonth(iod, dayTotal, monthCounter, day);
      newline();
      break;
      
   case "fp": // print to file
      System.out.print("\nEnter month to print (1-12) : ");
      month = myObj.nextInt();
      Pair<Integer, String> pairK = calculateDate(1, month);
      iod = pairK.getKey(); // index position of day 1
      Pair<Integer, String> pairL = monthInfo(1, month);
      dayTotal = pairL.getKey(); // number of days in month
      thisMonth = pairL.getValue(); // name of month
      day = 0;
      System.out.println("Output file name: ");
		String nameOut = myObj.next();
		File out = new File(nameOut);
		try { 
      PrintStream outfile = new PrintStream(out);
      drawMonth2(outfile, iod, dayTotal, month); }
      catch (FileNotFoundException e) {
      System.out.println(e.getMessage()); }
      System.out.println("File Created.\n");
      break;
         
   case "q": // quit
    stop = true;
    break;
   
   default : 
     System.out.print("\nPlease enter a valid command.");
       
} // end of switch(mc)
}
} // end of boolean while statement


// method for menu choice
public static String menuChoice() {
   Scanner menu = new Scanner(System.in);
   System.out.print("\nPlease type a command :\n");
   System.out.print(" \"e\" to enter a date and display the corresponding calendar\n");
   System.out.print(" \"t\" to get today's date and display the calendar\n");
   System.out.print(" \"n\" to display the next month\n");
   System.out.print(" \"p\" to display the previous month\n");
   System.out.print(" \"fp\" to print calendar to file\n");
   System.out.print(" \"q\" to quit the program\n");
   newline();
  
   String choice = menu.nextLine(); 
  
   return choice; } // end of menuChoice method

   
public static void newline() { // print newline
   System.out.printf("%n");  } // end newline
                         
public static int monthFromDate(String date) {
   int monthInt=Integer.parseInt(date); // convert to integer
   return monthInt;
                                                }
            
public static int dayFromDate(String date) {
   int dayInt=Integer.parseInt(date); // convert to integer
   return dayInt; }

// method to get month name and number of days            
public static  Pair<Integer, String> monthInfo(int dd, int mm) {
   int dayCount = 0;
   String monthString;      
   switch (mm) {
      case 1:  monthString = "January";
        dayCount = 31;
          break;
      case 2:  monthString = "February";
        dayCount = 28;
          break;
      case 3:  monthString = "March";
        dayCount = 31;
          break;
      case 4:  monthString = "April";
        dayCount = 30;
          break;
      case 5:  monthString = "May";
        dayCount = 31;
          break;
      case 6:  monthString = "June";
        dayCount = 30;
          break;
      case 7:  monthString = "July";
        dayCount = 31;
          break;
      case 8:  monthString = "August";
        dayCount = 31;
           break;
      case 9:  monthString = "September";
        dayCount = 30;
           break;
      case 10: monthString = "October";
        dayCount = 31;
           break;
      case 11: monthString = "November";
        dayCount = 30;
           break;
      case 12: monthString = "December";
        dayCount = 31;
           break;
      default: monthString = "Invalid month";
          break;
        } // end switch (mm)
      return new Pair<Integer, String>(dayCount, monthString);
        } // end monthInfo method

// method to draw calendar 
public static int drawMonth(int fom, int dayCount, int monthCounter, int day) {  
   newline();
   System.out.print("Sun         Mon        Tues       Wed        Thu        Fri        Sat\n");  
   lined();
   int dayCounter = firstRow(fom, day);
      drawRow();
   int dayCounterRow2 = dateRow(dayCounter, dayCount, day);
      drawRow();
   int dayCounterRow3 = dateRow(dayCounterRow2, dayCount, day);
      drawRow();
   int dayCounterRow4 = dateRow(dayCounterRow3, dayCount, day);
      drawRow();
   int dayCounterRow5  = dateRow(dayCounterRow4, dayCount, day);
       drawRow(); 
   if (dayCounterRow5 <= dayCount) {
      dateRow(dayCounterRow5, dayCount, day); 
      drawRow(); }      
   return monthCounter;
} // end drawMonth
                                              
public static void lined() { // print horizontal line for calendar
   for ( int i = 0; i<= 77; i++ ) {
      System.out.printf("%c", 196);
         if (i == 77) {
         newline(); }
      }
      } // end lined method

// method to find 1st of month and day name      
public static  Pair<Integer, String> calculateDate(int dd, int mm) {
   String day[] = { "Sunday", "Monday", "Tuesday",
						 "Wednesday", "Thursday", "Friday",
						 "Saturday" }; 
                   
	int m_no[] = { 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5 };

	int total = (2022 % 100) + ((2022 % 100) / 4) + dd
					+ m_no[mm - 1] + 6;
		
   String dow = (day[(total % 7)]);
   int nod = (total % 7);
   return new Pair<Integer, String>(nod, dow);

	} // end of calculateDate method

// method for first week of month   
public static int firstRow(int start, int day) {
   int dayCounter = 1;
   for (int row = 0; row<= 6; row++) {
      System.out.print("|");
      
      if (row >= start) {
        System.out.printf("%d", dayCounter);
        
          if (dayCounter == day) {
            System.out.print("*");
            sideC();}
            
        else sideB();
        start++; dayCounter++; }
                 
      else if (row <= start) {
        sideA(); }
         
      if (row == 6) {
        System.out.print("|"); } // closing bracket
                          
    } // end row loop
    newline();
    return dayCounter;                                                  
} // end firstRow method

// method to draw blank rows                                                   
public static void drawRow() { // print rows
   for (int boxloop = 0; boxloop<= 3; boxloop++) {
      for (int row = 0; row<= 6; row++) {
        System.out.print("|");
        
      if (row == 6) {
        sideA();
        System.out.print("|"); } // right side
        sideA(); } // end row loop
        
        newline();
    } // end box loop
    lined();
} // end of drawRow method

// method for date rows            
public static int dateRow(int dayCounter, int dayCount, int day) {
   for (int row = 0; row<= 6; row++) {
     System.out.print("|");
     
     if (dayCounter <= 9) {
       System.out.printf("%d", dayCounter);
       if (dayCounter == day) {
            System.out.print("*");
            sideC();}

       else sideB();
       dayCounter++; }
           
     else if (dayCounter >= 10 && dayCounter <= dayCount) {
       System.out.printf("%d", dayCounter);
       if (dayCounter == day) {
            System.out.print("*");
            sideD();}

       else sideC();
       dayCounter++; }
       
     else if (dayCounter > dayCount) {
       sideA(); 
       dayCounter++; }
       
     if (row == 6) {
       System.out.print("|"); // closing bracket
       break; }
    } // end row loop
    newline();
    return dayCounter;                                                  
} // end of dateRow method
      
public static void sideA() { // blank spacing A
   System.out.printf("          "); // no date
} // end sideA
                       
public static void sideB() { // blank spacing B
   System.out.printf("         "); // single digit date
} // end sideB
                       
public static void sideC() { // blank spacing C
   System.out.printf("        "); // double digit date
} // end sideC

public static void sideD() { // blank spacind D
    System.out.printf("       "); // double w/date marker
} // end sideD

public static void drawMonth2(PrintStream out, int fom, int dayCount, int day) {  
   newline2(out);
   out.print("Sun         Mon        Tues       Wed        Thu        Fri        Sat\n");  
   lined2(out);
   int dayCounter = firstRow2(out, fom, day);
      drawRow2(out);
   int dayCounterRow2 = dateRow2(out, dayCounter, dayCount, day);
      drawRow2(out);
   int dayCounterRow3 = dateRow2(out, dayCounterRow2, dayCount, day);
      drawRow2(out);
   int dayCounterRow4 = dateRow2(out, dayCounterRow3, dayCount, day);
      drawRow2(out);
   int dayCounterRow5  = dateRow2(out, dayCounterRow4, dayCount, day);
       drawRow2(out); 
   if (dayCounterRow5 <= dayCount) {
      dateRow2(out, dayCounterRow5, dayCount, day); 
      drawRow2(out); }      
  
} // end drawMonth

public static void lined2(PrintStream out) { // print horizontal line for calendar
   for ( int i = 0; i<= 77; i++ ) {
      out.print("_");
         if (i == 77) {
         newline2(out); }
      }
      } // end lined method
      
public static int firstRow2(PrintStream out, int start, int day) {
   int dayCounter = 1;
   for (int row = 0; row<= 6; row++) {
      out.print("|");
      
      if (row >= start) {
        out.printf("%d", dayCounter);
        
          if (dayCounter == day) {
            out.print("*");
            sideC2(out);}
            
        else sideB2(out);
        start++; dayCounter++; }
                 
      else if (row <= start) {
        sideA2(out); }
         
      if (row == 6) {
        out.print("|"); } // closing bracket
                          
    } // end row loop
    newline2(out);
    return dayCounter;                                                  
} // end firstRow method

public static int dateRow2(PrintStream out, int dayCounter, int dayCount, int day) {
   for (int row = 0; row<= 6; row++) {
     out.print("|");
     
     if (dayCounter <= 9) {
       out.printf("%d", dayCounter);
       if (dayCounter == day) {
            out.print("*");
            sideC2(out);}

       else sideB2(out);
       dayCounter++; }
           
     else if (dayCounter >= 10 && dayCounter <= dayCount) {
       out.printf("%d", dayCounter);
       if (dayCounter == day) {
            out.print("*");
            sideD2(out);}

       else sideC2(out);
       dayCounter++; }
       
     else if (dayCounter > dayCount) {
       sideA2(out); 
       dayCounter++; }
       
     if (row == 6) {
       out.print("|"); // closing bracket
       break; }
    } // end row loop
    newline2(out);
    return dayCounter;                                                  
} // end of dateRow method

public static void drawRow2(PrintStream out) { // print rows
   for (int boxloop = 0; boxloop<= 3; boxloop++) {
      for (int row = 0; row<= 6; row++) {
        out.print("|");
        
      if (row == 6) {
        sideA2(out);
        out.print("|"); } // right side
        sideA2(out); } // end row loop
        
        newline2(out);
    } // end box loop
    lined2(out);
} // end of drawRow method




public static void sideA2(PrintStream out) { // blank spacing A
   out.printf("          "); // no date
} // end sideA
                       
public static void sideB2(PrintStream out) { // blank spacing B
   out.printf("         "); // single digit date
} // end sideB
                       
public static void sideC2(PrintStream out) { // blank spacing C
   out.printf("        "); // double digit date
} // end sideC

public static void sideD2(PrintStream out) { // blank spacind D
    out.printf("       "); // double w/date marker
} // end sideD

public static void newline2(PrintStream out) { // print newline
   out.printf("%n");  } // end newline
   
public static File whatFile(Scanner console) { // ask for file
	System.out.print("Input file name: "); 
	File answer = new File(console.nextLine());
         
   while(!answer.exists()) {
	System.out.println("File not found. Try again: ");
   answer = new File(console.nextLine());	} // end of filename check
   return  answer;
}  // end of whatFile method





} // end of class calendarDylanCanfield
