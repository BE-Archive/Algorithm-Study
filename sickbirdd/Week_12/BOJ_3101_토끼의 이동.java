import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long N;
    static long calculate(long row, long col) {
        if((row == 0L && col == 0L) || (row == N - 1L && col == N - 1L)) return (row + 1L) * (col + 1L);
        long diag = row + col;
        if(diag < N) {
            long sum = diag * (diag + 1L) / 2L;
            return (diag & 1L) == 0L ? sum + col + 1L : sum + row + 1L;
        }
        else if(diag == N) {
            long sum = diag * (diag + 1L) / 2L;
            return (diag & 1L) == 0L ? sum + col : sum + row;
        }
        else {
            long sum = N * N - (2 * N - diag - 1) * (2 * N - diag) / 2L;
            return (diag & 1L) == 0L ? sum + col + (N - diag) : sum + row + (N - diag);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[] s = br.readLine().toCharArray();
        long ans = 1L, sr = 0L, sc = 0L;
        for(int i = 0; i < K; i++) {
            if(s[i] == 'D') sr++;
            else if(s[i] == 'U') sr--;
            else if(s[i] == 'L') sc--;
            else sc++;
            long cal = calculate(sr, sc);
            ans += cal;
        }
        System.out.println(ans);
    }
}