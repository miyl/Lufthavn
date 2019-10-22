package dk.kea.dbconnect;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;

import java.io.FileReader;
import java.io.BufferedReader;
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

      Path currentRelativePath = Paths.get("");
      String s = currentRelativePath.toAbsolutePath().toString();
      System.out.println(s);
      var fileIn = "control/src/main/java/dk/kea/dbconnect/config.ini";



      try {

        var fr = new FileReader(fileIn);
        var br = new BufferedReader(fr);
        var delimiter = "=";
        var line = "";

        while ( (line = br.readLine()) != null ) {
          String firstChar = line.substring(0,1);
          if ( firstChar.equals("#") || firstChar.equals("[") ) continue;  // Skip comments and block titles

          var fields = line.split(delimiter);
          config.put(fields[0], fields[1]);
        }

      }
      catch (IOException e) {
        System.out.println("No config found/made! Please ensure there's a config.ini file in the same directory as this program!");
        System.out.println("Exiting program.");
        System.exit(-1);
      }
  }
  
}
