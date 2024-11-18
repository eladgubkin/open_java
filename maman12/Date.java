package maman12;

public class Date {

  private int day; // Day of the month (1–31)
  private int month; // Month of the year (1–12)
  private int year; // Year (1000–9999)

  // Default date to fall back on for invalid inputs
  private static final int DEFAULT_DAY = 1;
  private static final int DEFAULT_MONTH = 1;
  private static final int DEFAULT_YEAR = 2024;

  /**
   * Constructor: Creates a Date object with the specified day, month, and year.
   * If the provided date is invalid, defaults to January 1, 2024.
   */
  public Date(int day, int month, int year) {
    if (isValidDate(day, month, year)) {
      this.day = day;
      this.month = month;
      this.year = year;
    } else {
      this.day = DEFAULT_DAY;
      this.month = DEFAULT_MONTH;
      this.year = DEFAULT_YEAR;
    }
  }

  /**
   * Default Constructor: Creates a Date object with the default date January 1,
   * 2024.
   */
  public Date() {
    this(DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR);
  }

  /**
   * Copy Constructor: Creates a new Date object by copying another Date object.
   */
  public Date(Date other) {
    this.day = other.day;
    this.month = other.month;
    this.year = other.year;
  }

  // Getters
  public int getDay() {
    return day;
  }

  public int getMonth() {
    return month;
  }

  public int getYear() {
    return year;
  }

  // Setters
  public void setDay(int dayToSet) {
    if (isValidDate(dayToSet, this.month, this.year)) {
      this.day = dayToSet;
    }
  }

  public void setMonth(int monthToSet) {
    if (isValidDate(this.day, monthToSet, this.year)) {
      this.month = monthToSet;
    }
  }

  public void setYear(int yearToSet) {
    if (isValidDate(this.day, this.month, yearToSet)) {
      this.year = yearToSet;
    }
  }

  /**
   * Checks if this date is equal to another date.
   */
  public boolean equals(Date other) {
    return this.day == other.day && this.month == other.month && this.year == other.year;
  }

  /**
   * Checks if this date is before another date.
   */
  public boolean before(Date other) {
    if (this.year != other.year)
      return this.year < other.year;
    if (this.month != other.month)
      return this.month < other.month;
    return this.day < other.day;
  }

  /**
   * Checks if this date is after another date. Uses the before method.
   */
  public boolean after(Date other) {
    return !this.before(other) && !this.equals(other);
  }

  /**
   * Calculates the absolute difference in days between this date and another
   * date.
   */
  public int difference(Date other) {
    return Math.abs(calculateDate(this.day, this.month, this.year) -
        calculateDate(other.day, other.month, other.year));
  }

  /**
   * Returns a string representation of the date in yyyy/mm/dd format.
   */
  public String toString() {
    return String.format("%04d/%02d/%02d", this.year, this.month, this.day);
  }

  /**
   * Returns a new Date object representing the day after this date.
   */
  public Date tomorrow() {
    if (isValidDate(this.day + 1, this.month, this.year)) {
      return new Date(this.day + 1, this.month, this.year);
    } else if (isValidDate(1, this.month + 1, this.year)) {
      return new Date(1, this.month + 1, this.year);
    } else {
      return new Date(1, 1, this.year + 1);
    }
  }

  // Private helper methods

  /**
   * Validates if the given day, month, and year form a valid date.
   */
  private boolean isValidDate(int day, int month, int year) {
    if (year < 1000 || year > 9999 || month < 1 || month > 12 || day < 1) {
      return false;
    }

    int[] daysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    if (month == 2 && isLeapYear(year)) {
      return day <= 29;
    }

    return day <= daysInMonth[month - 1];
  }

  /**
   * Checks if a given year is a leap year.
   */
  private boolean isLeapYear(int year) {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
  }

  /**
   * Computes the number of days since the beginning of the Gregorian calendar.
   */
  private int calculateDate(int day, int month, int year) {
    if (month < 3) {
      year--;
      month += 12;
    }
    return 365 * year + year / 4 - year / 100 + year / 400 + (month * 306 + 5) / 10 + (day - 62);
  }
}
