package sjhlko.week4.BOJ_1722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1722_순열의순서 {
    //https://www.acmicpc.net/problem/1722
    //순열의 순서
    static int N;
    static int[] arr;
    static boolean[] isUsed;
    static long[] f;
    static long k;

    static void initF() {
        f = new long[N + 1];
        f[1] = f[0] = 1L;
        for (int i = 2; i < N + 1; i++) {
            f[i] = f[i - 1] * i;
        }
    }

    static int getSmallerCount(int n) {
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (isUsed[i]) continue;
            count++;
        }
        return count;
    }

    static int getIndex(int target) {
        int count = 0;
        for (int i = 1; i < N + 1; i++) {
            if (isUsed[i]) continue;
            if (count++ == target) return i;
        }
        return -1;
    }

    static void solution1() {
        StringBuilder ans = new StringBuilder();
        k--;
        for (int i = N - 1; i >= 0; i--) {
            int r = (int) (k / f[i]);
            int index = getIndex(r);
            ans.append(index).append(" ");
            isUsed[index] = true;
            k %= f[i];
        }
        System.out.println(ans);
    }

    static long solution2() {
        long ans = 1L;
        for (int i = 0; i < N; i++) {
            int indexCount = getSmallerCount(arr[i]);
            ans += (long) indexCount * f[N - i - 1];
            isUsed[arr[i]] = true;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        isUsed = new boolean[N + 1];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        initF();
        int type = Integer.parseInt(st.nextToken());
        switch (type) {
            case 1:
                k = Long.parseLong(st.nextToken());
                solution1();
                break;
            case 2:
                arr = new int[N];
                for (int i = 0; i < N; i++) {
                    arr[i] = Integer.parseInt(st.nextToken());
                }
                System.out.println(solution2());
                break;
        }
    }

}
