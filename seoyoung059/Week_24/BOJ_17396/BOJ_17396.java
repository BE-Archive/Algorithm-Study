package Week_24.BOJ_17396;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17396 {

    static class Node implements Comparable<Node> {
        int idx;
        long t;

        public Node(int idx, long t) {
            this.idx = idx;
            this.t = t;
        }

        @Override
        public int compareTo(Node o) {
            if(this.t==o.t) return 0;
            return  (int) ((this.t - o.t)/Math.abs(this.t - o.t));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); //분기점 수
        int m = Integer.parseInt(st.nextToken()); //분기점을 잇는 길의 수

        boolean[] sight = new boolean[n];
        String str = br.readLine();
        ArrayDeque<Node>[] arr = new ArrayDeque[n];
        long[] distance = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new ArrayDeque<>();
            distance[i] = Long.MAX_VALUE;
            sight[i] = (str.charAt(i * 2) == '1');
        }
        sight[n - 1] = false;


        int a, b, t;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            if(sight[a] || sight[b]) continue;
            arr[a].offer(new Node(b, t));
            arr[b].offer(new Node(a, t));
        }


        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node curr;
        long answer = -1;
        pq.offer(new Node(0, 0));
        distance[0] = 0;
        while (!pq.isEmpty()) {
            curr = pq.poll();
            if (curr.idx == n - 1) {
                answer = curr.t;
                break;
            }
            if(distance[curr.idx] < curr.t) continue;
            for (Node node : arr[curr.idx]) {
                if (distance[node.idx] > distance[curr.idx] + node.t) {
                    distance[node.idx] = distance[curr.idx] + node.t;
                    pq.offer(new Node(node.idx, distance[curr.idx] + node.t));
                }
            }
        }


        System.out.println(answer);
    }
}