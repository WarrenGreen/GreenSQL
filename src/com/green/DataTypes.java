package com.green;

/**
 * Created by wsgreen on 3/30/16.
 */
public class DataTypes {
  public final static String INT        = "integer";
  public final static String SHORTINT   = "shortint";
  public final static String VARCHAR    = "varchar";
  public final static String FLOAT      = "float";
  public final static String DOUBLE     = "double";

  public static Object parseValue(String value, String type) {
      switch (type) {
        case INT:
          return Integer.parseInt(value);
        case SHORTINT:
          return Short.parseShort(value);
        case VARCHAR:
          return value;
        case FLOAT:
          return Float.parseFloat(value);
        case DOUBLE:
          return Double.parseDouble(value);
      }

      throw new RuntimeException("Cannot parse database schema.");
    }
  }
