import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] arr = new int[n][3];
        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //최대점수
        int[][] sum = new int[n][3];
        for (int i=0; i<3; i++) sum[0][i]=arr[0][i];
        for (int i=1; i<n; i++){
            sum[i][0] = Math.max(sum[i-1][0]+arr[i][0], sum[i-1][1]+arr[i][0]);
            sum[i][1] = Math.max(
                    Math.max(sum[i-1][0]+arr[i][1], sum[i-1][1]+arr[i][1]),
                    sum[i-1][2]+arr[i][1]);
            sum[i][2] = Math.max(sum[i-1][1]+arr[i][2], sum[i-1][2]+arr[i][2]);
        }
        int maxSum = Math.max(Math.max(sum[n-1][0], sum[n-1][1]), sum[n-1][2]);

        //최소점수
        for (int i=1; i<n; i++){
            sum[i][0] = Math.min(sum[i-1][0]+arr[i][0], sum[i-1][1]+arr[i][0]);
            sum[i][1] = Math.min(
                    Math.min(sum[i-1][0]+arr[i][1], sum[i-1][1]+arr[i][1]),
                    sum[i-1][2]+arr[i][1]);
            sum[i][2] = Math.min(sum[i-1][1]+arr[i][2], sum[i-1][2]+arr[i][2]);
        }
        int minSum = Math.min(Math.min(sum[n-1][0], sum[n-1][1]), sum[n-1][2]);

        System.out.println(maxSum+" "+minSum);
    }
}
