package com.green;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by wsgreen on 3/7/16.
 */
public class Table {

  private final File tableFile;
  public final Map<String, Column> schema;
  public final Map<Integer, List<Object>> data; //TODO: using an internal int as the primary key
  private int maxKeyValue = 0;

  public Table() {
    tableFile = null;
    schema = new LinkedHashMap<>();
    data = new LinkedHashMap<>();
  }

  public Table(String tableFilePath) {
    tableFile = new File(tableFilePath);
    schema = new LinkedHashMap<>();
    data = new LinkedHashMap<>();

    readSchema();
    readFile();
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
      String type = column[0].toLowerCase().trim();

      schema.put(title, new Column(i, title, type));
    }
  }

  private void readFile() {
    Scanner input = null;

    try {
      input = new Scanner(tableFile);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    input.nextLine();
    while(input.hasNextLine()) {
      String[] line = input.nextLine().split(",");
      List<Object> row = new ArrayList<>();
      Column col = schema.values().iterator().next();
      for(int i=0;i<line.length;i++) {
        row.add(DataTypes.parseValue(line[i], col.getType()));
      }

      Integer primaryKey = (Integer) row.get(0);
      maxKeyValue = Math.max(maxKeyValue, primaryKey);
      data.put(primaryKey, row);
    }
  }

  public void print() {
      schema.forEach((title, column) -> {
        System.out.print(title + ":" + column.getType() + " ");
      });
      System.out.println();

      data.forEach((key, value) -> {
        value.forEach( obj -> {
          System.out.print(obj + " ");
        });
      });
  }
}
