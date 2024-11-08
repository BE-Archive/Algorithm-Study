import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] rect = new int[N][M];

        int max = 1;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                rect[i][j] = input.charAt(j);
            }
        }

        for (int k = 1; k < Math.min(N, M); k++) {
            for (int i = 0; i < N - k; i++) {
                for (int j = 0; j < M - k; j++) {
                    if (rect[i][j] == rect[i][j + k]
                            && rect[i][j] == rect[i + k][j]
                            && rect[i][j] == rect[i + k][j + k]) {
                        max = k + 1;
                    }
                }
            }
        }
        System.out.println((int) Math.pow(max, 2));
    }
}