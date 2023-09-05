# Configuration

There is a configuration file called `settings.json` in the `config` directory.
Here is an explanation on what every property means and how to use it.

## Settings

In the root of the settings are the followng settings:

title - title that is shown on the top of the calendar.
fontName - the name of the font that is used for all the text on the calendar.
backgroundColor - color that is used for the background of the calendar (on top and at the bottom where no days are present).
normalColor - background color of a single day
weekendColor - background color to use on a weekend day
columnHeaderColor - background color on the top column displaying the month
pageSize - A4 or A3 for the size of the images
showWeekNumbers - true if you like to see the weeknumbers in the calendar displayed
language - use the ISO standard to make the labels in a different language (like EN, NL, etc.)

## HolidaySets

You can define color sets for a set of holidays.

color - the background color to use for the vacation
holidays - set of holidays to draw with a specific color.

## Holidays

A holiday simply has the following properties:

startDate - starting date of the vacation (yyyy-mm-dd)
endDate - end date of the vacation (yyyy-mm-dd)
type - group name; like "Summerholiday", this is shown on the calendar
