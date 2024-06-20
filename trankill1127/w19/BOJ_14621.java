import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14621 {

    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken()); //정점 개수
        int m = Integer.parseInt(st.nextToken()); //도로 개수

        int[] gender = new int[n+1];
        st = new StringTokenizer(br.readLine().trim());
        for (int i=1; i<=n; i++) {
            if (st.nextToken().equals("W")) gender[i]=0;
            else gender[i]=1;
        }

        LinkedList<int[]>[] graph = new LinkedList[n+1];
        for (int i=1; i<=n; i++){
            graph[i]=new LinkedList<>();
        }
        for (int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine().trim());
            int v1=Integer.parseInt(st.nextToken());
            int v2=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());
            if (gender[v1]==gender[v2]) continue;
            graph[v1].add(new int[]{v2,w});
            graph[v2].add(new int[]{v1,w});
        }
        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        };
        PriorityQueue<int[]> pq = new PriorityQueue<>(comparator);
        visited = new boolean[n+1];
        pq.add(new int[]{1,0});

        int verCnt=0;
        int totW=0;
        while (!pq.isEmpty()){
            int[] now = pq.poll();
            if (visited[now[0]]) continue;
            visited[now[0]]=true;
            verCnt++;
            totW+=now[1];
            if (verCnt==n) break;

            for (int[] next: graph[now[0]]){
                if (visited[next[0]]) continue;
                pq.add(next);
            }
        }

        if (totW==0) totW=-1;
        System.out.println(totW);
    }
}