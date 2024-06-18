package sjhlko.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15501_부당한퍼즐 {
    //https://www.acmicpc.net/problem/15501
    //부당한 퍼즐
    static final String TRUE = "good puzzle", FALSE = "bad puzzle";
    static int[] arr, ans;
    static int N;

    static boolean solution() {
        int prefix = -1;
        boolean ret = false;
        for (int i = 0; i < N; i++) {
            if (ans[0] == arr[i]) {
                prefix = i;
                break;
            }
        }
        for (int i = 0; i < N; i++) {
            if (ans[i] != arr[(i + prefix) % N]) {
                ret = false;
                break;
            }
            ret = true;
        }
        if (ret) return ret;
        for (int i = 0; i < N; i++) {
            if (ans[i] != arr[(N - i + prefix) % N]) {
                ret = false;
                break;
            }
            ret = true;
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        ans = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            ans[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution() ? TRUE : FALSE);
    }
}
