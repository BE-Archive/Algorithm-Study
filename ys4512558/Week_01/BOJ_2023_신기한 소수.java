import java.io.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BOJ2023.prob2023();
    }
}

class BOJ2023 {
    static  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    public static void prob2023() throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] primes = new int[]{2, 3, 5, 7};
        for (int i = 0; i < primes.length; i++) {
            interestPrime(N, 1, String.valueOf(primes[i]));
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void interestPrime(int N, int depth, String pre){
        if (!checkPrime(pre)) {
            return;
        }
        if (N == depth) {
            sb.append(pre).append("\n");
            return;
        }

        for (int i = 0; i <= 9; i++) {
            interestPrime(N, depth+1, (pre+i));
        }
    }
    private static boolean checkPrime(String cur){
        int val = Integer.valueOf(cur);
        for (int i = 2; i * i <= val; i++) {
            if (val % i == 0) {
                return false;
            }
        }
        return true;
    }
}
