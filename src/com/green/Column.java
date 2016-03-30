package com.green;

/**
 * Created by wsgreen on 3/9/16.
 */
public class Column {
  private int index;
  private String title;
  private Class type;

  public Column(int index, String title, Class type) {
    this.index = index;
    this.title = title;
    this.type = type;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Class getType() {
    return type;
  }

  public void setType(Class type) {
    this.type = type;
  }
}
