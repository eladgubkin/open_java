/**
 * Maman13
 * 
 * @author (Elad Gubkin)
 * @version (11-01-2025)
 */
public class Ex13 {
  /**
   * Constructs a special array using the median of the input array.
   * 
   * Values above the median are placed at even indices,
   * those below at odd indices, and equal values fill the next available index.
   * 
   * Time Complexity: n * O(1) = O(n)
   * Space Complexity: O(n) + O(1) = O(n)
   * 
   * @param arr the input integer array
   * @param med the median of the input array
   */
  public static int[] specialArr(int[] arr, int med) {
    int[] result = new int[arr.length];
    int evenIndex = 0;
    int oddIndex = 1;

    for (int num : arr) {
      if (num > med) {
        // Place numbers greater than the median at even indices
        result[evenIndex] = num;
        evenIndex += 2; // Increment to the next even index
      } else if (num < med) {
        // Place numbers less than the median at odd indices
        result[oddIndex] = num;
        oddIndex += 2; // Increment to the next odd index
      } else {
        // For numbers equal to the median, place them in the next available index
        if (evenIndex < arr.length) {
          result[evenIndex] = num;
          evenIndex += 2; // Move to the next even index
        } else {
          result[oddIndex] = num;
          oddIndex += 2; // Move to the next odd index
        }
      }
    }

    return result;
  }

  /**
   * Returns the smallest missing positive integer from the array.
   *
   * Time Complexity: O(n) + O(n) + O(n) = O(n)
   * Space Complexity: O(1)
   * 
   * @param arr the input array of integers
   */
  public static int first(int[] arr) {
    int n = arr.length;

    // Replace non-positive numbers and numbers greater than n with n + 1
    for (int i = 0; i < n; i++) {
      if (arr[i] <= 0 || arr[i] > n) {
        arr[i] = n + 1;
      }
    }

    // Negate the value at the index corresponding to the number
    for (int i = 0; i < n; i++) {
      int num = Math.abs(arr[i]);
      if (num <= n) {
        arr[num - 1] = -Math.abs(arr[num - 1]);
      }
    }

    // Find the first positive index, indicating the missing integer
    for (int i = 0; i < n; i++) {
      if (arr[i] > 0) {
        return i + 1;
      }
    }

    return n + 1; // All numbers from 1 to n are present
  }

  /**
   * Returns the length of the longest nearly palindrome subarray.
   * 
   * @param arr the input array of integers
   * @return the length of the longest nearly palindrome subarray
   */
  public static int longestNearlyPal(int[] arr) {
    return findLongestNearlyPal(arr, 0, arr.length - 1, false);
  }

  private static int findLongestNearlyPal(int[] arr, int start, int end, boolean removed) {
    if (start > end) {
      return 0;
    }

    if (isNearlyPalindrome(arr, start, end, removed)) {
      return end - start + 1;
    }

    int removeStart = 0, removeEnd = 0;

    if (start + 1 <= end) {
      removeStart = findLongestNearlyPal(arr, start + 1, end, removed);
    }

    if (start <= end - 1) {
      removeEnd = findLongestNearlyPal(arr, start, end - 1, removed);
    }

    return Math.max(removeStart, removeEnd);
  }

  private static boolean isNearlyPalindrome(int[] arr, int start, int end, boolean removed) {
    if (start >= end) {
      return true;
    }

    if (arr[start] == arr[end]) {
      return isNearlyPalindrome(arr, start + 1, end - 1, removed);
    } else if (!removed) {
      // Try removing one element from either side if not already removed
      return isNearlyPalindrome(arr, start + 1, end, true) || isNearlyPalindrome(arr, start, end - 1, true);
    }

    return false;

  }

  public static int extreme(int[][] mat) {
    return findExtreme(mat, 0, 0, Integer.MIN_VALUE);
  }

  private static int findExtreme(int[][] mat, int i, int j, int currentMax) {
    int n = mat.length;

    if (i < 0 || j < 0 || i >= n || j >= n) {
      return Integer.MAX_VALUE;
    }

    // Update the maximum value encountered so far in this path.
    currentMax = Math.max(currentMax, mat[i][j]);

    // If reached the bottom-right corner, return the maximum value for this path.
    if (i == n - 1 && j == n - 1) {
      return currentMax;
    }

    // temp mark the current cell as visited by using a negative value.
    int temp = mat[i][j];
    mat[i][j] = -1;

    // Recurse to neighbors (right, left, down, up).
    int right = (j + 1 < n && mat[i][j + 1] != -1) ? findExtreme(mat, i, j + 1, currentMax) : Integer.MAX_VALUE;
    int left = (j - 1 >= 0 && mat[i][j - 1] != -1) ? findExtreme(mat, i, j - 1, currentMax) : Integer.MAX_VALUE;
    int down = (i + 1 < n && mat[i + 1][j] != -1) ? findExtreme(mat, i + 1, j, currentMax) : Integer.MAX_VALUE;
    int up = (i - 1 >= 0 && mat[i - 1][j] != -1) ? findExtreme(mat, i - 1, j, currentMax) : Integer.MAX_VALUE;

    // Restore the original value of the current cell.
    mat[i][j] = temp;

    // Return the minimum of the maximum values from all paths.
    return Math.min(Math.min(right, left), Math.min(down, up));
  }

}