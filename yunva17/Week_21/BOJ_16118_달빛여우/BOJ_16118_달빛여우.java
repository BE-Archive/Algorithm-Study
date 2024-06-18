import java.io.*;
import java.util.*;

public class BOJ_16118_달빛여우 {
    static int N, M;
    static List<Edge>[] graph;
    static int[] foxDis;
    static int[][] wolfDis;

    static class Edge implements Comparable<Edge> {
        int num, weight, state;

        public Edge(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        // 늑대 상태 관리
        public Edge(int num, int weight, int state) {
            this.num = num;
            this.weight = weight;
            this.state = state;
        }


        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, d));
            graph[b].add(new Edge(a, d));

        }

        foxDis = new int[N + 1];
        wolfDis = new int[N + 1][2];

        fox();
        wolf();

        int answer = 0;
        for (int i = 2; i <= N; i++) {
            if (foxDis[i] < Math.min(wolfDis[i][0], wolfDis[i][1])) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    // 달빛 여우
    public static void fox() {
        Queue<Edge> queue = new PriorityQueue<>();

        Arrays.fill(foxDis, Integer.MAX_VALUE);

        foxDis[1] = 0; // 1에서 시작
        queue.offer(new Edge(1, foxDis[1]));

        while (!queue.isEmpty()) {
            Edge now = queue.poll();

            if (foxDis[now.num] < now.weight) continue;

            for (Edge next : graph[now.num]) {
                if (foxDis[next.num] > foxDis[now.num] + next.weight * 2) {
                    foxDis[next.num] = foxDis[now.num] + next.weight * 2;

                    queue.offer(new Edge(next.num, foxDis[next.num]));
                }
            }

        }

    }

    // 달빛 늑대
    public static void wolf() {
        Queue<Edge> queue = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            Arrays.fill(wolfDis[i], Integer.MAX_VALUE);
        }

        wolfDis[1][0] = 0;
        queue.offer(new Edge(1, wolfDis[1][0]));

        while (!queue.isEmpty()) {
            Edge now = queue.poll();

            if (wolfDis[now.num][now.state] < now.weight) continue;

            for (Edge next : graph[now.num]) {
                int distance = now.state == 0 ? now.weight + next.weight : now.weight + next.weight * 4;
                int nextState = now.state ^ 1;

                if (wolfDis[next.num][nextState] > distance) {
                    wolfDis[next.num][nextState] = distance;
                    queue.offer(new Edge(next.num, distance, nextState));
                }
            }
        }
    }

}
