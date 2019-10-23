import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.*; // Experiment to see if it makes a difference

// Just for debugging
import java.sql.Driver; // Just for testing
import java.util.Enumeration;
import java.util.Collections;
import java.util.List;


/**
 * @author Marcus Miyalys resolutions@riseup.net
 */
public class DBConnect {
  private Connection conn = null;

  public DBConnect() {
    // DEBUG
    //testMariaDB();
    //testDB();
    Config config = new Config();

    final String DB_PRE = "jdbc";
    final String DB_SUB_PROTOCOL = ":mysql://";

    final String DB_ADDRESS = config.getConfigValue("address"); 
    final String DB_PORT = ":" + config.getConfigValue("port");
    final String DB_NAME = "/" + config.getConfigValue("name");
    
    final String FULL_DB_STRING = DB_PRE + DB_SUB_PROTOCOL + DB_ADDRESS + DB_PORT + DB_NAME;

    final String USERNAME = config.getConfigValue("user");
    final String PASSWORD = config.getConfigValue("pass");

    try {
      conn = DriverManager.getConnection(FULL_DB_STRING, USERNAME, PASSWORD);
    }
    catch(Exception e) { System.out.println("DBConnect: " + e);}
    //TODO: Close connection afterwards!
    // conn.close();
  }

  public Connection getConnection() {
    return conn;
  }

  // Test if the mariadb driver is loaded
  private void testMariaDB() {
    try {
      Class.forName("org.mariadb.jdbc.Driver");
    }
    catch (ClassNotFoundException e) {System.out.println(e);}
  }

  // Test if the official Oracle driver is loaded
  private void testOracleDB() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    }
    catch (ClassNotFoundException e) {System.out.println(e);}
  }

  // Another test to see what drivers are loaded (can be multiple)
  private void testDBS() {
    Enumeration<Driver> drvs = DriverManager.getDrivers();
    List drvslist = Collections.list(drvs);
    for (Object drv : drvslist) {
      System.out.println(drv);
    }
  }

}
