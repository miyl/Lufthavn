package dk.kea.departments;
import dk.kea.client.ServerHandler;

public class LuggageHandler extends ServerHandler {
    public LuggageHandler(){
        super();
    }

    public void start(){
        while(isConnected()){


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

            var serverAnswer = reader.read();

            System.out.println("SERVER: " + serverAnswer);

            switch(serverAnswer){
                case "PIKKEMAND":
                    System.out.println("INFO: clienten sendte en besked med teksten 'pikkemand' til serveren,");
                    System.out.println("      serveren sender lige nu altid det man skriver, fra klienten, tilbage.");
                    System.out.println("      clienten reagerer i switchcasen på svaret fra serveren");
                    break;
                default:
                    break;
            }
        }
    }
}
