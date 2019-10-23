package dk.kea.handlers;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TaxiDepartmentHandler extends DepartmentHandler {

    public TaxiDepartmentHandler(Socket socket, ObjectInputStream input, ObjectOutputStream output) {
        super(socket, input, output, "Taxi");

    }
}