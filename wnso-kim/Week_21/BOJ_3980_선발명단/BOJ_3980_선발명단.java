import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3980_선발명단 {
    static int C, answer;
    static int[][] players = new int[11][11];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        C = Integer.parseInt(br.readLine());

        for(int c=0; c<C; c++){
            
            for(int i=0; i<11; i++){
                StringTokenizer stk = new StringTokenizer(br.readLine());
                for(int j=0; j<11; j++){
                    players[i][j] = Integer.parseInt(stk.nextToken());
                }
            }

            answer = 0;
            dfs(0, 0, 0);

            System.out.println(answer);
        }
    }

    static void dfs(int depth, int sum, int visit){
        if(depth == 11){
            answer = Math.max(answer, sum);
            return;
        }

        for(int i=0; i<11; i++){
            if((visit & (1<<i)) != 0) continue;
            if(players[depth][i] == 0) continue;

            dfs(depth+1, sum+players[depth][i], visit|1<<i);
        }
    }
}