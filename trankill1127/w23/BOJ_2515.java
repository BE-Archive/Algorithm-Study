import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2515 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int  n = Integer.parseInt(st.nextToken());
        int  s = Integer.parseInt(st.nextToken());

        List<int[]> drawings = new ArrayList<>();
        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine().trim());
            drawings.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        drawings.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]){
                    return o1[1]-o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        int[][] dp = new int[n][2];
        dp[0][1]= drawings.get(0)[1];

        for (int i=1; i<n; i++){
            dp[i][0]=Math.max(dp[i-1][1], dp[i-1][0]);
            dp[i][1]=drawings.get(i)[1];

            int start=0, end=n-1, mid=0;
            while (start<=end){
                mid=(start+end)/2;
                if (drawings.get(i)[0] - drawings.get(mid)[0] >= s
                && drawings.get(i)[0] - drawings.get(mid+1)[0] < s ){
                    break;
                }
                else if (drawings.get(i)[0] - drawings.get(mid)[0] >= s
                        && drawings.get(i)[0] - drawings.get(mid+1)[0] >= s ){
                    start=mid+1;
                }
                else if (drawings.get(i)[0] - drawings.get(mid)[0] < s){
                    end=mid-1;
                }
            }
            if (drawings.get(i)[0] - drawings.get(mid)[0] >=s){
                dp[i][1]+=Math.max(dp[mid][0], dp[mid][1]);
            }
        }

        System.out.println(Math.max(dp[n-1][0], dp[n-1][1]));
    }
}