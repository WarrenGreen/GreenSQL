package com.green;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by wsgreen on 3/7/16.
 */
public class Server {
  private static final String DATABASE_FILE_KEY = "database";
  private static final String TABLE_EXTENSION = ".tbl";

  private final String configFilePath;
  private final Map<String, String> parameterMap;
  private final Map<String, Table> tables;
  private final QueryProcessor queryProcessor;

  private File databaseFile;

  public Server() {
    this("./server.cfg");
  }

  public Server(String configFile) {
    parameterMap = new HashMap<>();
    tables = new HashMap<>();
    configFilePath = configFile;
    readConfig();

    databaseFile = new File(parameterMap.get(DATABASE_FILE_KEY));
    if (databaseFile.exists()) {
      readTables();
    } else {
      try {
        databaseFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    queryProcessor = new QueryProcessor(tables);

  }

  public void execute(String query) {
    queryProcessor.execute(query);
  }

  private void readConfig() {
    File configFile = new File(configFilePath);
    Scanner input = null;
    try {
      input = new Scanner(configFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("Config file not found.");
    }

    while (input.hasNextLine()) {
      String[] line = input.nextLine().split("=");
      parameterMap.put(line[0].trim().toLowerCase(), line[1].trim().toLowerCase());

    }
  }

  private void readTables() {
    Scanner input = null;

    try {
      input = new Scanner(databaseFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("Database file not found.");
    }

    while(input.hasNextLine()) {
      String line = input.nextLine().toLowerCase().trim();
      tables.put(line, new Table(line+TABLE_EXTENSION));
    }
  }
}

