package org.limmen.calgen.generator;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.limmen.calgen.domain.Holiday;
import org.limmen.calgen.domain.HolidaySet;
import org.limmen.calgen.domain.Settings;

public class ImageGenerator {

  private Font headerFont;
  private Font columnFont;
  private Font cellFont;
  private Font smallCellFont;

  private final Settings context;

  public ImageGenerator(Settings context) throws IOException {
    this.context = context;
    
    setupFonts();
    createPage(0);
    createPage(1);
  }

  private void setupFonts() {
    List<Font> fonts = Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts());
    String fontName = fonts.stream().filter(f -> f.getFontName().equalsIgnoreCase(this.context.getFontName()))
        .findFirst()
        .map(font -> font.getName())
        .orElse("Helvetica");

    headerFont = new Font(fontName, Font.BOLD, 80);
    columnFont = new Font(fontName, Font.BOLD, 40);
    cellFont = new Font(fontName, Font.PLAIN, 30);
    smallCellFont = new Font(fontName, Font.PLAIN, 20);
  }

  private void createPage(int number) throws IOException {
    BufferedImage image = new BufferedImage(context.getPageSize().getWidth(), context.getPageSize().getHeight(), BufferedImage.TYPE_INT_RGB);

    Graphics graphics = image.createGraphics();
    graphics.setColor(this.context.getBackgroundColor());
    graphics.fillRect(0, 0, context.getPageSize().getWidth(), context.getPageSize().getHeight());

    createHeader(graphics);

    for (int column = 0; column < 6; column++) {
      int month = number == 0 ? column + 7 : column + 1;
      LocalDate currentDate = LocalDate.of(context.getYear() + number, month, 1);

      createColumnHeader(graphics, column, currentDate);
      while (currentDate.get(ChronoField.MONTH_OF_YEAR) == month) {
        createColumnCell(graphics, column, currentDate);
        currentDate = currentDate.plusDays(1);
      }
    }

    graphics.dispose();

    ImageIO.write(image, "png", new File(String.format("calendar-%d.png", number)));
  }

  private void createColumnHeader(Graphics graphics, int column, LocalDate date) {
    int columnWidth = (this.context.getPageSize().getWidth() / 6);
    int startX = columnWidth * column;

    graphics.setColor(this.context.getColumnHeaderColor());
    graphics.fillRect(startX, 300, columnWidth, 100);
    graphics.setColor(new Color(0, 0, 0));
    graphics.drawRect(startX, 300, columnWidth, 100);

    graphics.setColor(new Color(0, 0, 0));
    graphics.setFont(columnFont);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.forLanguageTag("NL"));
    centerString(graphics, formatter.format(date), startX + (columnWidth / 2), 380);
  }

  private void createColumnCell(Graphics graphics, int column, LocalDate date) {
    int columnWidth = (context.getPageSize().getWidth() / 6);
    int cellHeight = ((context.getPageSize().getHeight() - 400) / 31);
    int cell = date.get(ChronoField.DAY_OF_MONTH);
    int startX = columnWidth * column;
    int startY = 400 + ((cell - 1) * cellHeight);

    Optional<HolidaySet> set = getHolidaySetForDate(date);

    if (set.isPresent()) {
      graphics.setColor(set.get().getColor());
    } else {
      if (date.get(ChronoField.DAY_OF_WEEK) > 5) {
        graphics.setColor(this.context.getWeekendColor());
      } else {
        graphics.setColor(this.context.getNormalColor());
      }
    }
    graphics.fillRect(startX, startY, columnWidth, cellHeight);
    graphics.setColor(new Color(0, 0, 0));
    graphics.drawRect(startX, startY, columnWidth, cellHeight);
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E").withLocale(Locale.forLanguageTag("NL"));

    graphics.setColor(new Color(0, 0, 0));
    graphics.setFont(smallCellFont);
    graphics.drawString(String.format("%s %d", formatter.format(date), cell), startX + 15, startY - 25 + (cellHeight / 2));

    if (set.isPresent()) {
      Holiday holiday = set.get().getHolidayForDate(date);
      graphics.setFont(cellFont);
      graphics.drawString(holiday.getType(), startX + 15, startY + 15 + (cellHeight / 2));
    }

    if (this.context.isShowWeekNumbers() && date.get(ChronoField.DAY_OF_WEEK) == 1) {      
      int weekNr = date.get(WeekFields.of(new Locale("nl_NL")).weekOfWeekBasedYear());
      graphics.setFont(smallCellFont);
      graphics.drawString(String.format("Week %d", weekNr), (startX + columnWidth) - 110, startY - 25 + (cellHeight / 2));
    }
  }

  private void createHeader(Graphics graphics) {
    graphics.setColor(this.context.getBackgroundColor());
    graphics.fillRect(0, 0, this.context.getPageSize().getWidth(), 300);
    graphics.setColor(new Color(0, 0, 0));
    graphics.setFont(headerFont);

    centerString(graphics, this.context.getTitle(), this.context.getPageSize().getWidth() / 2, 190);
  }

  private void centerString(Graphics graphics, String text, int centerX, int centerY) {
    FontMetrics fontMetrics = graphics.getFontMetrics();
    int stringWidth = fontMetrics.stringWidth(text);
    int stringHeight = fontMetrics.getAscent();
    int posX = centerX - (stringWidth / 2);
    int posY = centerY - (stringHeight / 2);

    graphics.drawString(text, posX, posY);
  }

  private Optional<HolidaySet> getHolidaySetForDate(LocalDate date) {
    return this.context.getHolidaySets()        
        .stream()
        .filter(s -> s.containsDateInSet(date))
        .findFirst();
  }
}
