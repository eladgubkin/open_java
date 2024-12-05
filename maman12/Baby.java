package maman12;

/**
 * Represents a Baby with attributes such as name, ID, date of birth, and
 * weights.
 * This class provides constructors to create a Baby object, methods to get and
 * set
 * the baby's attributes, and methods to perform various comparisons and checks
 * related to the baby's weight and age.
 * 
 * @author (Elad Gubkin)
 * @version (07-12-2024)
 */
public class Baby {
  private String _firstName;
  private String _lastName;
  private String _id;
  private Date _dateOfBirth;
  private Weight _birthWeight;
  private Weight _currentWeight;

  // Constructor 1: Creates a Baby object with all attributes
  public Baby(String fName, String lName, String id, int day, int month, int year, int birthWeightInGrams) {
    this._firstName = fName;
    this._lastName = lName;
    this._id = id;
    this._dateOfBirth = new Date(day, month, year);
    this._birthWeight = new Weight(birthWeightInGrams);
    this._currentWeight = new Weight(birthWeightInGrams);
  }

  // Constructor 2: Creates a new Baby object by copying another Baby object.
  public Baby(Baby other) {
    this._firstName = other._firstName;
    this._lastName = other._lastName;
    this._id = other._id;
    this._dateOfBirth = new Date(other._dateOfBirth);
    this._birthWeight = new Weight(other._birthWeight);
    this._currentWeight = new Weight(other._currentWeight);
  }

  // Getters
  public String getFirstName() {
    return this._firstName;
  }

  public String getLastName() {
    return this._lastName;
  }

  public String getId() {
    return this._id;
  }

  public Date getDateOfBirth() {
    return new Date(this._dateOfBirth); // Return a copy to preserve immutability
  }

  public Weight getBirthWeight() {
    return new Weight(this._birthWeight); // Return a copy to preserve immutability
  }

  public Weight getCurrentWeight() {
    return new Weight(this._currentWeight); // Return a copy to preserve immutability
  }

  // Setter
  public void setCurrentWeight(Weight weightToSet) {
    if (weightToSet.getKilos() >= 1) {
      this._currentWeight = weightToSet;
    }
  }

  // Returns a string representation of the Baby object.
  public String toString() {
    return String.format("Name: %s %s\nId: %s\nDate of Birth: %s\nBirth Weight: %s\nCurrent Weight: %s\n",
        this._firstName, this._lastName, this._id, this._dateOfBirth.toString(), this._birthWeight.toString(),
        this._currentWeight.toString());
  }

  // Checks if this Baby object is equal to another Baby object.
  public boolean equals(Baby other) {
    return this._firstName.equals(other._firstName) &&
        this._lastName.equals(other._lastName) &&
        this._id.equals(other._id) &&
        this._dateOfBirth.equals(other._dateOfBirth);
  }

  // Checks if this baby is a twin of another baby.
  public boolean areTwins(Baby other) {
    boolean sameLastName = this._lastName.equals(other._lastName);
    boolean differentFirstName = !this._firstName.equals(other._firstName);
    boolean differentId = !this._id.equals(other._id);
    boolean sameBirthDate = this._dateOfBirth.equals(other._dateOfBirth);
    boolean oneDayApart = this._dateOfBirth.difference(other._dateOfBirth) == 1;

    return sameLastName && differentFirstName && differentId && (sameBirthDate || oneDayApart);
  }

  // Compares the birth weights of two babies and returns the heavier baby.
  public boolean heavier(Baby other) {
    return this._currentWeight.heavier(other._currentWeight);
  }

  // Adds grams to the current weight if the new weight is at least 1 kg.
  public void updateCurrentWeight(int grams) {
    Weight newWeight = this._currentWeight.add(grams);
    if (newWeight.getKilos() >= 1) {
      this._currentWeight = newWeight;
    }
  }

  // Checks if this baby is older than another.
  public boolean older(Baby other) {
    return this._dateOfBirth.before(other._dateOfBirth);
  }

