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

    final int[] exampleArr = { 1, -3, 6, 2, 0, 15 };
    final int[] arr1 = { 1, 1, 1, 1 };
    final int[] arr2 = { 4, 3, 2, 1 };
    final int[] arr3 = { 2, -2, 0, 1, 3, -1, 5 };
    final int[] arr4 = { 14, 12, 11, 9, 8, 7 };

    System.out.println("first(exampleArr): " + first(exampleArr));
    System.out.println("first(arr1): " + first(arr1));
    System.out.println("first(arr2): " + first(arr2));
    System.out.println("first(arr3): " + first(arr3));
    System.out.println("first(arr4): " + first(arr4));

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