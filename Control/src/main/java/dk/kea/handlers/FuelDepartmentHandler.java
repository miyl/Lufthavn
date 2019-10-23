package dk.kea.handlers;

import java.io.*;
import java.net.Socket;

public class FuelDepartmentHandler extends DepartmentHandler {

    public FuelDepartmentHandler(Socket socket, DataInputStream input, DataOutputStream output) {
        super(socket, input, output, "Fuel");

    }
}