package edu.phystech.task3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner num = new Scanner(System.in);

        //System.out.println("Input first size");
        int n = num.nextInt();

        int[] A = new int[n];
        //System.out.println("Input buffer elements");
        for (int i = 0; i < n; i++)
        {
            A[i] = num.nextInt();
        }

        //System.out.println("Input second size");
        int m = num.nextInt();
        int[] B = new int[m];
        //System.out.println("Input buffer elements");
        for (int i = 0; i < m; i++)
        {
            B[i] = num.nextInt();
        }

        //System.out.println("Input k");
        int k = num.nextInt();

        int sum = 0;
        int res = 0;
        int j0 = 0;
        for (int i = m-1; i >= 0; i--)
        {
            for (int j = j0; j < n; j++)
            {
                sum = A[j] + B[i];
                if (sum == k)
                {
                    res++;
                }
                else if (sum > k)
                {
                    j0 = j;
                    break;
                }
            }
        }



        System.out.println(res);
    }
}

