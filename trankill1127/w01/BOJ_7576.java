import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_7576 {

    public static class Pair{
        int x;
        int y;

        public Pair(int x, int y){
            this.x=x;
            this.y=y;
        }
    }

    static int n, m;
    static int[][] box;

    public static void main(String[]args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        m=Integer.parseInt(s[0]);
        n=Integer.parseInt(s[1]);

        box = new int [n][m];
        for (int i=0; i<n; i++){
            s = br.readLine().split(" ");
            for (int j=0; j<m; j++){
                box[i][j]=Integer.parseInt(s[j]);
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        Queue<Pair> q = new LinkedList<>();

        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                if (box[i][j]==1){
                    q.add(new Pair(i, j));
                }
            }
        }

        int lv = 0;
        int lvMem = q.size();
        while (!q.isEmpty()) {

            if (lvMem==0){
                lvMem=q.size();
                lv++;
            }

            Pair now = q.poll();
            lvMem--;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (nextX < 0 || nextX > n - 1 || nextY < 0 || nextY > m - 1) {
                    continue;
                }
                if (box[nextX][nextY] == -1 || box[nextX][nextY] == 1) {
                    continue;
                }

                if (box[nextX][nextY] == 0) {
                    q.add(new Pair(nextX, nextY));
                    box[nextX][nextY] = 1;
                }

            }
        }

        for (int[] arr : box){
            for (int elem : arr){
                if (elem==0) return -1;
            }
        }
        return lv;
    }
}
