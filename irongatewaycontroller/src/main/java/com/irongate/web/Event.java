package com.irongate.web;

import java.math.BigInteger;

public class Event {
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        //System.out.println(noOfWays(1000000000));
        //System.out.println(binomialCoeff(1000000000,5));
        int[] a = new int[6];

        a[0] = 1;
        a[1] = 2;
        a[2] = 7;
        a[3] = 2;
        a[4] = 3;
        a[5] = 1;
        System.out.println(harmonicTriplets(a, 5 - 1));
    }

    public static int greateIndex(int[] a, int k, int y) {

        a[k] = -1;
        for (int i = y; i < a.length; i++) {
            if (i >= y && a[i] == -1) {
                return i;
            }
        }
        return -1;
    }

    public static long noOfWays(int n) {
        int result = 0;
        int power = 1;
        if (n == 2) return 2;
        int modulo = 1000000007;

        for (int i = 1; i < n; i++) {
            power = (power * 2) % modulo;
        }

        return power - 1;

    }

    static int binomialCoeff(int n, int k) {
        int C[][] = new int[n + 1][k + 1];
        int i, j;

        // Calculate  value of Binomial Coefficient in bottom up manner
        for (i = 0; i <= n; i++) {
            for (j = 0; j <= Math.min(i, k); j++) {
                // Base Cases
                if (j == 0 || j == i)
                    C[i][j] = 1;

                    // Calculate value using previosly stored values
                else
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }

        return C[n][k];
    }

    public static long harmonicTriplets(int[] a, int j) {

        int maxLeft = findMax(a, 0, j - 1, a[j]);
        if (maxLeft < 0) return 0;
        int maxRight = findMax(a, j + 1, a.length - 1, a[j]);
        if (maxRight < 0) return 0;
        return maxLeft * a[j] * maxRight;

    }


    private static int findMax(int[] a, int start, int end, int val) {
        int max = -1;
        for (int i = start; i <= end; i++) {
            if (val >= a[i])
                max = Math.max(max, a[i]);
        }
        return max;
    }
}
