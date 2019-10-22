package dk.kea.menugenerator;

import java.net.SocketException;

public abstract class MenuPoint {
  public String name;

  public abstract void run() throws SocketException;
}
