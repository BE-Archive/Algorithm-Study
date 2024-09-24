import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(br.readLine());
        
        int[] knapsack = new int[101];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int tired = Integer.parseInt(st1.nextToken());
            int joy = Integer.parseInt(st2.nextToken());

            for (int j = 100; j > tired; j--) {
                knapsack[j] = Math.max(knapsack[j], knapsack[j - tired] + joy);
            }

        }
        System.out.println(knapsack[100]);
    }

}