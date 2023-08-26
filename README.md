# calgen
Simple tool to create a calendar.

# Building the application from source

## Prerequisites

  * Git
  * Java 20
  * Maven

## Configuration

There is a configuration file called `settings.json` in the `config` directory.
Here is an explanation on what every property means and how to use it.

### Settings

In the root of the settings are the followng settings:

title - title that is shown on the top of the calendar.
fontName - the name of the font that is used for all the text on the calendar.
backgroundColor - color that is used for the background of the calendar (on top and at the bottom where no days are present).
normalColor - background color of a single day
weekendColor - background color to use on a weekend day
columnHeaderColor - background color on the top column displaying the month
pageSize - A4 or A3 for the size of the images
showWeekNumbers - true if you like to see the weeknumbers in the calendar displayed

### ColorSets

You can define color sets and refer to them in the vacations. So you can define on or multiple colors and in the vacation simply refer to the `set` using the `colorCode`

set - the number to refer to
color - the background color to use for the vacation

### Vacations

A vacation simply has the following properties:

startDate - starting date of the vacation (yyyy-mm-dd)
endDate - end date of the vacation (yyyy-mm-dd)
type - group name; like "Summerholiday", this is shown on the calendar
colorCode - the reference to the colorset to use so you can show different colors for certain types of vacations if needed

## Steps

	git clone git@github.com:IvoLimmen/calgen.git

	mvn clean install	

  mvn -pl app exec:java