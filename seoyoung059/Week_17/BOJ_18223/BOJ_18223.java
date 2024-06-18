package Week_17.BOJ_18223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18223 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        if(v==p || v==1) {
            System.out.println("SAVE HIM");
            return;
        }

        int[] visited = new int[v+1];
        ArrayList<int[]>[] graph = new ArrayList[v+1];
        for (int i = 1; i < v+1; i++) {
            graph[i] = new ArrayList<>();
            visited[i] = Integer.MAX_VALUE;
        }


        int a, b, c;
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph[a].add(new int[] {b, c});
            graph[b].add(new int[] {a, c});
        }


        int distance = Integer.MAX_VALUE;
        boolean save = false;
        int[] curr;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o->o[1]));
        for(int[] edge: graph[1]) {
            pq.offer(new int[] {edge[0], edge[1], (edge[0]==p)?1:0});
        }

        while(!pq.isEmpty()) {
            curr = pq.poll();
            if(curr[1] > distance) break;
            if(curr[0]==v){
                distance = curr[1];
                if(curr[2]==1){
                    save = true;
                    break;
                }
            }

            for(int[] edge: graph[curr[0]]) {
                if(visited[edge[0]] >= curr[1]+edge[1]){
                    visited[edge[0]] = curr[1]+edge[1];
                    pq.offer(new int[] {edge[0], curr[1]+edge[1], (edge[0]==p)?1:curr[2]});
                }
            }
        }
        System.out.println(save?"SAVE HIM":"GOOD BYE");
    }
}
