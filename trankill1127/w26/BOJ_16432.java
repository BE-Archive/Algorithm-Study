import java.io.*;
import java.util.*;

public class BOJ_16432 {

    static int n;
    static boolean[][] riceCake;
    static int[][] memo;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());

        riceCake = new boolean[n+1][10];
        for (int i=1; i<=n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int kind = Integer.parseInt(st.nextToken());
            while (kind-->0){
                riceCake[i][Integer.parseInt(st.nextToken())]=true;
            }
        }

        memo= new int[n+2][10];
        dfs();

        if (memo[n+1][0]==0) {
            System.out.print("-1");
            return;
        }

        ArrayDeque<Integer> q= new ArrayDeque<>();
        int x=n+1; int y=0;
        while (memo[x][y]>0){
            q.addLast(memo[x][y]);
            y=memo[x][y]; x--;
        }
        while (!q.isEmpty()){
            System.out.println(q.pollLast());
        }
    }

    public static void dfs(){
        LinkedList<int[]> s = new LinkedList<>();
        boolean[][][] visited = new boolean[n+1][10][10];
        s.push(new int[]{0,0});

        while (!s.isEmpty()){
            int[] now = s.poll();
            visited[now[0]][now[1]][memo[now[0]][now[1]]]=true;
            if (now[0]==n){
                memo[n+1][0]=now[1];
                break;
            }

            for (int i=1; i<=9; i++){
                if (riceCake[now[0]+1][i] && now[1]!=i && !visited[now[0]+1][i][now[1]]){
                    s.push(now);
                    s.push(new int[]{now[0]+1, i});
                    memo[now[0]+1][i]=now[1];
                    break;
                }
            }
        }
    }
}