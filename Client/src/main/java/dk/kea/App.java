package dk.kea;

import dk.kea.departments.TaxiHandler;

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

  public void run() {

      System.out.println("Enter username: ");
      username = scanner.nextLine();
      System.out.println("Enter password: ");
      password = scanner.nextLine();

      String serverAnswer = "";

        try{
            socket = new Socket(address, port);

            if(socket.isConnected()){
                output = new ObjectOutputStream(socket.getOutputStream());
                input = new ObjectInputStream(socket.getInputStream());

                output.writeUTF(username + ";" + password);
                output.flush();
                serverAnswer = input.readUTF();

                //Ok;taxi
                String[] splitServerAnswer = serverAnswer.split(";");

                if(splitServerAnswer[0].equals("Ok")){

                    switch (splitServerAnswer[1]){
                        case "taxi":
                            running = true;
                            var taxi = new TaxiHandler(socket, input, output);
                            taxi.start();
                            break;
                        case "luggage":
                            System.out.println("I luggage");
                            running = true;
                            break;
                        default:
                            break;
                    }

                }
                else {
                    System.out.println("Username/Password incorrect");
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
}
