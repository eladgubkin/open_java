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
  private int _kilos; // Always above 0
  private int _grams; // Between 0 and 999

  // Constructor 1: Initializes weight with kilos and grams
  public Weight(int kilos, int grams) {
    this._kilos = Math.max(kilos, 1); // Set to 1 if invalid
    this._grams = (grams >= 0 && grams < 1000) ? grams : 0; // Set to 0 if invalid
  }

  // Constructor 2: Initializes weight by copying another Weight object
  public Weight(Weight other) {
    this._kilos = other._kilos;
    this._grams = other._grams;
  }

  // Constructor 3: Initializes weight using total grams
  public Weight(int totalGrams) {
    if (totalGrams >= 0) {
      this._kilos = totalGrams / 1000; // Convert to kilograms
      this._grams = totalGrams % 1000; // Calculate remaining grams
      if (this._kilos < 1) {
        this._kilos = 1; // Ensure at least 1 kilogram
      }
    } else {
      this._kilos = 1;
      this._grams = 0; // Default values for invalid input
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
    int totalGrams = this._kilos * 1000 + this._grams + grams; // Calculate total grams
    if (totalGrams < 0) {
      // If the result is invalid, return a copy of the current object
      return new Weight(this._kilos * 1000 + this._grams);
    }

    return new Weight(totalGrams); // Return the new weight
  }
}
