package com.green;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wsgreen on 3/8/16.
 */
public class QueryProcessor {

  private final Map<String, Table> tables;

  public QueryProcessor(Map<String, Table> tables) {
    this.tables = tables;
  }

  public void execute(String query) {
    String[] split = query.split(" ");
    Set<String> columns = new HashSet<>();
    int index = 0;
    if(split[index].equalsIgnoreCase("select")) {
      index++;
    }

    while(!split[index].equalsIgnoreCase("from")) {
      columns.add(split[index]);
      index++;
    }

    index++; // from

    String table = split[index];

    Table result = projection(tables.get(table), columns);

    result.print();

  }

  private Table projection(Table table, Set<String> columns) {
    if(columns.contains("*"))
      return table;

    Table results = new Table();
    table.schema.forEach((key, value) -> {
      if (columns.contains(key))
        results.schema.put(key, value);
    });

    table.data.forEach((key, value) -> {
      List<Object> row = new ArrayList();
      results.schema.forEach((title, column) -> {
        row.add(value.get(column.getIndex()));
      });
      results.data.put(key, row);
    });

    return results;
  }
}
