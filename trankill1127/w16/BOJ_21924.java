import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21924 {

    public static class Node implements Comparable<Node>{
        int to;
        int w;

        public Node(int to, int w) {
            super();
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w-o.w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int n = Integer.parseInt(st.nextToken()); //건물 개수
        int m = Integer.parseInt(st.nextToken()); //도로 개수

        //그래프 세팅
        ArrayList<Node>[] g = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            g[i]=new ArrayList<>();
        }

        //도로 입력
        long allW=0;
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int n1=Integer.parseInt(st.nextToken());
            int n2=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());
            g[n1].add(new Node(n2,w));
            g[n2].add(new Node(n1,w));
            allW+=w;
        }

        long totW=0;
        int left=n;
        boolean[] visited = new boolean[n+1];
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(1,0)); //현재 위치, 누적 비용

        while (!q.isEmpty()) {
            if (left==0) break;
            Node now = q.poll();
            if (visited[now.to]) continue;
            visited[now.to]=true;
            totW+=now.w;
            left--;

            for (Node next: g[now.to]) {
                if (visited[next.to]) continue;
                q.add(next);
            }
        }

        if (left!=0) System.out.println(-1);
        else System.out.println(allW-totW);
    }
}
