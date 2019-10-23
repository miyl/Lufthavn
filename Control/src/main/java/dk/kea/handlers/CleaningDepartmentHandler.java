package dk.kea.handlers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class CleaningDepartmentHandler extends DepartmentHandler {

    public CleaningDepartmentHandler(Socket socket, DataInputStream input, DataOutputStream output) {
        super(socket, input, output, "Cleaning");
    }
}
