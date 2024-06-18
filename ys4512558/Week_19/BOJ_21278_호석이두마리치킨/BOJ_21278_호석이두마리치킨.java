import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] dist;
    static int N, M;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;

        dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
            dist[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            dist[v][u] = 1;
            dist[u][v] = 1;
        }

        floydWarshall();

        int[] comb = new int[N];
        comb[N - 1] = 1;
        comb[N - 2] = 1;

        int[] result = null;
        do {
            int[] numbers = new int[2];
            int idx = 0;
            for (int i = 0; i < comb.length; i++) {
                if(comb[i] == 0) continue;
                numbers[idx++] = i + 1;
            }
            result = calcMin(numbers, result);
        } while (nextPerm(comb));
        StringBuilder sb = new StringBuilder();
        sb.append(result[0]).append(" ");
        sb.append(result[1]).append(" ");
        sb.append(result[2]);
        System.out.println(sb);
    }

    private static int[] calcMin(int[] numbers, int[] result) {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += Math.min(dist[numbers[0]][i], dist[numbers[1]][i]);
        }
        if (min >= sum) {
            min = sum;
            return new int[]{numbers[0], numbers[1], sum * 2};
        }
        return result;
    }

    private static boolean nextPerm(int[] comb) {
        int i = comb.length - 1;
        while (i > 0 && comb[i - 1] >= comb[i]) i--;
        if(i == 0) return false;
        int dest = i - 1;

        int j = comb.length - 1;
        while (j >= i && comb[dest] >= comb[j]) j--;
        swap(dest, j, comb);

        int k = comb.length - 1;
        while (i < k) swap(i++, k--, comb);
        return true;
    }

    private static void swap(int dest, int j, int[] comb) {
        int temp = comb[dest];
        comb[dest] = comb[j];
        comb[j] = temp;
    }


    private static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}