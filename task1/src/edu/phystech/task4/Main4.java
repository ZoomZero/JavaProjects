package edu.phystech.task4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner num = new Scanner(System.in);

        //System.out.println("Input number of people");
        int n = num.nextInt();
        //System.out.println("n = " + n);

        //System.out.println("Input k");
        int k = num.nextInt();
        //System.out.println("k = " + k);

        int surv = 0;
        boolean[] people = new boolean[n];
        for(int i = 0; i < n; i++)
        {
            people[i] = true;
        }

        for(int i = 0; i < n; i++)
        {
            int skipped = 1;
            while (skipped < k || !people[surv])
            {
                skipped = skipped + boolToInt(people[surv]);
                surv = (surv + 1) % n;
            }

            people[surv] = false;
        }


        System.out.println(surv + 1);
    }

    public  static  int boolToInt(Boolean b) {
        return b.compareTo(false);
    }
}

