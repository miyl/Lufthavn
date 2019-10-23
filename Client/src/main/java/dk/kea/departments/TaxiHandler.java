package dk.kea.departments;

import java.util.Arrays;

import dk.kea.client.ServerHandler;
import dk.kea.shared.Flights;;import java.net.Socket;

public class TaxiHandler extends ServerHandler
{

    public TaxiHandler(Socket socket){
        super( socket);
    }

    public void start(){

        while (isConnected()) {

            //Får information fra keyboardet - hvis der er noget.
            System.out.printf("> ");
            if (keyboard.getReader().hasNextLine()) {
                String[] tokens = keyboard.getReader().nextLine().split(" ");

                if(tokens[0].equalsIgnoreCase("EXIT"))
                {
                    close();
                    break;                    
                } else if (tokens[0].equalsIgnoreCase("FLIGHT")) {
                    sender.sendPlane(new Flights(tokens[1]));
                } else if(tokens[0].equalsIgnoreCase("LIST")) {

                    if(getFlightList().size() > 0)
                    {
                        System.out.print("[INFO]: Active planes in this department:\n\n");
                        getFlightList().forEach(value -> System.out.print(
                            "        [" + value.getId() + ", " + value.getName() + "]\n"
                            ));
                        System.out.println();
                    } else {
                        System.out.print("[INFO]: No active planes in this department.\n");
                    }
                } else {
                    //Sender information til serveren
                    sender.send(String.join(" ", tokens), false);
                }
                
            }
        }
    }
}