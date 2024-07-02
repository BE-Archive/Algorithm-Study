package sjhlko.week21.BOJ_16118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16118_달빛여우 {
    //https://www.acmicpc.net/problem/16118
    //달빛 여우
    static int N, M;
    static List<List<Node>> graph = new ArrayList<>();
    static double[] distF, distW[];

    static class Node implements Comparable<Node> {
        int target;
        double cost;
        boolean flag = false;

        public Node(int target, double cost, boolean flag) {
            this.target = target;
            this.cost = cost;
            this.flag = flag;
        }

        public Node(int target, double cost) {
            this.target = target;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(cost, o.cost);
        }
    }

    static void dijkstraFox() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distF = new double[N + 1];
        Arrays.fill(distF, Integer.MAX_VALUE);
        distF[1] = 0;
        pq.add(new Node(1, 0, true));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (distF[now.target] < now.cost) continue;
            for (Node next : graph.get(now.target)) {
                double nextCost = next.cost;
                if (distF[next.target] <= distF[now.target] + nextCost) continue;
                distF[next.target] = distF[now.target] + nextCost;
                pq.add(new Node(next.target, distF[next.target], !now.flag));
            }
        }
    }

    static void dijkstraWolf() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distW = new double[N + 1][2];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(distW[i], Integer.MAX_VALUE);
        }
        distW[1][0] = 0;
        pq.add(new Node(1, 0, true));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int nowFlag = now.flag ? 0 : 1;
            int nextFlag = 1 - nowFlag;
            if (distW[now.target][nowFlag] < now.cost) continue;
            for (Node next : graph.get(now.target)) {
                double nextCost = now.flag ? next.cost / 2 : next.cost * 2;
                if (distW[next.target][nextFlag] <= distW[now.target][nowFlag] + nextCost) continue;
                distW[next.target][nextFlag] = distW[now.target][nowFlag] + nextCost;
                pq.add(new Node(next.target, distW[next.target][nextFlag], !now.flag));
            }
        }
    }

    static int solution() {
        dijkstraFox();
        dijkstraWolf();
        int count = 0;
        for (int i = 1; i < N + 1; i++) {
            if (distF[i] < Math.min(distW[i][0], distW[i][1])) count++;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
        System.out.println(solution());
    }
}
