package dk.kea;

import dk.kea.departments.LuggageHandler;
import dk.kea.departments.TaxiHandler;
import dk.kea.menugenerator.MenuGenerator;
import dk.kea.menugenerator.MenuPoint;
import dk.kea.shared.Calculator;

import java.text.ParseException;

public class App {

    public static String address = "localhost";
    public static int port = 5000;

    public class Mp1 extends MenuPoint {
        public void run() {
            System.out.println("Running the TAXI Client..");
            var taxi = new TaxiHandler();
            taxi.start();
        }

        public Mp1() {
            name = "Taxi";
        }
    }

    public class Mp2 extends MenuPoint {
        public void run() {
            System.out.println("Running menu point 2!");
        }

        public Mp2() {
            name = "Cleaning";
        }
    }

    public class Mp3 extends MenuPoint {
        public void run() {

            System.out.println("Running the TAXI Client..");
            var luggage = new LuggageHandler();
            luggage.start();
        }

        public Mp3() {
            name = "Luggage";
        }
    }

    public class Mp4 extends MenuPoint {
        public void run() {
            System.out.println("Running menu point 4!");
        }

        public Mp4() {
            name = "Fuel";
        }
    }

    public class Mp5 extends MenuPoint {
        public void run() {
            System.out.println("Running menu point 5!");
        }

        public Mp5() {
            name = "Options";
        }
    }

    public class Mp6 extends MenuPoint {
        public void run() {
                Calculator calculator = new Calculator();
                calculator.calculate();

        }

        public Mp6() {
            name = "Calculator";
        }
    }

    public static void main(String[] args) {
    new App().run();
  }

  public void run() {
    var mg = new MenuGenerator("Testmenu", new Mp1(), new Mp2(), new Mp3(), new Mp4(), new Mp5(), new Mp6());
    mg.run();
    //mg.run(1);
  }
}
