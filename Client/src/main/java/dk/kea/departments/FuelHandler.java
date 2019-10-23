package dk.kea.departments;

import dk.kea.client.ServerHandler;

public class FuelHandler extends ServerHandler {
    public FuelHandler(){
        super("Fuel");
    }
    public void start(){
        while (isConnected()) {

            //Får information fra keyboardet - hvis der er noget.
            System.out.printf("> ");
            if (keyboard.getReader().hasNextLine()) {
                String line = keyboard.getReader().nextLine();
                if(line.equalsIgnoreCase("exit"))
                {
                    close();
                    break;
                }

                //Sender information til serveren
                sender.send(line);
            }

            // Får information fra server
            var serverAnswer = reader.read();

            System.out.println("SERVER: " + serverAnswer);

            switch(serverAnswer){
                case "TEST":
                    System.out.println("INFO: clienten sendte en besked med teksten 'TEST' til serveren,");
                    System.out.println("      serveren sender lige nu altid det man skriver, fra klienten, tilbage.");
                    System.out.println("      clienten reagerer i switchcasen på svaret fra serveren");
                    break;
                default:
                    break;
            }
        }

    }
}