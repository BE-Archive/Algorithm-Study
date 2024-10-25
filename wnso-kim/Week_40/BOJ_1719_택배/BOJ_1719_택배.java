package BOJ_1719_택배;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1719_택배 {

    static int INF = 200_001;
    static int N,M;
    static int[][] costs, numbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        costs = new int[N+1][N+1];
        numbers = new int[N+1][N+1];

        for(int m=0; m<M; m++){
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());

            costs[from][to] = costs[to][from] = cost;
            numbers[from][to] = to;
            numbers[to][from] = from;
        }

        // 초기화
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(costs[i][j] == 0){
                    costs[i][j] = INF;
                }
            }
        }

        for(int k=1; k<=N; k++){
            for(int from=1; from<=N; from++){
                if(k == from) continue;

                for(int to=1; to<=N; to++){
                    if(from == to) continue;
                    
                    if(costs[from][k]+costs[k][to] < INF &&
                        costs[from][to] > costs[from][k]+costs[k][to]){
                        costs[from][to] = costs[from][k]+costs[k][to];
                        numbers[from][to] = numbers[from][k];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                sb.append(costs[i][j]==INF? "-": numbers[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}