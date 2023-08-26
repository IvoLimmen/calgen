package org.limmen.calgen.domain;

import java.time.LocalDate;

public class Vacation {

  private LocalDate startDate;
  private LocalDate endDate;
  private String type;
  private int colorCode;

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public String getType() {
    return type;
  }

  public int getColorCode() {
    return colorCode;
  }
 
  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setColorCode(int colorCode) {
    this.colorCode = colorCode;
  }

  public boolean isOverlapping(LocalDate date) {
    return (this.getEndDate().isAfter(date) || this.getEndDate().isEqual(date))
        && (this.getStartDate().isBefore(date) || this.getStartDate().isEqual(date));
  }
}
