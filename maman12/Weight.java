package maman12;

public class Weight {
  private int kilos;
  private int grams;

  // Constructor: Initializes weight with kilograms and grams
  public Weight(int kilos, int grams) {
    this.kilos = Math.max(kilos, 1); // Set to 1 if invalid
    this.grams = (grams >= 0 && grams < 1000) ? grams : 0; // Set to 0 if invalid
  }

  // Copy Constructor: Initializes weight by copying another Weight object
  public Weight(Weight other) {
    this.kilos = other.kilos;
    this.grams = other.grams;
  }

  // Constructor: Initializes weight using total grams
  public Weight(int totalGrams) {
    if (totalGrams >= 0) {
      this.kilos = totalGrams / 1000; // Convert to kilograms
      this.grams = totalGrams % 1000; // Calculate remaining grams
      if (this.kilos < 1)
        this.kilos = 1; // Ensure at least 1 kilogram
    } else {
      this.kilos = 1;
      this.grams = 0; // Default values for invalid input
    }
  }

  // Accessor for kilos
  public int getKilos() {
    return kilos;
  }

  // Accessor for grams
  public int getGrams() {
    return grams;
  }

  // Check if two weights are equal
  public boolean equals(Weight other) {
    return this.kilos == other.kilos && this.grams == other.grams;
  }

  // Check if this weight is lighter than another weight
  public boolean lighter(Weight other) {
    return this.kilos < other.kilos ||
        (this.kilos == other.kilos && this.grams < other.grams);
  }

  // Check if this weight is heavier than another weight (using lighter method)
  public boolean heavier(Weight other) {
    return !this.lighter(other) && !this.equals(other);
  }

  // Returns a string representation in the format "kilos.grams"
  public String toString() {
    String gramsStr = String.valueOf(this.grams).replaceAll("0+$", ""); // Remove trailing zeros
    if (gramsStr.isEmpty())
      gramsStr = "0"; // If empty, set to "0"
    return kilos + "." + gramsStr;
  }

  // Adds a given number of grams and returns a new Weight object
  public Weight add(int grams) {
    int totalGrams = this.kilos * 1000 + this.grams + grams; // Calculate total grams
    if (totalGrams < 0) {
      // If the result is invalid, return a copy of the current object
      return new Weight(this.kilos * 1000 + this.grams);
    }
    return new Weight(totalGrams); // Return the new weight
  }
}
