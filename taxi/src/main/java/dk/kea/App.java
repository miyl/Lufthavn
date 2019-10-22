package dk.kea;

import java.io.BufferedReader;
import java.io.IOException;

public class App 
{
    private static BufferedReader inputStream = null;
    private static ServerHandler serverHandler = null;

    public static void main(String args[]) throws IOException {

        serverHandler = new ServerHandler("172.20.10.9", "5000");

    }
}
