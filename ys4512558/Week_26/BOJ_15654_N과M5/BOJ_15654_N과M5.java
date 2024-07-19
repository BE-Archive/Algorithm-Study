import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15654 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static int[] numbers;
    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        isSelected = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        numbers = new int[M];
        solve(N, M, 0);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve(int n, int m, int depth) {
        if (m == depth) {
            for (int i = 0; i < m; i++) {
                sb.append(numbers[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0; i < n; i++) {
            numbers[depth] = arr[i];
            if (isSelected[i]) {
                continue;
            }
            isSelected[i] = true;
            solve(n, m, depth + 1);
            isSelected[i] = false;
        }
    }
}