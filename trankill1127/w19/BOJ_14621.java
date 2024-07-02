import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14621 {

    //17504 KB, 184ms
    public static class Edge implements Comparable<Edge>{
        int s;
        int e;
        int w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w-o.w;
        }
    }

    public static int n, m;
    public static boolean[] gender;
    public static PriorityQueue<Edge> pq;
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        gender=new boolean[n+1];
        st = new StringTokenizer(br.readLine().trim());
        for (int i=1; i<=n; i++){
            gender[i]=( st.nextToken().equals("M")? false : true );
        }

        pq = new PriorityQueue<>();
        for (int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine().trim());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (gender[s]==gender[e]) continue;
            pq.add(new Edge(s,e,w));
        }

        System.out.println(kruskal());
    }

    public static int kruskal(){
        int totW=0;
        int edgeCnt=0;

        parent = new int[n+1];
        for (int i=1; i<=n; i++) parent[i]=i;

        while (!pq.isEmpty()){
            Edge now = pq.poll();
            if (find(now.s)==find(now.e)) continue;
            union(now.s, now.e);
            edgeCnt++;
            totW += now.w;
        }
        return (edgeCnt==n-1) ? totW : -1;
    }

    public static void union(int s, int e){
        int sP = find(s);
        int eP = find(e);

        if (sP==eP) return;

        if (sP<eP){
            parent[eP]=sP;
        }
        else {
            parent[sP]=eP;
        }
    }

    public static int find(int v){
        if (parent[v]==v) return v;
        return parent[v]=find(parent[v]);
    }
}