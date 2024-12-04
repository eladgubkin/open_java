package maman12;

public class Date {

  private int _day; // Day of the month (1–31)
  private int _month; // Month of the year (1–12)
  private int _year; // Year (1000–9999)

  // Default date to fall back on for invalid inputs
  private static final int DEFAULT_DAY = 1;
  private static final int DEFAULT_MONTH = 1;
  private static final int DEFAULT_YEAR = 2024;

  /**
   * Constructor 1: Creates a Date object with the specified day, month, and year.
   * If the provided date is invalid, defaults to January 1, 2024.
   */
  public Date(int day, int month, int year) {
    if (isValidDate(day, month, year)) {
      this._day = day;
      this._month = month;
      this._year = year;
    } else {
      this._day = DEFAULT_DAY;
      this._month = DEFAULT_MONTH;
      this._year = DEFAULT_YEAR;
    }
  }

  /**
   * Constructor 2: Creates a Date object with the default date January 1,
   * 2024.
   */
  public Date() {
    this(DEFAULT_DAY, DEFAULT_MONTH, DEFAULT_YEAR);
  }

  /**
   * Constructor 3: Creates a new Date object by copying another Date object.
   */
  public Date(Date other) {
    this._day = other._day;
    this._month = other._month;
    this._year = other._year;
  }

  // Getters
  public int getDay() {
    return _day;
  }

  public int getMonth() {
    return _month;
  }

  public int getYear() {
    return _year;
  }

  // Setters
  public void setDay(int dayToSet) {
    if (isValidDate(dayToSet, this._month, this._year)) {
      this._day = dayToSet;
    }
  }

  public void setMonth(int monthToSet) {
    if (isValidDate(this._day, monthToSet, this._year)) {
      this._month = monthToSet;
    }
  }

  public void setYear(int yearToSet) {
    if (isValidDate(this._day, this._month, yearToSet)) {
      this._year = yearToSet;
    }
  }

  // Checks if this date is equal to other date
  public boolean equals(Date other) {
    return this._day == other._day && this._month == other._month && this._year == other._year;
  }

  // Checks if this date is before another date.
  public boolean before(Date other) {
    if (this.equals(other)) {
      return false;
    }

    if (this._year != other._year) {
      return this._year < other._year;
    }

    if (this._month != other._month) {
      return this._month < other._month;
    }

    return this._day < other._day;
  }

  // Checks if this date is after other date, must use only the before method.
  public boolean after(Date other) {
    return !this.before(other);
  }

  // Calculates the absolute difference in days between this date and another
  // date.
  public int difference(Date other) {
    return Math.abs(calculateDate(this._day, this._month, this._year) -
        calculateDate(other._day, other._month, other._year));
  }

  // Returns a string representation of the date in dd/mm/yyyy format.
  public String toString() {
    return String.format("%02d/%02d/%04d", this._day, this._month, this._year);
  }

  // Returns a new Date object representing the day after this date.
  public Date tomorrow() {
    if (isValidDate(this._day + 1, this._month, this._year)) {
      return new Date(this._day + 1, this._month, this._year);
    } else if (isValidDate(1, this._month + 1, this._year)) {
      return new Date(1, this._month + 1, this._year);
    } else {
      return new Date(1, 1, this._year + 1);
    }
  }

  // Validates if the given day, month, and year form a valid date.
  private boolean isValidDate(int day, int month, int year) {
    if (year < 1000 || year > 9999 || month < 1 || month > 12 || day < 1) {
      return false;
    }

    int maxDays;

    if (month == 2) {
      maxDays = isLeapYear(year) ? 29 : 28; // February
    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
      maxDays = 30; // Apr, Jun, Sep, Nov
    } else {
      maxDays = 31; // Jan, Mar, May, Jul, Aug, Oct, Dec
    }

    return day <= maxDays; // Check if the day is valid for the month
  }

  // Checks if the year is a leap year
  private boolean isLeapYear(int year) {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
  }

  // Computes the day number since the beginning of the Christian counting of
  // years
  private int calculateDate(int day, int month, int year) {
    if (month < 3) {
      year--;
      month += 12;
    }

    return 365 * year + year / 4 - year / 100 + year / 400 + (month * 306 + 5) / 10 + (day - 62);
  }
}
