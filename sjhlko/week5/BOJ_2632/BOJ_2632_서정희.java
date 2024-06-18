package sjhlko.week5.BOJ_2632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_2632_서정희 {
    //https://www.acmicpc.net/problem/2632
    //피자판매
    static int buy, m, n;
    static int[] a, b;
    static Map<Integer, Integer> madeByA = new HashMap<>(), madeByB = new HashMap<>();

    static void makeSum(int[] arr, Map<Integer, Integer> map) {
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            int sum = arr[i];
            for (int j = 1; j < arr.length; j++) {
                map.putIfAbsent(sum, 0);
                map.put(sum, map.get(sum) + 1);
                sum += arr[(i + j) % arr.length];
            }
            if (i == 0)
                map.put(sum, 1);
        }
    }

    static long solution() {
        long ans = 0;
        makeSum(a, madeByA);
        makeSum(b, madeByB);
        for (int i = 0; i <= buy; i++) {
            if (!madeByA.containsKey(i) || !madeByB.containsKey(buy - i)) continue;
            ans += (long) madeByA.get(i) * madeByB.get(buy - i);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        buy = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        a = new int[m];
        b = new int[n];
        for (int i = 0; i < m; i++) {
            a[i] = Integer.parseInt(bf.readLine());
        }
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(bf.readLine());
        }
        System.out.println(solution());
    }
}
