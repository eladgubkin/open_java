package maman13;

import java.util.Arrays;

public class Ex13 {

  public static void main(String[] args) {
    // final int[] arr1 = { 4, -5, -3, 1, 2, 7, 0 };
    // final int[] arr2 = { 4, -5, -3, 1, 2, 7, 9, 0 };

    // final int[] specialArr1 = specialArr(arr1, calcMedianForArr(arr1));
    // final int[] specialArr2 = specialArr(arr2, calcMedianForArr(arr2));

    // System.out.println("specialArr(arr1, med1): " +
    // Arrays.toString(specialArr1));
    // System.out.println("specialArr(arr2, med2): " +
    // Arrays.toString(specialArr2));

    // final int[] exampleArr = { 1, -3, 6, 2, 0, 15 };
    // final int[] arr1 = { 1, 1, 1, 1 };
    // final int[] arr2 = { 4, 3, 2, 1 };
    // final int[] arr3 = { 2, -2, 0, 1, 3, -1, 5 };
    // final int[] arr4 = { 14, 12, 11, 9, 8, 7 };

    // System.out.println("first(exampleArr): " + first(exampleArr));
    // System.out.println("first(arr1): " + first(arr1));
    // System.out.println("first(arr2): " + first(arr2));
    // System.out.println("first(arr3): " + first(arr3));
    // System.out.println("first(arr4): " + first(arr4));

    // int[] arr = { 1, 2, 3, 4, 1 };
    // System.out.println(longestNearlyPal(arr)); // Expected Output: 4

    int[] arr2 = { 1, 1, 4, 10, 10, 4, 3, 10, 10 };
    System.out.println(longestNearlyPal(arr2)); // Expected Output: 6

    // int[][] a = {
    // { 1, 2 },
    // { 3, 4 }
    // };

    // int[][] b = {
    // { 1, 3 },
    // { 4, 2 }
    // };

    // int[][] c = {
    // { 4, 5, 8, 2 },
    // { 3, 12, 7, 16 },
    // { 13, 1, 10, 14 },
    // { 15, 11, 9, 6 }
    // };

    // int[][] d = {
    // { 4, 5, 8, 2 },
    // { 3, 12, 16, 7 },
    // { 13, 1, 10, 14 },
    // { 15, 11, 9, 6 }
    // };

    // System.out.println(extreme(a));
    // System.out.println(extreme(b));

  }

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

  public static int longestNearlyPal(int[] arr) {
    return findLongestNearlyPal(arr, 0, arr.length - 1);
  }

  private static int findLongestNearlyPal(int[] arr, int start, int end) {
    // Base case: If the subarray has no elements or one element
    if (start > end)
      return 0;
    if (start == end)
      return 1;

    // Check if the current subarray is palindromic
    if (isPalindromic(arr, start, end)) {
      return end - start + 1;
    }

    // Check if removing any single element makes the subarray palindromic
    for (int i = start; i <= end; i++) {
      if (isPalindromic(arr, start, i - 1) && isPalindromic(arr, i + 1, end)) {
        return end - start; // Removing one element
      }
    }

    // Recursive calls: Explore reducing the subarray from both sides
    int lengthWithoutStart = findLongestNearlyPal(arr, start + 1, end);
    int lengthWithoutEnd = findLongestNearlyPal(arr, start, end - 1);

    // Return the longest result found
    return Math.max(lengthWithoutStart, lengthWithoutEnd);
  }

  private static boolean isPalindromic(int[] arr, int start, int end) {
    // Recursive check for palindromic condition
    if (start >= end)
      return true;
    if (start < 0 || end >= arr.length)
      return false;
    if (arr[start] != arr[end])
      return false;
    return isPalindromic(arr, start + 1, end - 1);
  }

  public static int extreme(int[][] mat) {
    return findExtreme(mat, 0, 0, Integer.MIN_VALUE);
  }

  private static int findExtreme(int[][] mat, int i, int j, int currentMax) {
    int n = mat.length;

    // Update the maximum value encountered so far in this path.
    currentMax = Math.max(currentMax, mat[i][j]);

    // If reached the bottom-right corner, return the maximum value for this path.
    if (i == n - 1 && j == n - 1) {
      return currentMax;
    }

    // Temporarily mark the current cell as visited by using a negative value.
    int temp = mat[i][j];
    mat[i][j] = -1;

    // Recurse to neighbors (right, left, down, up).
    int right = (j + 1 < n && mat[i][j + 1] != -1) ? findExtreme(mat, i, j + 1, currentMax) : 0;
    int left = (j - 1 >= 0 && mat[i][j - 1] != -1) ? findExtreme(mat, i, j - 1, currentMax) : 0;
    int down = (i + 1 < n && mat[i + 1][j] != -1) ? findExtreme(mat, i + 1, j, currentMax) : 0;
    int up = (i - 1 >= 0 && mat[i - 1][j] != -1) ? findExtreme(mat, i - 1, j, currentMax) : 0;

    // Restore the original value of the current cell.
    mat[i][j] = temp;

    // Return the minimum of the maximum values from all paths.
    return Math.min(Math.min(right, left), Math.min(down, up));
  }

  // DELETE BEFORE SUBMISSION
  private static int calcMedianForArr(int[] arr) {
    Arrays.sort(arr);
    final int len = arr.length;

    if (len % 2 == 0) {
      // For even length, return the second middle number
      return arr[len / 2];
    } else {
      // For odd length, return the middle number
      return arr[len / 2];
    }
  }

}