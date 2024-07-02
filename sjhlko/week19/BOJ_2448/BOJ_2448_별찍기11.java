package sjhlko.week19.BOJ_2448;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2448_별찍기11 {
    //https://www.acmicpc.net/problem/2448
    //별찍기
    static char STAR = '*';
    static int N, bottom;
    static char[][] map;

    static void solution() {
        bottom = N * 5 + (N - 1);
        map = new char[N * 3][bottom];
        map[0][bottom / 2] = STAR;
        for (int i = 1; i < N * 3; i++) {
            int type = i % 3;
            switch (type) {
                case 0:
                    for (int j = 2; j < bottom - 2; j++) {
                        if (map[i - 1][j] == STAR) continue;
                        if (map[i - 1][j + 1] == STAR && map[i - 1][j - 1] != STAR) {
                            map[i][j] = STAR;
                        }
                        if (map[i - 1][j - 1] == STAR && map[i - 1][j + 1] != STAR) {
                            map[i][j] = STAR;
                        }
                    }
                    break;
                case 1:
                    for (int j = 1; j < bottom - 1; j++) {
                        if (map[i - 1][j + 1] == STAR || map[i - 1][j - 1] == STAR) {
                            map[i][j] = STAR;
                        }
                    }
                    break;
                case 2:
                    for (int j = 0; j < bottom -1; j++) {
                        if (map[i - 1][j + 1] == STAR) {
                            for (int k = 0; k < 5; k++) {
                                map[i][j++] = STAR;
                            }
                        }
                    }
                    break;
            }
        }
    }

    static void printAns() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N * 3; i++) {
            for (int j = 0; j < bottom; j++) {
                if(map[i][j]==STAR) sb.append(STAR);
                else sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine()) / 3;
        solution();
        printAns();
    }
}