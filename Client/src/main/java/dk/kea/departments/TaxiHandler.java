package dk.kea.departments;

import dk.kea.client.ServerHandler;
import dk.kea.shared.Flights;;

public class TaxiHandler extends ServerHandler
{

    public TaxiHandler(){
        super("taxi");
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
                } else if (line.equalsIgnoreCase("fly"))
                {
                    sender.sendPlane(new Flights());
                } else {
                    //Sender information til serveren
                    sender.send(line, false);
                }
                
            }

            // Får information fra server'
            //Flights serverAnswer = reader.read();

            //System.out.println(serverAnswer.getName());
                        
            
            
            /*if((serverAnswer instanceof Flights))
            {
                System.out.println(test);
                test++;
            } else if (serverAnswer instanceof String) {
                System.out.println("String");
                System.out.println("SERVER: " + (String) serverAnswer);

                switch((String) serverAnswer){
                    case "TEST":
                        System.out.println("INFO: clienten sendte en besked med teksten 'TEST' til serveren,");
                        System.out.println("      serveren sender lige nu altid det man skriver, fra klienten, tilbage.");
                        System.out.println("      clienten reagerer i switchcasen på svaret fra serveren");
                        break;
                    default:
                        break;
                }
            }*/
        }
        
    }

}