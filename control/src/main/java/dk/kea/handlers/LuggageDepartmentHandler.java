package dk.kea.handlers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class LuggageDepartmentHandler extends DepartmentHandler {

    public LuggageDepartmentHandler(Socket socket, DataInputStream input, DataOutputStream output) {
        super(socket, input, output, "Luggage");

    }
}