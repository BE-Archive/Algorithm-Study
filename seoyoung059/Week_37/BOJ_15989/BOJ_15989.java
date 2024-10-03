package Week_37.BOJ_15989;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_15989 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        int max = 0;
        int[] tc = new int[t];
        for (int i = 0; i < t; i++) {
            tc[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, tc[i]);
        }

        int[][] arr = new int[4][max+1];

        arr[1][1] = 1;
        arr[2][2] = 1; arr[1][2] = 1;
        arr[3][3] = 1; arr[2][3] = 1; arr[1][3] = 1;

        for (int i = 4; i <= max; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int k = 1; k <= j; k++) {
                    arr[j][i] += arr[k][i-j];
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            sb.append(arr[1][tc[i]]+arr[2][tc[i]]+arr[3][tc[i]]).append("\n");
        }
        System.out.println(sb);
    }
}
