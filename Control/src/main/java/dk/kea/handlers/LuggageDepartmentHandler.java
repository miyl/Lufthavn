package dk.kea.handlers;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LuggageDepartmentHandler extends DepartmentHandler {

    public LuggageDepartmentHandler(Socket socket, ObjectInputStream input, ObjectOutputStream output) {
        super(socket, input, output, "Luggage");

    }
}