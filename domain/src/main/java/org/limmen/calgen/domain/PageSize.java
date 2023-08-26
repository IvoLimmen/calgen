package org.limmen.calgen.domain;

public enum PageSize {
  A3(3508,4960),
  A4(2480,3508);

  private int width;
  private int height;

  PageSize(int width, int height) {
    this.height = height;
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }
}
