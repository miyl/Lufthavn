package dk.kea.dbconnect;

import java.util.HashMap;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class Config {

  private HashMap<String, String> config = new HashMap<>();

  public String getConfigValue(String value) {
    if ( !config.containsKey(value) ) {
      System.out.println("The config is missing at least " + value);
      System.out.println("Exiting");
      System.exit(-1);
    }
   return (String) config.get(value); // Will only run if it's found
  }

  public Config() {

      var userdir = System.getProperty("user.dir");
      var path = "/src/main/java/dk/kea/dbconnect/config.ini";
      var fileIn = userdir + path;
      var file = new File(fileIn);

      if (!file.exists())
      {
        fileIn = userdir + "/Control" + path;
        file = new File(fileIn);
      }

      try {

        var fr = new FileReader(file);
        var br = new BufferedReader(fr);
        var delimiter = "=";
        var line = "";

        while ( (line = br.readLine()) != null ) {
          String firstChar = line.substring(0,1);
          if ( firstChar.equals("#") || firstChar.equals("[") ) continue;  // Skip comments and block titles

          var fields = line.split(delimiter);
          config.put(fields[0], fields[1]);
        }

        br.close();

      }
      catch (IOException e) {
        System.out.println("No config found/made! Please ensure there's a config.ini file in the same directory as this program!");
        System.out.println("Exiting program.");
        System.exit(-1);
      }
  }
  
}
