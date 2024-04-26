package Ing.BOJ_16197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_16197 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] coins = new int[4];
        int cIdx = 0;

        char[][] arr = new char[n][m];
        String str;
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = str.charAt(j);
                if(arr[i][j]=='o'){
                    coins[2*cIdx] = i;
                    coins[2*cIdx+1] = j;
                    cIdx++;
                }
            }
        }

        boolean[][][][] visited = new boolean[n][m][n][m];

        ArrayDeque<int[]> q = new ArrayDeque<>();
        int[] curr; int qSize, ny1, ny2, nx1, nx2, cnt=0;
        boolean coin1, coin2;
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};
        q.offer(coins);
        while(!q.isEmpty() && cnt++ < 10){
            qSize = q.size();
//            for (int[] a: q){
//                System.out.println(Arrays.toString(a));
//            }
            while(qSize-- > 0){
                curr = q.pollFirst();
                for (int i = 0; i < 4; i++) {
                    ny1 = curr[0]+dy[i];
                    nx1 = curr[1]+dx[i];
                    ny2 = curr[2]+dy[i];
                    nx2 = curr[3]+dx[i];

                    if((coin1 = (0<=ny1 && ny1<n && 0<=nx1 && nx1<m)) && arr[ny1][nx1]=='#'){
                        ny1 = curr[0];
                        nx1 = curr[1];
                    }
                    if((coin2 = (0<=ny2 && ny2<n && 0<=nx2 && nx2<m)) && arr[ny2][nx2]=='#'){
                        ny2 = curr[2];
                        nx2 = curr[3];
                    }
//                    System.out.println(ny1+" "+nx1+" "+ny2+" "+nx2);
//                    System.out.println(coin1+" "+coin2);

                    if(!coin1&&!coin2||(ny1==ny2 && nx1==nx2)) continue;
                    if(coin1^coin2) {
                        System.out.println(cnt);
                        return;
                    }
                    if(!visited[ny1][nx1][ny2][nx2]){
                        q.offer(new int[] {ny1, nx1, ny2, nx2});
                        visited[ny1][nx1][ny2][nx2] = true;
                    }
                }
            }
        }
        System.out.println(-1);
    }
}