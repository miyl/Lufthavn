package dk.kea.handlers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TaxiDepartmentHandler extends DepartmentHandler {

    public TaxiDepartmentHandler(Socket socket, DataInputStream input, DataOutputStream output) {
        super(socket, input, output, "Taxi");

    }
}