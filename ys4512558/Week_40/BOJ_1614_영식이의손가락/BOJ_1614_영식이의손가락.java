import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1614 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        long M = Long.parseLong(br.readLine());

        long result = 0;
        if (M == 0) {
            result = N - 1;
        } else if (N == 1 || N == 5){
            result = calc(M * 2, N - 1);
        } else if (M % 2 == 0) {
            result = calc(M, N - 1);
        } else {
            long base = 8 - (N - 1);
            result = calc(M - 1, base);
        }
        System.out.println(result);
    }

    public static long calc(long M, long base) {
        return (M / 2) * 8 + base;
    }
}