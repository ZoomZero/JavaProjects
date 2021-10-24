//package edu.phystech.task1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner num = new Scanner(System.in);
        //System.out.println("Input size of buffers");
        int n = num.nextInt();

        int[] A = new int[n];
        int[] B = new int[n];
        //System.out.println("Input buffer");
        for (int i = 0; i < n; i++)
        {
            A[i] = num.nextInt();
        }

        for (int j = 0; j < n; j++)
        {
            B[j] = num.nextInt();
        }

        int i0 = 0;
        int j0 = 0;
        int max_A = A[0];
        int max_B = B[0];
        for (int i = 1; i < n; i++)
        {
            if (B[i] > max_B)
            {
                max_B = B[i];
                j0 = i;
            }
        }

        for (int i = 1; i <= j0; i++)
        {
            if (A[i] > max_A)
            {
                max_A = A[i];
                i0 = i;
            }
        }

        int sum = max_A + max_B;
        for (int i = j0; i < n; i++)
        {
            if (A[i] > max_A)
            {
                for (int j = i; j < n; j++)
                {
                    if (A[i] + B[j] > sum)
                    {
                        i0 = i;
                        j0 = j;
                        sum = A[i] + B[j];
                    }
                }
            }
        }

        System.out.println(i0 + " " + j0);
    }
}

