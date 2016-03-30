package com.green;

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

  public Table execute(String query) {

  }

  private Table projection(Table table, Set<String> columns) {
    if(columns.contains("*"))
      return table;

    Table results = new Table();
    table.schema.forEach((key, value) -> {
      if (columns.contains(key))
        results.schema.put(key, value);
    });

    return results;
  }
}
