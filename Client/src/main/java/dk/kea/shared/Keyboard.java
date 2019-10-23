package dk.kea.shared;

import java.util.Scanner;

import dk.kea.client.ServerHandler;

public class Keyboard implements Runnable
{
    public Scanner kb;
    private ServerHandler client;

    public Keyboard(ServerHandler client) {

        kb = new Scanner(System.in);
        this.client = client;
    }

    @Override
    public void run() {
        while(client.isConnected())
        {
            
        }
        kb.close();
    }

    public Scanner getReader()
    {
        return kb;
    }
    
}