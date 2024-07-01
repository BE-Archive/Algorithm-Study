import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17396 {

    public static class Node implements Comparable<Node>{
        int id;
        long time;

        public Node(int id, long time){
            this.id=id;
            this.time=time;
        }

        @Override
        public int compareTo(Node o) {
            return (int)(this.time-o.time);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[] isVisible = new boolean[n];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < n; i++) {
            if (Integer.parseInt(st.nextToken()) == 1) {
                isVisible[i] = true;
            }
        }
        isVisible[n-1]=false;

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            if (isVisible[v1] || isVisible[v2]) continue;
            graph.get(v1).add(new Node(v2, t));
            graph.get(v2).add(new Node(v1, t));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] times = new long[n];
        Arrays.fill(times, Long.MAX_VALUE);

        pq.offer(new Node(0,0));
        times[0]=0;
        while (!pq.isEmpty()){
            Node now = pq.poll();
            if (now.id==n-1) continue;
            if (now.time>times[now.id]) continue;

            for (Node next: graph.get(now.id)){
                if (times[next.id]>now.time+next.time){
                    times[next.id]=now.time+next.time;
                    pq.offer(new Node(next.id, times[next.id]));
                }
            }
        }

        if (times[n-1]==Long.MAX_VALUE) System.out.println(-1);
        else System.out.println(times[n-1]);
    }
}
