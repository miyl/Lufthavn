package dk.kea.handlers;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class FuelDepartmentHandler extends DepartmentHandler {

    public FuelDepartmentHandler(Socket socket, ObjectInputStream input, ObjectOutputStream output) {
        super(socket, input, output, "Fuel");

    }
}