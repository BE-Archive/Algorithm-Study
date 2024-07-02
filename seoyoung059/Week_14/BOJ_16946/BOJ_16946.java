package Week_14.BOJ_16946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_16946 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[][][] arr = new int[n][m][3];
//        int[][] answer = new int[n][m];
        String str;
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j][0] = str.charAt(j)-'0';
                arr[i][j][1] = i;
                arr[i][j][2] = j;
            }
        }



        ArrayDeque<int[]> bfsq = new ArrayDeque<>();
        HashSet<int[]> wallSet;
        int[] curr;
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        int ny, nx, cnt;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j][0] == 0) {
                    cnt = 1;
                    wallSet = new HashSet<>();
                    bfsq.offerLast(arr[i][j]);
                    arr[i][j][0] = -1;
                    while(!bfsq.isEmpty()){
                        curr = bfsq.pollFirst();
                        for (int k = 0; k < 4; k++) {
                            ny = curr[1]+dy[k];
                            nx = curr[2]+dx[k];
                            if(ny<0||nx<0||n<=ny||m<=nx||arr[ny][nx][0]==-1) continue;
                            if(arr[ny][nx][0]>0){
                                wallSet.add(arr[ny][nx]);
                            } else {
                                bfsq.offerLast(arr[ny][nx]);
                                arr[ny][nx][0] = -1;
                                cnt++;
                            }
                        }
                    }

                    for(int[] w: wallSet) {
//                        answer[w[1]][w[2]]+=cnt;
                        arr[w[1]][w[2]][0]+=cnt;
                    }

                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append((arr[i][j][0]>0)?(arr[i][j][0])%10:0);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
