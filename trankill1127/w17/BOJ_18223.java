import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18223 {

    public static int V, E, P;
    public static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        V = Integer.parseInt(st.nextToken()); //정점 개수
        E = Integer.parseInt(st.nextToken()); //간선 개수
        P = Integer.parseInt(st.nextToken()); //건우 위치

        int a,b,c;
        graph = new ArrayList[V+1];
        for (int i=1; i<=V; i++) graph[i]=new ArrayList<>();

        for (int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine().trim());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b,c});
            graph[b].add(new int[]{a,c});
        }

        int len1 = solution(1,V);
        int len2 = solution(1,P)+solution(P,V);
        if (len1==len2) System.out.println("SAVE HIM");
        else System.out.println("GOOD BYE");
    }

    public static int solution(int start, int end){
        Queue<int[]> pq = new PriorityQueue<>((a,b)->(a[1]-b[1]));
        boolean[] visited = new boolean[V+1];
        int[] shortest = new int[V+1];
        Arrays.fill(shortest, Integer.MAX_VALUE);
        shortest[start]=0;
        pq.add(new int[]{start, 0});

        while(!pq.isEmpty()){
            int[] now = pq.poll();
            if (visited[now[0]]) continue;
            visited[now[0]]=true;
            if (now[0]==end) {
                return shortest[end];
            }

            for (int[] next: graph[now[0]]){
                if (visited[next[0]]) continue;

                if (shortest[next[0]]>shortest[now[0]]+next[1]){
                    shortest[next[0]]=shortest[now[0]]+next[1];
                    pq.add(next);
                }
            }
        }
        return start;
    }
}
