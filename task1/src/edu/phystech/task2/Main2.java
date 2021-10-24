//package edu.phystech.task2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner num = new Scanner(System.in);
        //System.out.println("Input size of nodes");
        int n = num.nextInt();

        if (n < 3)
        {
            //System.out.println("Wrong number of nodes");
            return;
        }

        int[] x = new int[n+1];
        int[] y = new int[n+1];
        //System.out.println("Input coordinates for each node");
        for (int i = 0; i < n; i++)
        {
            x[i] = num.nextInt();
            y[i] = num.nextInt();
        }
        x[n] = x[0];
        y[n] = y[0];

        double area = 0;

        for (int i = 0; i < n; i++)
        {
            area += x[i]*y[i+1] - x[i+1]*y[i];
        }

        System.out.println(Math.abs(area/2));
    }
}

