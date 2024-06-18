import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_5656 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t=Integer.parseInt(br.readLine());
        for (int tc=1; tc<=t; tc++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); //공 개수
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            int[][] blocks = new int[h][w];
            for (int i=0; i<h; i++){
                st = new StringTokenizer(br.readLine(), " ");
                for (int j=0; j<w; j++){
                    blocks[i][j]=Integer.parseInt(st.nextToken());
                }
            }

            minLeftBlocks=Integer.MAX_VALUE;
            for (int j=0; j<w; j++){
                int i=0;
                while (i<h && blocks[i][j]==0) {
                    i++;
                }
                if (i==h) continue;

                //System.out.println("0: "+i+" "+j);
                falls(0,i,j,blocks);
            }

            if (minLeftBlocks!=Integer.MAX_VALUE)
                sb.append("#").append(tc).append(" ").append(minLeftBlocks).append("\n");
            else
                sb.append("#").append(tc).append(" ").append(0).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int n; //공 개수
    public static int w;
    public static int h;
    public static int[][] direction = {
            {-1,0}, {1,0}, {0,1}, {0,-1}
    };
    public static int minLeftBlocks;

    public static void falls(int trial, int x, int y, int[][] prevState){

        if (minLeftBlocks==0) return;

        if (trial==n){
            int leftBlocks=0;
            for (int i=0; i<w; i++){
                for (int j=h-1; j>=0; j--){
                    if (prevState[j][i]==0) break;
                    leftBlocks++;
                }
            }
            minLeftBlocks=Math.min(minLeftBlocks, leftBlocks);
            return;
        }

        int[][] curState = updateBlocks(prevState, getEffectedRange(x,y,prevState));
        if (trial+1==n){
            falls(trial+1, 0, 0, curState);
            return;
        }

        for (int j=0; j<w; j++){
            int i=0;
            while (i<h && curState[i][j]==0) {
                i++;
            }
            if (i==h) continue;

            //System.out.println(trial+1+": "+i+" "+j);
            falls(trial+1, i, j, curState);
        }
        return;

    }

    public static int[][] getEffectedRange(int x, int y, int[][] prevState){
        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[prevState.length][prevState[0].length];
        q.add(new int[]{x,y,prevState[x][y]});
        visited[x][y]=1;

        while (!q.isEmpty()){
            int[] now = q.poll();
            for (int i=0; i<4; i++){
                for (int j=0; j<now[2]; j++){
                    int nextX = now[0]+(j*direction[i][0]);
                    int nextY = now[1]+(j*direction[i][1]);
                    if (nextX<0||nextX>=h||nextY<0||nextY>=w||visited[nextX][nextY]==1) continue;
                    visited[nextX][nextY]=1;
                    q.add(new int[]{nextX,nextY,prevState[nextX][nextY]});
                }
            }
        }

        return visited;
    }

    public static int[][] updateBlocks(int[][] state, int[][] visited) {
        int[][] newState = new int[state.length][state[0].length];
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                newState[i][j] = state[i][j];
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int j = 0; j < state[0].length; j++) {
            for (int i = state.length - 1; i >= 0; i--) {
                if (visited[i][j] == 0 && state[i][j] != 0) {
                    q.add(state[i][j]);
                }
            }
            int i = state.length - 1;
            while (i >= 0) {
                if (!q.isEmpty()) {
                    newState[i][j] = q.poll();
                } else {
                    newState[i][j] = 0;
                }
                i--;
            }
        }

        return newState;
    }

}
