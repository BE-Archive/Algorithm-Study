import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map;
    static int[] dx = {0, -1, 0};
    static int[] dy = {-1, 0, 1};
    static int N, M, D, count, max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) count++;
            }
        }
        int[] arr = new int[M];
        for (int i = 1; i <= 3; i++) {
            arr[M - i] = 1;
        }
        do {
            int[][] copy = copyMap(map);
            simulation(copy, arr);
        } while (nextPerm(arr));
        System.out.println(max);
    }
    private static void simulation(int[][] copy, int[] arr) {
        int cnt = 0;
        int row = N;
        Queue<Pair> queue = new ArrayDeque<>();
        List<Pair> next = new ArrayList<>();
        int idx = 0;
        for (int i = 0; i < M; i++) {
            if (arr[i] == 1) {
                next.add(new Pair(idx, N, i, 0));
                queue.offer(next.get(idx++));
            }
        }

        while (row != 0) {
            Set<Pair> attacks = new HashSet<>();
            boolean[][][] isv = new boolean[3][N][M];
            boolean[] kill = new boolean[3];
            while (!queue.isEmpty()) {
                Pair p = queue.poll();

                if(kill[p.idx]) continue;
                for (int i = 0; i < 3; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= row || ny >= M || isv[p.idx][nx][ny]) continue;
                    isv[p.idx][nx][ny] = true;
                    if (copy[nx][ny] == 1) {
                        if(attacks.add(new Pair(p.idx, nx, ny, 0))) {
                            cnt++;
                        }
                        kill[p.idx] = true;
                        break;
                    }
                    if (p.breadth == D - 1) {
                        continue;
                    }
                    queue.offer(new Pair(p.idx, nx, ny, p.breadth + 1));
                }
            }
            for (int i = 0; i < 3; i++) {
                Pair pair = next.get(i);
                pair.x--;
                queue.offer(pair);
            }
            for (Pair attack : attacks) {
                copy[attack.x][attack.y] = 0;
            }
            row--;
        }
        max = Math.max(max, cnt);
    }

    private static int[][] copyMap(int[][] map) {
        int[][] copy = new int[N + 1][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    private static boolean nextPerm(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) i--;
        if(i == 0) return false;
        int dest = i - 1;

        int j = arr.length - 1;
        while (j > i && arr[dest] >= arr[j]) j--;
        swap(arr, j, dest);

        int k = arr.length - 1;
        while (i < k) swap(arr, i++, k--);
        return true;
    }

    private static void swap(int[] arr, int j, int dest) {
        int temp = arr[j];
        arr[j] = arr[dest];
        arr[dest] = temp;
    }
}

class Pair{
    int idx, x, y, breadth;

    public Pair(int idx, int x, int y, int breadth) {
        this.idx = idx;
        this.x = x;
        this.y = y;
        this.breadth = breadth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return x == pair.x && y == pair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}