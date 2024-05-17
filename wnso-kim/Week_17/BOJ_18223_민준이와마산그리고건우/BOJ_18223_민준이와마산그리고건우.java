package Week_17.BOJ_18223_민준이와마산그리고건우;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18223_민준이와마산그리고건우 {

    static int V,E,P;
    static List<Next>[] nodes;

    static class Next{
        int index;
        int weight;
        Next(int index, int weight){
            this.index = index;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력| V E P
        StringTokenizer stk = new StringTokenizer(br.readLine());
        V = Integer.parseInt(stk.nextToken());
        E = Integer.parseInt(stk.nextToken());
        P = Integer.parseInt(stk.nextToken());

        // 초기화| nodes
        nodes = new List[V+1];
        for(int i=0; i<=V; i++) nodes[i] = new LinkedList<>();
        
        // 입력| 간선 정보
        for(int i=0; i<E; i++){
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());

            nodes[a].add(new Next(b, c));
            nodes[b].add(new Next(a, c));
        }

        // 다익스트라| 1->V
        int one2v = dijkstra(1, V);

        // 다익스트라| 1->P
        int one2p = dijkstra(1, P);

        // 다익스트라| P->V
        int p2v = dijkstra(P, V);
        
        // 출력
        System.out.println((one2v < one2p+p2v)? "GOOD BYE": "SAVE HIM");
    }

    static int dijkstra(int start, int end){
        Queue<int[]> queue = new PriorityQueue<>((a1,a2)->a1[1]-a2[1]);
        int[] visit = new int[V+1];
        Arrays.fill(visit, Integer.MAX_VALUE);

        queue.add(new int[]{start, 0});
        visit[start] = 0;

        while(!queue.isEmpty()){
            int[] state = queue.poll();
            int from = state[0];
            int sum = state[1];
  
            for(Next next: nodes[from]){
                int to = next.index;
                int weight = next.weight + sum;

                if(visit[to] <= weight) continue;

                queue.add(new int[]{to, weight});
                visit[to] = weight;
            }
        }

        return visit[end];
    } 
}