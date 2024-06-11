import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3980 {

    static int[][] board = new int[11][11];
    static boolean[] position = new boolean[11];
    public static int maxTotScore=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int c = Integer.parseInt(br.readLine());
        while (c>0){
            for (int i=0; i<11; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < 11; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());

                }
            }
            maxTotScore=0;
            position = new boolean[11];
            recursive(0,0);
            sb.append(maxTotScore).append("\n");
            c--;
        }
        System.out.println(sb.toString());
    }

    public static void recursive(int player, int totScore){
        if (player==11){
            maxTotScore = Math.max(maxTotScore, totScore);
            return;
        }

        for (int pos=0; pos<11; pos++){
            if (position[pos] || board[player][pos]==0) continue;
            position[pos]=true;
            recursive(player+1, totScore+board[player][pos]);
            position[pos]=false;
        }

    }
}
