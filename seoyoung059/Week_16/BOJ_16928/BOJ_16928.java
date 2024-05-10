package Week_16.BOJ_16928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_16928 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[] visited= new boolean[100];
        Map<Integer, Integer> moveto = new HashMap<>();

        for (int i = 0; i < n+m; i++) {
            st = new StringTokenizer(br.readLine());
            moveto.put(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        visited[0] = true;
        int qSize, curr, next, cnt = 0;
        loop: while(!q.isEmpty()) {
            qSize = q.size();
            cnt++;
            while(qSize-->0){
                curr = q.pollFirst();
                for (int i = 1; i <= 6; i++) {
                    next = curr+i;
                    if(visited[next]) continue;
                    visited[next] = true;
                    if(moveto.containsKey(next)){
                        next = moveto.get(next);
                        if(visited[next]) continue;
                    }
                    if(next==99) break loop;
                    q.offer(next);
                }
            }
        }
        System.out.println(cnt);
    }
}