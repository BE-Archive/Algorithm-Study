package Week_15.BOJ_1081;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1081 {

    static long sum(long num) {
        long ans = 0;
        int l = Long.toString(num).length() - 1;
        long tmp = (long) Math.pow(10, l);

        long lTmp = 0;

        while (l >= 0) {
            lTmp = num / tmp;
            ans += lTmp * (lTmp - 1) / 2 * tmp;
            ans += (num % tmp + 1) * lTmp;
            ans += lTmp * 45 * tmp / 10 * l;

            l--;
            num %= tmp;
            tmp /= 10;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long l = Long.parseLong(st.nextToken());
        long u = Long.parseLong(st.nextToken());

        System.out.println(sum(u) - sum(l - 1));
    }
}
