package seoyoung059.Week_03.BOJ_2096;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2096 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n+1][3];
        for (int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] minAnswer = new int[n+1][3];
        int[][] maxAnswer = new int[n+1][3];
        int minAns=Integer.MAX_VALUE;int maxAns=Integer.MIN_VALUE;
        for (int i = 1; i < n+1; i++) {
            for (int j = 0; j < 3; j++) {
                minAnswer[i][j] = Integer.MAX_VALUE;
                maxAnswer[i][j] = Integer.MIN_VALUE;
                if(j>0){
                    minAnswer[i][j] = Math.min(minAnswer[i-1][j-1]+arr[i][j], minAnswer[i][j]);
                    maxAnswer[i][j] = Math.max(maxAnswer[i-1][j-1]+arr[i][j], maxAnswer[i][j]);
                }
                minAnswer[i][j] = Math.min(minAnswer[i-1][j]+arr[i][j], minAnswer[i][j]);
                maxAnswer[i][j] = Math.max(maxAnswer[i-1][j]+arr[i][j], maxAnswer[i][j]);
                if(j<2){
                    minAnswer[i][j] = Math.min(minAnswer[i-1][j+1]+arr[i][j], minAnswer[i][j]);
                    maxAnswer[i][j] = Math.max(maxAnswer[i-1][j+1]+arr[i][j], maxAnswer[i][j]);
                }

                if(i==n){
                    minAns = Math.min(minAns, minAnswer[i][j]);
                    maxAns = Math.max(maxAns, maxAnswer[i][j]);
                }
            }
        }
        sb.append(maxAns).append(" ").append(minAns);
        System.out.print(sb);

    }
}
