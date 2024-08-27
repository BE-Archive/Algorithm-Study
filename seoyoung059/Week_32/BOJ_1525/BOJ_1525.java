package Week_32.BOJ_1525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1525 {
    static Set<Integer> isVisited;

    static void swap(int y1, int x1, int y2, int x2, int[][] arr) {
        int tmp = arr[y1][x1];
        arr[y1][x1] = arr[y2][x2];
        arr[y2][x2] = tmp;
    }

    static boolean isValid(int ny, int nx) {
        return 0<=ny && ny<3 && 0<=nx && nx<3;
    }

    static int getHash(int[][] arr) {
        int tmp = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tmp = tmp*10 + arr[i][j];
            }
        }
        return tmp;
    }

    static class Status {
        int y;
        int x;
        int[][] arr;
        int state;

        Status(int y, int x, int[][] arr, int state) {
            this.y = y;
            this.x = x;
            this.arr = arr;
            this.state = state;
        }
    }
    static int solve(int sy, int sx, int[][] arr, int tmp) {
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};

        int ny, nx; int[][] newArr;
        Status curr;
        ArrayDeque<Status> q = new ArrayDeque<>();
        q.add(new Status(sy, sx, arr, tmp));

        int cnt = 0; int qSize, hash;
        while (!q.isEmpty()) {
            qSize = q.size();
            while(qSize -- > 0) {
                curr = q.pollFirst();
                if (curr.state == 123_456_780) return cnt;
                sy = curr.y; sx = curr.x;
                for (int i = 0; i < 4; i++) {
                    ny = curr.y + dy[i];
                    nx = curr.x + dx[i];
                    if (!isValid(ny, nx)) continue;
                    swap(sy, sx, ny, nx, curr.arr);
                    hash = getHash(curr.arr);
                    newArr = new int[3][3];
                    for (int j = 0; j < 3; j++) {
                        for (int k = 0; k < 3; k++) {
                            newArr[j][k] = curr.arr[j][k];
                        }
                    }
                    swap(sy, sx, ny, nx, curr.arr);
                    if (!isVisited.add(hash)) {
                        continue;
                    }
                    q.offer(new Status(ny, nx, newArr, hash));
                }
            }
            cnt++;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] arr = new int[3][3];
        isVisited = new HashSet<>();
        StringTokenizer st;
        int sy=-1, sx=-1; int tmp = 0;
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==0) {
                    sy = i; sx = j;
                }
                tmp = tmp*10+arr[i][j];
            }
        }
        isVisited.add(tmp);

        System.out.println(solve(sy, sx, arr, tmp));
    }
}