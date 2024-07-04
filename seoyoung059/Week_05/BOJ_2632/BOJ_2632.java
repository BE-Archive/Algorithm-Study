package seoyoung059.Week_05.BOJ_2632;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2632 {
    /*
     * 20:49 풀이 시작
     * 21:16 pass
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int want = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[m];
        int sum = 0;
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }

        int[] pizza1 = new int[want + 1];
        if (sum <= want) pizza1[sum]++;
        int tmp;
        for (int i = 0; i < m; i++) {
            tmp = 0;
            for (int j = 0; j < m - 1; j++) {
                tmp += arr[Math.floorMod(i + j, m)];
                if (tmp > want) break;
                pizza1[tmp]++;
            }
        }


        int[] arr2 = new int[n];
        sum = 0;
        for (int i = 0; i < n; i++) {
            arr2[i] = Integer.parseInt(br.readLine());
            sum += arr2[i];
        }


        int[] pizza2 = new int[want + 1];
        if (sum <= want) pizza2[sum]++;
        for (int i = 0; i < n; i++) {
            tmp = 0;
            for (int j = 0; j < n - 1; j++) {
                tmp += arr2[Math.floorMod(i + j, n)];
                if (tmp > want) break;
                pizza2[tmp]++;
            }
        }

        int ans = 0;
        for (int i = 1; i <= want - 1; i++) {
            ans += pizza1[i] * pizza2[want - i];
        }
        ans += pizza1[want] + pizza2[want];
        System.out.println(ans);

    }
}