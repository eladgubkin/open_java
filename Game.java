import java.util.Scanner;

/**
 * A simple Rock, Paper, Scissors game that allows two players to compete.
 * Players enter their choice as a single character:
 * 'R' for Rock, 'P' for Paper, or 'S' for Scissors.
 * 
 * Game Rules:
 * - Rock crushes Scissors
 * - Paper covers Rock
 * - Scissors cuts Paper
 * 
 * Input validation is not performed, so players must enter valid characters.
 * 
 * @author (Elad Gubkin)
 * @version (16-11-2024)
 */

public class Game {
  public static void main(String[] args) {
    final Scanner scan = new Scanner(System.in);

    System.out.println("Enter first player's object:");
    final char player1 = scan.next().charAt(0);

    System.out.println("Enter second player's object:");
    final char player2 = scan.next().charAt(0);

    String output = "Game ends with a tie.";

    // Check winning combinations for both players
    // R beats S, P beats R, S beats P
    if ((player1 == 'R' && player2 == 'S') ||
        (player1 == 'P' && player2 == 'R') ||
        (player1 == 'S' && player2 == 'P')) {
      output = "Player 1 wins.";
    } else if ((player2 == 'R' && player1 == 'S') ||
        (player2 == 'P' && player1 == 'R') ||
        (player2 == 'S' && player1 == 'P')) {
      output = "Player 2 wins.";
    }

    System.out.println(output);

    // Close the scanner
    scan.close();

  }
}