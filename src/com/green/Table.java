package com.green;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by wsgreen on 3/7/16.
 */
public class Table {

  private final File tableFile;
  public final Map<String, Column> schema;

  public Table() {
    tableFile = null;
    schema = new LinkedHashMap<>();
  }

  public Table(String tableFilePath) {
    tableFile = new File(tableFilePath);
    schema = new LinkedHashMap<>();

    readSchema();
  }

  private void readSchema() {
    Scanner input = null;

    try {
      input = new Scanner(tableFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    String[] line = input.nextLine().split(",");
    for(int i=0;i<line.length;i++) {
      String[] column = line[i].split(":");
      String title = column[0].toLowerCase().trim();
      Class type = getColumnClass(column[0].toLowerCase().trim());

      schema.put(title, new Column(i, title, type));
    }
  }

  private Class getColumnClass(String type) {
    switch (type) {
      case "INTEGER":
        return Integer.class;
      case "SHORTINT":
        return Short.class;
      case "VARCHAR":
        return String.class;
      case "FLOAT":
        return Float.class;
      case "DOUBLE":
        return Double.class;
    }

    throw new RuntimeException("Cannot parse database schema.");
  }



}
