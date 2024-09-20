import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long mul = N * M;
        long res = 1;
        while (mul > 4) {
            res *= 3;
            res %= MOD;
            mul -= 3;
        }
        res *= mul;
        res %= MOD;
        System.out.println(res);
    }
}