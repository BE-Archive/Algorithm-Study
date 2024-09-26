package sjhlko.week36.BOJ_11559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //https://www.acmicpc.net/problem/11559
    //뿌요 뿌요
    static int max = 12;
    static char[][] map = new char[12][6];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static boolean pop(int i, int j) {
        boolean[][] visited = new boolean[12][6];
        visited[i][j] = true;
        Queue<int[]> queue = new ArrayDeque<>();
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{i, j});
        queue.add(new int[]{i, j});
        int count = 1;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nextI = now[0] + dx[k];
                int nextJ = now[1] + dy[k];
                if (nextI < max || nextI >= 12 || nextJ < 0 || nextJ >= 6) continue;
                if (visited[nextI][nextJ]) continue;
                if (map[nextI][nextJ] != map[i][j]) continue;
                count++;
                visited[nextI][nextJ] = true;
                list.add(new int[]{nextI, nextJ});
                queue.add(new int[]{nextI, nextJ});
            }
        }
        if (count >= 4) {
            for (int[] ints : list) {
                map[ints[0]][ints[1]] = '.';
            }
        }
        return count >= 4;
    }

    static boolean pop() {
        boolean ret = false;
        for (int i = max; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (map[i][j] != '.' && map[i][j]!=0) {
                    ret = ret | pop(i, j);
                }
            }
        }
        return ret;
    }

    static void down() {
        char[][] tmpMap = new char[12][6];
        for (int j = 0; j < 6; j++) {
            for (int i = 11, tI = 11; i >= max; i--) {
                if (map[i][j] != '.') {
                    tmpMap[tI][j] = map[i][j];
                    tI--;
                }
            }
        }
        map = tmpMap;
    }

    static int solution() {
        int count = 0;
        while (pop()) {
            down();
            count++;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            String s = bf.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] != '.') max = Math.min(max, i);
            }
        }
        System.out.println(solution());
    }
}
