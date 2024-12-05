package maman12;

/**
 * The Weight class represents a weight measurement in kilograms and grams.
 * It ensures that the weight is always valid, with kilos always above 0
 * and grams between 0 and 999. The class provides constructors for
 * initializing weights, methods for comparison, and a method for
 * adding grams to the weight.
 * 
 * @author (Elad Gubkin)
 * @version (07-12-2024)
 */
public class Weight {
  private static final int MIN_KILOS = 1;
  private static final int MAX_GRAMS = 999;
  private static final int MIN_GRAMS = 0;
  private static final int GRAMS_IN_A_KILO = 1000;

  private int _kilos;
  private int _grams;

  // Constructor 1: Initializes weight with kilos and grams
  public Weight(int kilos, int grams) {
    this._kilos = Math.max(kilos, MIN_KILOS); // Set to MIN_KILOS if invalid
    this._grams = (grams >= MIN_GRAMS && grams <= MAX_GRAMS) ? grams : MIN_GRAMS; // Set to MIN_GRAMS if invalid
  }

  // Constructor 2: Initializes weight by copying another Weight object
  public Weight(Weight other) {
    this._kilos = other._kilos;
    this._grams = other._grams;
  }

  // Constructor 3: Initializes weight using total grams
  public Weight(int totalGrams) {
    if (totalGrams >= MIN_GRAMS) {
      this._kilos = totalGrams / GRAMS_IN_A_KILO; // Convert to kilograms
      this._grams = totalGrams % GRAMS_IN_A_KILO; // Calculate remaining grams
      if (this._kilos < MIN_KILOS) {
        this._kilos = MIN_KILOS; // Ensure at least MIN_KILOS
      }
    } else {
      this._kilos = MIN_KILOS;
      this._grams = MIN_GRAMS; // Default values for invalid input
    }
  }

  public int getKilos() {
    return this._kilos;
  }

  public int getGrams() {
    return this._grams;
  }

  // Check if two weights are equal
  public boolean equals(Weight other) {
    return this._kilos == other._kilos && this._grams == other._grams;
  }

  // Check if this weight is lighter than another weight
  public boolean lighter(Weight other) {
    if (this.equals(other)) {
      return false; // If weights are equal, return false
    }
    if (this._kilos < other._kilos) {
      return true; // This weight is lighter
    }
    if (this._kilos == other._kilos && this._grams < other._grams) {
      return true; // This weight is lighter
    }
    return false; // This weight is not lighter
  }

  // Check if this weight is heavier than another weight (using lighter method)
  public boolean heavier(Weight other) {
    return !this.lighter(other);
  }

  // Returns a string representation in the format "kilos.grams"
  public String toString() {
    // Format grams to always show three digits
    String gramsStr = String.format("%03d", this._grams);

    // If grams are zero, return "kilos.0"
    if (gramsStr.equals("000")) {
      return this._kilos + ".0";
    }

    // Remove trailing zeros from grams and return the formatted string
    return this._kilos + "." + gramsStr.replaceAll("0+$", "");
  }

  // Adds a given number of grams and returns a new Weight object
  public Weight add(int grams) {
    int totalGrams = this._kilos * GRAMS_IN_A_KILO + this._grams + grams; // Calculate total grams
    if (totalGrams < MIN_GRAMS) {
      // If the result is invalid, return a copy of the current object
      return new Weight(this._kilos * GRAMS_IN_A_KILO + this._grams);
    }

    return new Weight(totalGrams);
  }
}
