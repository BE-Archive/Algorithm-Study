package Week_37.BOJ_2251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2251 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int[] bottles = new int[3];
        for (int i = 0; i < 3; i++) {
            bottles[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][][] visited = new boolean[bottles[0]+1][bottles[1]+1][bottles[2]+1];

        Set<Integer> answer = new TreeSet<>();
        answer.add(bottles[2]);


        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0, bottles[2]});
        visited[0][0][bottles[2]] = true;
        int[] curr, next; int tmp;
        while(!q.isEmpty()) {
            curr = q.poll();
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    if(i==j || curr[j]==bottles[j] || curr[i]==0) continue;
                    tmp = Math.min(curr[i], bottles[j]-curr[j]);
                    next = curr.clone();
                    next[i] -= tmp;
                    next[j] += tmp;
                    if(visited[next[0]][next[1]][next[2]]) continue;
                    visited[next[0]][next[1]][next[2]] = true;
                    q.offer(new int[] {next[0], next[1], next[2]});
                    if(next[0]==0) answer.add(next[2]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Integer i : answer) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}
