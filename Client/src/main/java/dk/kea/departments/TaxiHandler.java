package dk.kea.departments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dk.kea.client.ServerHandler;

public class TaxiHandler extends ServerHandler
{

    public TaxiHandler(){
        super("taxi");
    }

    public void start(){
        while (connected) {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line;
            try {
                line = keyboard.readLine();
                sender.send(line);
            } catch (IOException e) {
                close();
                e.printStackTrace();
            }
        }
        
    }

}