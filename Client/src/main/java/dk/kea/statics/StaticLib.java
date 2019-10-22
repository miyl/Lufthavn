package dk.kea.statics;

import java.util.Scanner;
import java.io.IOException;
import java.lang.InterruptedException;

public class StaticLib {

  private static Scanner input = new Scanner(System.in);

  public static void pressEnterToContinue() {
    System.out.println("PRESS ENTER TO CONTINUE");
    input.nextLine();
  }

  public static String getInput() {
    System.out.print("> ");
    return input.nextLine();
  }

  public static boolean isNumericalValue(String input) {
    for (int i = 0; i < input.length(); i++) {
      if (!Character.isDigit(input.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  public static int getNumericalInputLoop() {
    int value = 0;

    while (true) {
      String input = getInput();

      if (input.length() > 0 && isNumericalValue(input)) {
        value = Integer.parseInt(input);
        break;
      }
      else {
        System.out.println("You need to input a numerical value!");
      }
    }

    return value;
  }

  // Returns -1 if not numerical or not positive
  public static int checkNumericalPositiveInput(String input) {
    int value = -1;

    if (input.length() > 0 && isNumericalValue(input)) {
      int number = Integer.parseInt(input);
      if (number > 0) value = number;
    }
    else {
      System.out.println("You need to input a numerical value!");
    }

    return value;
  }

  public static int getNumericalInputRangeLoop(int min, int max) {
    int value = getNumericalInputLoop();

    while (value < min || value > max) {

      System.out.println("Numerical value must be in the range of (" + min + ", " + max + ")");
      value = getNumericalInputLoop();    
    }
    return value;
  }

  public static String getOS() {

    String fullName = System.getProperty("os.name").toLowerCase();

    // -1 = NOT FOUND
    if ( fullName.indexOf("win") > -1 ) return "Windows";
    else if ( fullName.indexOf("mac") > -1 ) return "macOS";

    else return "Linux";

  }

  // Cross platform screen clearing
  public static void clearScreen() {
    if (getOS() == "Windows") {
      try {
          // Run a shell in the current thread with the cls (clear screen) command.
          new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      }
      catch (IOException | InterruptedException e) {}
    }
    else {  // Works for Linux and macOS
      String ansiClear = "\033[H\033[2J"; // The ANSI escape code for screen clearing
      System.out.print(ansiClear);
      System.out.flush();
      //Runtime.getRuntime().exec("clear");
    }
  }

}
