package Week_20.BOJ_1756;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1756 {


    static int lower_bound(int[] oven, int start, int dough) {
        int end = oven.length - 1, mid;
        while (start < end) {
            mid = (start + end) / 2;
            if (oven[mid] < dough) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int d = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] oven = new int[d + 1];
        st = new StringTokenizer(br.readLine());
        oven[d] = Integer.MAX_VALUE;
        for (int i = d - 1; i >= 0; i--) {
            oven[i] = Math.min(Integer.parseInt(st.nextToken()), oven[i + 1]);
        }


        int start = -1;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            start = lower_bound(oven, start + 1, Integer.parseInt(st.nextToken()));
        }

        System.out.println(d - start);
    }
}
