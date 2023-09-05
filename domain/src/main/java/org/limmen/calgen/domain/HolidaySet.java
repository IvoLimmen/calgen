package org.limmen.calgen.domain;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HolidaySet {

  private Color color;
  private List<Holiday> holidays = new ArrayList<>();

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public List<Holiday> getHolidays() {
    return holidays;
  }

  public void setHolidays(List<Holiday> holidays) {
    this.holidays = holidays;
  }

  public boolean containsDateInSet(LocalDate date) {
    return this.holidays
        .stream()
        .anyMatch(f -> f.isOverlapping(date));
  }

  public Holiday getHolidayForDate(LocalDate date) {
    return this.holidays
        .stream()
        .filter(s -> s.isOverlapping(date))
        .findFirst()
        .get();
  }
}
