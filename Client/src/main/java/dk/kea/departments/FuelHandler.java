package dk.kea.departments;

import dk.kea.client.ServerHandler;
import dk.kea.shared.Flights;

public class FuelHandler extends ServerHandler {
    public FuelHandler(){
        super("fuel");
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
                } else if (line.split(" ")[0].equalsIgnoreCase("fly"))
                {
                    sender.sendPlane(new Flights(line.split(" ")[1]));
                } else {
                    //Sender information til serveren
                    sender.send(line, false);
                }
                
            }

            // Får information fra server'
            //Flights serverAnswer = reader.read();

            if(getFlightList().size() > 0) 
            {
                System.out.println(getFlightList().get(getFlightList().size()-1).getName());
            }
        }
    }
}
