import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1041 {

    public static int[][] set = {
            {0,1,2}, {0,2,4}, {0,3,4}, {0,1,3},
            {1,2,5}, {2,4,5}, {3,4,5}, {1,3,5}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine().trim());

        int[] dice = new int[6];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int i=0; i<6; i++){
            dice[i]=Integer.parseInt(st.nextToken());
        }

        long answer=0;
        if (n==1) {
            Arrays.sort(dice);
            for (int d=0; d<5; d++) answer+=dice[d];
        }
        else {
            answer = Long.MAX_VALUE;

            for (int i=0; i<8; i++){
                for (int j=0; j<3; j++){
                    for (int k=0; k<3; k++){
                        if (j==k) continue;

                        for (int l=0; l<3; l++){
                            if (j==l || k==l) continue;

                            int minOne=dice[set[i][j]];
                            int minTwo=minOne+dice[set[i][k]];
                            long minThree= (long) (minTwo+dice[set[i][l]]);
                            answer = Math.min(answer, minThree*4 + minTwo*(8L *n-12) + minOne*(5L *n*n- 16L *n+12));
                            System.out.println(set[i][j]+" "+set[i][k]+" "+set[i][l]+" "+answer);
                        }
                    }
                }

            }
        }

        System.out.println(answer);
    }
}