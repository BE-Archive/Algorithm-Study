package sjhlko.week29.BOJ_1525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //https://www.acmicpc.net/problem/1525
    //퍼즐
    static Set<Integer> visited = new HashSet<>();
    static int clean = 123456780;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Status {
        int[][] puzzle;
        int i, j;
        int count;

        public Status(int[][] puzzle, int i, int j, int count) {
            this.puzzle = puzzle;
            this.i = i;
            this.j = j;
            this.count = count;
        }
    }

    static int makeKey(int[][] puzzle) {
        int ret = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ret = ret * 10 + puzzle[i][j];
            }
        }
        return ret;
    }

    static int solution(Status now) {
        visited.add(makeKey(now.puzzle));
        Queue<Status> queue = new LinkedList<>();
        queue.add(now);
        while (!queue.isEmpty()) {
            Status s = queue.poll();
            int key = makeKey(s.puzzle);
            if (key==clean)
                return s.count;
            for (int i = 0; i < 4; i++) {
                int nextI = s.i + dx[i];
                int nextJ = s.j + dy[i];
                if (nextI < 0 || nextJ < 0 || nextI >= 3 || nextJ >= 3)
                    continue;
                int[][] next = new int[3][3];
                for (int j = 0; j < 3; j++) {
                    next[j] = s.puzzle[j].clone();
                }
                next[s.i][s.j] = next[nextI][nextJ];
                next[nextI][nextJ] = 0;
                int nextKey = makeKey(next);
                if (visited.contains(nextKey))
                    continue;
                visited.add(nextKey);
                queue.add(new Status(next, nextI, nextJ, s.count + 1));
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[][] puzzle = new int[3][3];
        int firstI = 0, firstJ = 0;
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 3; j++) {
                puzzle[i][j] = Integer.parseInt(st.nextToken());
                if (puzzle[i][j] == 0) {
                    firstI = i;
                    firstJ = j;
                }
            }
        }
        System.out.println(solution(new Status(puzzle, firstI, firstJ, 0)));
    }
}
