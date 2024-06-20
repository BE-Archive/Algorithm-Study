import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11048 {

    //상향식 DP
//    public static int[][] direction = {{1, 0}, {0, 1}, {1,1}};
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        int n = Integer.parseInt(st.nextToken());
//        int m = Integer.parseInt(st.nextToken());
//        int[][] memo = new int[n+1][m+1];
//        for (int i = 0; i < n+1; i++) Arrays.fill(memo[i], 0);
//        int[][] maze = new int[n+1][m+1];
//        for (int i = 1; i <= n; i++) {
//            st = new StringTokenizer(br.readLine(), " ");
//            for (int j = 1; j <= m; j++) {
//                maze[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        for (int i=1; i<=n; i++) {
//            for (int j=1; j<=m; j++) {
//                for (int k=0; k<3; k++) {
//                    memo[i][j] = Math.max(memo[i][j],memo[i-direction[k][0]][j-direction[k][1]]+maze[i][j]);
//                }
//            }
//        }
//
//        System.out.println(memo[n][m]);
//    }

    //하향식 DP
    public static int[][] maze;
    public static int[][] memo; //하향식이어도 memo할 것은 필요하다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        memo = new int[n+1][m+1];
        maze = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= m; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
                memo[i][j] = -1;
            }
        }
        System.out.println(lowerDP(n,m));
    }

    public static int lowerDP(int i, int j){
        if (memo[i][j]<0){ //메모된 값이 없다면 값을 구해준다.
            memo[i][j]=maze[i][j]+Math.max(lowerDP(i,j-1), Math.max(lowerDP(i-1,j-1), lowerDP(i-1,j)));
        }

        return memo[i][j]; //값을 반환한다. (메모된 값이 있다면 바로 반환한다.)
    }

}
