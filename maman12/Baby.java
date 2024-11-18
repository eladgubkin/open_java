package maman12;

public class Baby {
  private String name; // Baby's name
  private Date birthDate; // Baby's birth date
  private Weight birthWeight; // Baby's birth weight

  /**
   * Constructor: Creates a Baby object with the given name, birth date, and birth
   * weight.
   */
  public Baby(String name, Date birthDate, Weight birthWeight) {
    this.name = (name != null && !name.isEmpty()) ? name : "Unknown"; // Default name if invalid
    this.birthDate = (birthDate != null) ? new Date(birthDate) : new Date(); // Use copy constructor or default date
    this.birthWeight = (birthWeight != null) ? new Weight(birthWeight) : new Weight(1, 0); // Default weight is 1 kg
  }

  /**
   * Copy Constructor: Creates a new Baby object by copying another Baby object.
   */
  public Baby(Baby other) {

    this.name = other.name;
    this.birthDate = new Date(other.birthDate);
    this.birthWeight = new Weight(other.birthWeight);
  }

  // Getters
  public String getName() {
    return name;
  }

  public Date getBirthDate() {
    return new Date(birthDate); // Return a copy to preserve immutability
  }

  public Weight getBirthWeight() {
    return new Weight(birthWeight); // Return a copy to preserve immutability
  }

  // Setters
  public void setName(String name) {
    if (name != null && !name.isEmpty()) {
      this.name = name;
    }
  }

  public void setBirthDate(Date birthDate) {
    if (birthDate != null) {
      this.birthDate = new Date(birthDate); // Use copy constructor
    }
  }

  public void setBirthWeight(Weight birthWeight) {
    if (birthWeight != null) {
      this.birthWeight = new Weight(birthWeight); // Use copy constructor
    }
  }

  /**
   * Returns a string representation of the Baby object.
   */
  public String toString() {
    return String.format("Name: %s, Birth Date: %s, Birth Weight: %s",
        name, birthDate.toString(), birthWeight.toString());
  }

  /**
   * Checks if this Baby object is equal to another Baby object.
   */
  public boolean equals(Baby other) {
    return this.name.equals(other.name) &&
        this.birthDate.equals(other.birthDate) &&
        this.birthWeight.equals(other.birthWeight);
  }

  /**
   * Calculates the age of the baby in days from its birth date to a given date.
   */
  public int calculateAgeInDays(Date currentDate) {
    return birthDate.difference(currentDate);
  }

  /**
   * Compares the birth weights of two babies and returns the heavier baby.
   */
  public Baby heavierBaby(Baby other) {
    return this.birthWeight.heavier(other.birthWeight) ? this : other;
  }
}
