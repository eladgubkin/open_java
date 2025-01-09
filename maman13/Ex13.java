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

    int[] arr = { 1, 2, 3, 4, 1 };
    System.out.println(longestNearlyPal(arr)); // Expected Output: 4

    int[] arr2 = { 1, 1, 4, 10, 10, 4, 3, 10, 10 };
    System.out.println(longestNearlyPal(arr2)); // Expected Output: 6

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
    return helper(arr, 0, arr.length - 1, true);
  }

  private static int helper(int[] arr, int left, int right, boolean mismatchAllowed) {
    // Base case: When the left pointer exceeds the right pointer
    if (left > right) {
      return 0; // Return 0 when the subarray is exhausted
    }

    // Base case: If we are left with a single element
    if (left == right) {
      return 1; // A single element is trivially palindromic
    }

    // Case 1: If the current elements at both ends match
    if (arr[left] == arr[right]) {
      // Include both elements in the subsequence and continue with the rest
      return 2 + helper(arr, left + 1, right - 1, mismatchAllowed);
    }

    // Case 2: If a mismatch is allowed (only one mismatch allowed)
    if (mismatchAllowed) {
      // Option 1: Treat this as a mismatch (skip both elements)
      int case1 = helper(arr, left + 1, right - 1, false); // Mark mismatch as not allowed

      // Option 2: Skip the left element (allowing further mismatches)
      int case2 = helper(arr, left + 1, right, mismatchAllowed);

      // Option 3: Skip the right element (allowing further mismatches)
      int case3 = helper(arr, left, right - 1, mismatchAllowed);

      // Return the maximum length from these three options
      return Math.max(case1, Math.max(case2, case3));
    }

    // Case 3: If no mismatch is allowed, we can only skip one element (from either
    // left or right)
    return Math.max(
        helper(arr, left + 1, right, mismatchAllowed),
        helper(arr, left, right - 1, mismatchAllowed));
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