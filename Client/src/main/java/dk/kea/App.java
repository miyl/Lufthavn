package dk.kea;

import dk.kea.departments.*;
import dk.kea.menugenerator.MenuGenerator;
import dk.kea.menugenerator.MenuPoint;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class App {

    static String address = "localhost";
    static int port = 5000;
    Scanner scanner = new Scanner(System.in);
    Socket socket;
    ObjectOutputStream output;
    ObjectInputStream input;
    String username;
    String password;
    public static boolean running = false;

    public static void main(String[] args) {
    while(!running){
        new App().run();
    }
  }

  public class Mp1 extends MenuPoint {
    public void run() {
      System.out.println("\nLog in\n--------------");
      System.out.printf("Enter username\n> ");
      username = scanner.nextLine();
      System.out.printf("Enter password\n> ");
      password = scanner.nextLine();
      System.out.println("--------------");

      String serverAnswer = "";

        try{
            socket = new Socket(address, port);

            if(socket.isConnected()){
                output = new ObjectOutputStream(socket.getOutputStream());
                input = new ObjectInputStream(socket.getInputStream());

                output.writeUTF(username + ";" + password);
                output.flush();

                serverAnswer = input.readUTF();

                String[] splitServerAnswer = serverAnswer.split(";");

                if(splitServerAnswer[0].equals("Ok")){

                    switch (splitServerAnswer[1]){
                        case "Taxi":
                            running = true;
                            var taxi = new TaxiHandler(socket, input, output);
                            taxi.start();
                            break;
                        case "Baggage":
                            running = true;
                            break;
                        default:
                            break;
                    }

                }
                else {
                    System.out.println("[ERROR]: Incorrect username or password");
                    socket.close();
                    input.close();
                    output.close();

                }


            }
            else {
                System.out.println("Something went wrong connection error");
                socket.close();
            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Mp1() {
        name = "Login";
    }
    }

    public class Mp2 extends MenuPoint {
        public void run() {
            System.out.println("No options available..");
        }

        public Mp2() {
            name = "Options";
        }
    }

  public void run() {

      var mg = new MenuGenerator("Client", new Mp1(), new Mp2());
      mg.run();

  }
}