  /**
   * Checks if the current weight of the baby is within the expected range
   * based on the baby's age in days.
   *
   * Returns:
   * 1 if the input is invalid (less than 1 or greater than 365),
   * 2 if the current weight is below the expected weight,
   * 3 if the current weight is within the expected range.
   */
  public int isWeightInValidRange(int numOfDays) {
    final double INITIAL_WEIGHT_LOSS_PERCENTAGE = 0.10;
    final int WEIGHT_LOSS_FIRST_WEEK_DAYS = 7;
    final double WEIGHT_LOSS_DIVISOR = 7.0;
    final int WEIGHT_GAIN_DAY_8_TO_60 = 30; // grams per day from 8 to 60 days
    final int WEIGHT_GAIN_DAY_61_TO_120 = 25; // grams per day from 61 to 120 days
    final int WEIGHT_GAIN_DAY_121_TO_240 = 16; // grams per day from 121 to 240 days
    final int WEIGHT_GAIN_DAY_241_AND_BEYOND = 8; // grams per day from 241 days and beyond
    final int MAX_DAYS_FIRST_PERIOD = 60;
    final int MAX_DAYS_SECOND_PERIOD = 120;
    final int MAX_DAYS_THIRD_PERIOD = 240;
    final int GRAMS_IN_A_KILO = 1000;

    if (numOfDays < 1 || numOfDays > 365) {
      return 1;
    }

    double expectedWeight = this._birthWeight.getKilos() * GRAMS_IN_A_KILO + this._birthWeight.getGrams();
    double currentWeightInGrams = this._currentWeight.getKilos() * GRAMS_IN_A_KILO + this._currentWeight.getGrams();

    // Weight loss in the first week
    if (numOfDays <= WEIGHT_LOSS_FIRST_WEEK_DAYS) {
      expectedWeight -= expectedWeight * INITIAL_WEIGHT_LOSS_PERCENTAGE * (numOfDays / WEIGHT_LOSS_DIVISOR);
    } else {
      expectedWeight -= expectedWeight * INITIAL_WEIGHT_LOSS_PERCENTAGE; // Full 10% loss for the first week
      numOfDays -= WEIGHT_LOSS_FIRST_WEEK_DAYS; // Remaining days after the first week

      // Weight gain from day 8 to 60 (inclusive)
      if (numOfDays > 0) {
        int daysToGain = Math.min(numOfDays, MAX_DAYS_FIRST_PERIOD);
        expectedWeight += daysToGain * WEIGHT_GAIN_DAY_8_TO_60; // 30 grams per day
        numOfDays -= daysToGain; // Remaining days after 60
      }

      // Weight gain from day 61 to 120 (inclusive)
      if (numOfDays > 0) {
        int daysToGain = Math.min(numOfDays, MAX_DAYS_SECOND_PERIOD);
        expectedWeight += daysToGain * WEIGHT_GAIN_DAY_61_TO_120; // 25 grams per day
        numOfDays -= daysToGain; // Remaining days after 120
      }

      // Weight gain from day 121 to 240 (inclusive)
      if (numOfDays > 0) {
        int daysToGain = Math.min(numOfDays, MAX_DAYS_THIRD_PERIOD);
        expectedWeight += daysToGain * WEIGHT_GAIN_DAY_121_TO_240; // 16 grams per day
        numOfDays -= daysToGain; // Remaining days after 240
      }

      // Weight gain from day 241 and beyond
      if (numOfDays > 0) {
        expectedWeight += numOfDays * WEIGHT_GAIN_DAY_241_AND_BEYOND; // 8 grams per day
      }
    }

    // Ensure expected weight does not go negative
    if (expectedWeight < 0) {
      expectedWeight = 0;
    }

    // Check if the current weight is below the expected weight
    if (currentWeightInGrams < expectedWeight) {
      return 2;
    }

    return 3;
  }
}
