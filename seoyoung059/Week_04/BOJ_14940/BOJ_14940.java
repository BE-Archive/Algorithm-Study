package seoyoung059.Week_04.BOJ_14940;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_14940 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][][] arr = new int[n][m][2];

        int[] start = new int[]{0, 0};
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j][0] = Integer.parseInt(st.nextToken());
                if (arr[i][j][0] == 2) {
                    start[0] = i;
                    start[1] = j;
                }
                arr[i][j][1] = (arr[i][j][0] == 0) ? 0 : -1;
            }
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(start);
        arr[start[0]][start[1]][1] = 0;
        int[] curr;
        while (!q.isEmpty()) {
            curr = q.poll();
            if (curr[0] + 1 < n && arr[curr[0] + 1][curr[1]][1] == -1) {
                if (arr[curr[0] + 1][curr[1]][0] == 1) {
                    arr[curr[0] + 1][curr[1]][1] = arr[curr[0]][curr[1]][1] + 1;
                    q.offer(new int[]{curr[0] + 1, curr[1]});
                }

            }
            if (curr[0] - 1 >= 0 && arr[curr[0] - 1][curr[1]][1] == -1) {
                if (arr[curr[0] - 1][curr[1]][0] == 1) {
                    arr[curr[0] - 1][curr[1]][1] = arr[curr[0]][curr[1]][1] + 1;
                    q.offer(new int[]{curr[0] - 1, curr[1]});
                }
            }
            if (curr[1] + 1 < m && arr[curr[0]][curr[1] + 1][1] == -1) {
                if (arr[curr[0]][curr[1] + 1][0] == 1) {
                    arr[curr[0]][curr[1] + 1][1] = arr[curr[0]][curr[1]][1] + 1;
                    q.offer(new int[]{curr[0], curr[1] + 1});
                }
            }
            if (curr[1] - 1 >= 0 && arr[curr[0]][curr[1] - 1][1] == -1) {
                if (arr[curr[0]][curr[1] - 1][0] == 1) {
                    arr[curr[0]][curr[1] - 1][1] = arr[curr[0]][curr[1]][1] + 1;
                    q.offer(new int[]{curr[0], curr[1] - 1});
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(arr[i][j][1]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}