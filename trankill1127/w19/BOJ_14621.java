import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14621 {

    //17916KB, 192ms
    public static class Status implements Comparable<Status>{
        int v;
        int w;

        public Status(int v, int w) {
            this.v=v;
            this.w=w;
        }

        @Override
        public int compareTo(Status o) {
            return this.w-o.w;
        }
    }

    public static int n, m;
    public static boolean[] gender;
    public static ArrayList<ArrayList<Status>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        n = Integer.parseInt(st.nextToken()); //학교 개수(~1000)
        m = Integer.parseInt(st.nextToken()); //도로 개수(~10000)

        gender=new boolean[n+1];
        st = new StringTokenizer(br.readLine().trim());
        for (int i=1; i<=n; i++){
            gender[i]=( st.nextToken().equals("M")? false : true );
        }

        graph = new ArrayList<>();
        for (int i=0; i<=n; i++) graph.add(new ArrayList<>());

        for (int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine().trim());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if (gender[s]==gender[e]) continue;

            graph.get(s).add(new Status(e,w));
            graph.get(e).add(new Status(s,w));
        }

        System.out.println(prim());
    }

    public static int prim(){

        PriorityQueue<Status> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n+1];
        pq.add(new Status(1, 0));

        int visitCnt=0;
        int totW=0;

        while (!pq.isEmpty()){
            Status now = pq.poll();
            if (visited[now.v]) continue;
            visited[now.v]=true;
            visitCnt++;
            totW+=now.w;

            for (Status next: graph.get(now.v)){
                if (visited[next.v]) continue;
                pq.add(next);
            }
        }

        return (visitCnt==n)? totW : -1;
    }

}