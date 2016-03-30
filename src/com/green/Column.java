package com.green;

/**
 * Created by wsgreen on 3/9/16.
 */
public class Column {
  private int index;
  private String title;
  private String type;

  public Column(int index, String title, String type) {
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
