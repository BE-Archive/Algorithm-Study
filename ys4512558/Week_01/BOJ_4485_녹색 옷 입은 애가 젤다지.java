import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int cnt = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0){
                break;
            }
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append("Problem ").append(cnt++).append(": ")
                            .append(bfs(N, map)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    
    //풀이 2 (1번 풀고 다른 사람 풀이 보고 생각나서 했음 첨부터 내 머리속에서 나온거 아님 ㅠㅠ)
    private static long bfs_priority(int N, int[][] map) {
        class Point implements Comparable<Point>{
            int row;
            int col;
            int cost;

            public Point(int row, int col, int cost) {
                this.row = row;
                this.col = col;
                this.cost = cost;
            }

            @Override
            public int compareTo(Point p) {
                return cost - p.cost; //작은게 우선순위 높게
            }
        }

        int[] dy = {0, -1, 0, 1};
        int[] dx = {-1, 0, 1, 0};

        boolean[][] isVisit = new boolean[N][N];

        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.add(new Point(0,0,map[0][0]));
        isVisit[0][0] = true;

        while (!queue.isEmpty()){
            Point point = queue.poll();
            int row = point.row;
            int col = point.col;
            int cost = point.cost;

            for (int i = 0; i < 4; i++) {
                int y = row + dy[i];
                int x = col + dx[i];

                if(y == N - 1 && x == N - 1){
                    return cost + map[y][x];
                }

                if ((y < 0 || y >= N || x < 0 || x >= N) || isVisit[y][x]) {
                    continue;
                }

                isVisit[y][x] = true;
                queue.offer(new Point(y, x, map[y][x] + cost));
            }
        }
        return 0;
    }

    //풀이 1
    private static long bfs(int N, int[][] map) {
        class Point{
            int row;
            int col;
            int cost;

            public Point(int row, int col, int cost) {
                this.row = row;
                this.col = col;
                this.cost = cost;
            }
        }

        int[] dy = {0, -1, 0, 1};
        int[] dx = {-1, 0, 1, 0};

        boolean[][] isVisit = new boolean[N][N];
        int[][] costs = new int[N][N];

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0,0,map[0][0]));
        isVisit[0][0] = true;
        costs[0][0] = map[0][0];

        while (!queue.isEmpty()){
            Point point = queue.poll();
            int row = point.row;
            int col = point.col;
            int cost = point.cost;

            for (int i = 0; i < 4; i++) {
                int y = row + dy[i];
                int x = col + dx[i];

                if(y < 0 || y >= N || x < 0 || x >= N){
                    continue;
                }

                //아직 방문하지 않았다면 해당 좌표 + 이전 비용 + 현재 비용
                if(!isVisit[y][x]){
                    queue.add(new Point(y, x, cost + map[y][x]));
                    costs[y][x] = cost + map[y][x];
                    isVisit[y][x] = true;
                }
                //이미 방문했지만 비용감소가 가능한 경우
                if(isVisit[y][x] && costs[y][x] > (cost + map[y][x])){
                    queue.add(new Point(y, x, cost + map[y][x]));
                    costs[y][x] = cost + map[y][x];
                }
            }
        }
        return costs[N-1][N-1];
    }
}