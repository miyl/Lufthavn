package dk.kea.handlers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class LuggageDepartmentHandler extends DepartmentHandler {

    public LuggageDepartmentHandler(Socket socket, DataInputStream input, DataOutputStream output) {
        super(socket, input, output, "Luggage");

    }
}