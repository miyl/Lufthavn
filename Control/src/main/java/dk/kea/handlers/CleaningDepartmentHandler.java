package dk.kea.handlers;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CleaningDepartmentHandler extends DepartmentHandler {

    public CleaningDepartmentHandler(Socket socket, ObjectInputStream input, ObjectOutputStream output) {
        super(socket, input, output, "Cleaning");

    }
}