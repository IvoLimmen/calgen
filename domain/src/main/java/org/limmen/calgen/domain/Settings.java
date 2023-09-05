package org.limmen.calgen.domain;

import java.awt.Color;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Settings {

  private String language;
  private List<HolidaySet> holidaySets;
  private Color weekendColor;
  private Color backgroundColor;
  private Color normalColor;
  private Color columnHeaderColor;
  private String fontName;
  private boolean showWeekNumbers;
  private String title;
  private PageSize pageSize;  

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }
  
  public PageSize getPageSize() {
    return pageSize;
  }

  public void setPageSize(PageSize pageSize) {
    this.pageSize = pageSize;
  }
  
  public String getTitle() {
    return title;
  }

  public Color getWeekendColor() {
    return weekendColor;
  }

  public Color getBackgroundColor() {
    return backgroundColor;
  }

  public Color getNormalColor() {
    return normalColor;
  }

  public Color getColumnHeaderColor() {
    return columnHeaderColor;
  }

  public String getFontName() {
    return fontName;
  }

  public boolean isShowWeekNumbers() {
    return showWeekNumbers;
  }

  public void setWeekendColor(Color weekend) {
    this.weekendColor = weekend;
  }

  public void setBackgroundColor(Color background) {
    this.backgroundColor = background;
  }

  public void setNormalColor(Color normal) {
    this.normalColor = normal;
  }

  public void setColumnHeaderColor(Color columnHeader) {
    this.columnHeaderColor = columnHeader;
  }

  public void setFontName(String fontName) {
    this.fontName = fontName;
  }

  public void setShowWeekNumbers(boolean showWeekNumbers) {
    this.showWeekNumbers = showWeekNumbers;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<HolidaySet> getHolidaySets() {
    return holidaySets;
  }

  public void setHolidaySets(List<HolidaySet> holidaySets) {
    this.holidaySets = holidaySets;
  }

  public int getYear() {
    return this.holidaySets.stream()
        .flatMap(h -> h.getHolidays().stream())
        .map(Holiday::getStartDate)
        .map(LocalDate::getYear)
        .collect(Collectors.minBy((o1, o2) -> {
          return o1.compareTo(o2);
        }))
        .get();
  }
}