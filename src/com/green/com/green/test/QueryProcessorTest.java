package com.green.com.green.test;

import com.green.Column;
import com.green.DataTypes;
import com.green.QueryProcessor;
import com.green.Table;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wsgreen on 3/9/16.
 */
public class QueryProcessorTest {

  public static void testProjection() {
    Table test1 = new Table();
    test1.schema.put("string", new Column(0, "string", DataTypes.VARCHAR));
    test1.schema.put("int", new Column(1, "int", DataTypes.INT));
    test1.schema.put("double", new Column(2, "double", DataTypes.DOUBLE));
    test1.data.put(1, Arrays.asList("test", 99, 99.0));
    Map<String, Table> db = new HashMap<>();
    db.put("test1", test1);
    QueryProcessor qp = new QueryProcessor(db);
    qp.execute("select * from test1");
  }

  public static void main(String[] args) {
    testProjection();
  }
}
