package dk.kea;

import java.io.IOException;

import dk.kea.client.ServerHandler;
import dk.kea.menugenerator.MenuGenerator;
import dk.kea.menugenerator.MenuPoint;

public class App {

    public class Mp1 extends MenuPoint {
        public void run() {
            System.out.println("Running menu point 1!");
            new ServerHandler("172.20.10.9", "5000");
        }

        public Mp1() {
            name = "Start";
        }
    }

    public class Mp2 extends MenuPoint {
        public void run() {
            System.out.println("Running menu point 2!");
        }

        public Mp2() {
            name = "Options";
        }
    }

    public static void main(String[] args) throws IOException {
    new App().run();
  }

  public void run() {
    var mg = new MenuGenerator("Testmenu", new Mp1(), new Mp2());
    mg.run();
    //mg.run(1);
  }
}
