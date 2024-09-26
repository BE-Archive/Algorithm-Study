package trankill1127.w36;

import java.io.*;
import java.util.*;

public class BOJ_11559 {

    static char[][] map = new char[12][6];
    static boolean[][] visited = new boolean[12][6];
    static List<int[]> group = new ArrayList<>();
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.print(solution());
    }

    public static int solution(){
        int chainReactionCnt = 0;

        while (explosion()){
            chainReactionCnt++;
            gravity();
            for (boolean[] row : visited) {
                Arrays.fill(row, false);
            }
        }

        return chainReactionCnt;
    }

    public static boolean explosion(){
        boolean isExploded = false;

        for (int i = 11; i >= 0; i--){
            for (int j = 0; j < 6; j++){
                if (map[i][j] == '.' || visited[i][j]) continue;

                group.clear();
                dfs(i, j);

                if (group.size() >= 4){
                    for (int[] p : group){
                        map[p[0]][p[1]] = '.';
                    }
                    isExploded = true;
                }
            }
        }

        return isExploded;
    }

    public static void dfs(int x, int y){
        visited[x][y] = true;
        group.add(new int[]{x, y});

        for (int[] d : dir){
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx < 0 || nx > 11 || ny < 0 || ny > 5) continue;
            if (map[nx][ny] != map[x][y] || visited[nx][ny]) continue;
            dfs(nx, ny);
        }
    }

    public static void gravity(){
        for (int j = 0; j < 6; j++){
            int index = 11;
            for (int i = 11; i >= 0; i--){
                if (map[i][j] != '.') {
                    map[index][j] = map[i][j];
                    index--;
                }
            }
            while(index >= 0) map[index--][j] = '.';
        }
    }
}
