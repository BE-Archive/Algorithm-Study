package Week_17.BOJ_2638;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2638 {
    static int n, m;

    public static boolean isValid(int y, int x) {
        return 0<=y && y<n && 0<=x && x<m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        ArrayDeque<int[]> q = new ArrayDeque<>();
        ArrayDeque<int[]> newq = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};
        int ny, nx, cnt = 0; int[] curr;
        while(!newq.isEmpty() || !q.isEmpty()) {
            cnt++;
            while(!newq.isEmpty()) {
                curr = newq.pollFirst();
                q.offerLast(curr);
            }
            while (!q.isEmpty()) {
                curr = q.pollFirst();
                for (int i = 0; i < 4; i++) {
                    ny = curr[0] + dy[i];
                    nx = curr[1] + dx[i];
                    if (isValid(ny, nx)) {
                        if (arr[ny][nx]== 0) {
                            q.offerLast(new int[]{ny, nx});
                            arr[ny][nx] = -1;
                        }
                        else if(arr[ny][nx]>0){
                            arr[ny][nx]++;
                            if (arr[ny][nx] == 3) {
                                newq.offerLast(new int[]{ny, nx});
                                arr[ny][nx] = -1;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(cnt-1);
    }
}
