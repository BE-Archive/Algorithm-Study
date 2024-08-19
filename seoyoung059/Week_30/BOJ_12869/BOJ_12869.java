package Week_30.BOJ_12869;

import javax.xml.transform.Source;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_12869 {
    static int[][][] visited;
    static int answer;
    static void attack(int[] hp, int cnt) {
        if(hp[0]<=0 && hp[1]<=0 && hp[2]<=0) {
            answer = Math.min(answer, cnt);
            return;
        }

        hp[0]-=1; hp[1]-=1; hp[2]-=1;
        for(int i=0; i<3; i++) {
            hp[i] -= 8;
            for(int j=0; j<3; j++) {
                if(i==j) continue;
                hp[j] -= 2;
                if(visited[Math.max(hp[0], 0)][Math.max(hp[1], 0)][Math.max(hp[2], 0)] > cnt) {
                    visited[Math.max(hp[0], 0)][Math.max(hp[1], 0)][Math.max(hp[2], 0)] = cnt;
                    attack(hp, cnt + 1);
                }
                hp[j] += 2;
            }
            hp[i] += 8;
        }
        hp[0]+=1; hp[1]+=1; hp[2]+=1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] hp = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            hp[i] = Integer.parseInt(st.nextToken());
        }

        visited = new int[hp[0]+1][hp[1]+1][hp[2]+1];
        for (int i = 0; i < hp[0]+1; i++){
            for (int j = 0; j < hp[1]+1; j++){
                for (int k = 0; k < hp[2]+1; k++){
                    visited[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
//        System.out.println(" = " + Arrays.toString(hp));
        answer = Integer.MAX_VALUE;
        attack(hp, 1);
        System.out.println(answer-1);

    }
}