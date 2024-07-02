import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2638 {
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int N, M, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) count++;
            }
        }

        int time = 0;

        Queue<Pair> queue = new ArrayDeque<>();
        boolean[][] isv = new boolean[N][M];
        
        //치즈가 아닌 공기에서 수행
        queue.offer(new Pair(0, 0));
        isv[0][0] = true;
        int[][] counts = new int[N][M];

        while (count != 0) {
            queue = bfs(queue, isv, counts);
            time++;
        }
        System.out.println(time);
    }

    public static Queue<Pair> bfs(Queue<Pair> queue, boolean[][] isv, int[][] counts) {
        Queue<Pair> meltingCheese = new ArrayDeque<>();

        while (!queue.isEmpty()) {
            Pair air = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = air.x + dx[i];
                int ny = air.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || isv[nx][ny]) continue;
                if(map[nx][ny] == 1 && ++counts[nx][ny] == 2) meltingCheese.offer(new Pair(nx, ny));
                if(map[nx][ny] == 0){
                    isv[nx][ny] = true;
                    queue.offer(new Pair(nx, ny));
                }
            }
        }
        //녹인 치즈 위치만 다음 탐색에서 사용 (이미 다른 공기는 체크한 상태이므로)
        return melt(meltingCheese, isv);
    }

    public static Queue<Pair> melt(Queue<Pair> meltingCheese, boolean[][] isv) {
        Queue<Pair> nextQueue = new ArrayDeque<>();
        int size = meltingCheese.size();
        while (!meltingCheese.isEmpty()) {
            Pair cheese = meltingCheese.poll();
            //녹인 치즈위치만 탐색하기 위해 다시 큐에 넣어주는 작업
            map[cheese.x][cheese.y] = 0;
            isv[cheese.x][cheese.y] = true;
            nextQueue.offer(cheese);
        }
        count -= size;
        return nextQueue;
    }
}

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}