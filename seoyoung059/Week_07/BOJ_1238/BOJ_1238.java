package Week_07.BOJ_1238;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1238 {
    static final int MAX = 99999999;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //학생 수N, 도로 수 M, 파티 장소 X
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken())-1;

        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(arr[i], MAX);
            arr[i][i] = 0;
        }

        int a, b, t;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken())-1;
            b = Integer.parseInt(st.nextToken())-1;
            t = Integer.parseInt(st.nextToken());
            arr[a][b] = t;
        }

        int start = 0; int curr;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(arr[j][i]==MAX || arr[i][k]==MAX) continue;
                    arr[j][k] = Math.min(arr[j][k], arr[j][i]+arr[i][k]);
                }
            }
        }
//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(arr[i]));
//        }

        int answer= Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, arr[i][x]+arr[x][i]);
        }
        System.out.println(answer);
    }
}