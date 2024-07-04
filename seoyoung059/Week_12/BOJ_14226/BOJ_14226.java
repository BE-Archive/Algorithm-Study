package Week_12.BOJ_14226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BOJ_14226 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(br.readLine());

        boolean[][] visited  = new boolean[1001][1001];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {1, 0});
        visited[1][0] = true;

        int qSize; int[] curr;
        int answer = 0;
        loop: while(!q.isEmpty()){
            qSize = q.size();
            while(qSize-- > 0){
                curr = q.pollFirst();
                if(curr[0] == s){
                    break loop;
                }
                if(!visited[curr[0]][curr[0]]){
                    q.offerLast(new int[] {curr[0], curr[0]});
                    visited[curr[0]][curr[0]] = true;
                }
                if(curr[0]-1>=0 && !visited[curr[0]-1][curr[1]]){
                    q.offerLast(new int[] {curr[0]-1, curr[1]});
                    visited[curr[0]-1][curr[1]]=true;
                }
                if(curr[0]+curr[1]<1001 && !visited[curr[0]+curr[1]][curr[1]] ){
                    q.offerLast(new int[] {curr[0]+curr[1], curr[1]});
                    visited[curr[0]+curr[1]][curr[1]] = true;
                }
            }
            answer++;
        }
        System.out.println(answer);
    }
}
