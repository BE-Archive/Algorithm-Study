import java.io.*;
import java.util.StringTokenizer;


public class Main {
    static int[][][][] dir = {{
                    {{0, 1}, {1, 0}, {1, 1}}}, //ㅁ
                    {{{1, 0}, {2, 0}, {3, 0}}, //-
                    {{0, 1}, {0, 2}, {0, 3}}},
                    {{{0, 1}, {0, 2}, {1, 2}}, //ㄱ
                    {{0, 1}, {0, 2}, {-1, 2}},
                    {{1, 0}, {1, 1}, {1, 2}},
                    {{1, 0}, {0, 1}, {0, 2}},
                    {{0, 1}, {1, 1}, {2, 1}},
                    {{1, 0}, {2, 0}, {0, 1}},
                    {{1, 0}, {2, 0}, {2, -1}},
                    {{1, 0}, {2, 0}, {2, 1}}},
                    {{{0, 1}, {-1, 1}, {-1, 2}},//ㄱㄴ
                    {{0, 1}, {1, 1}, {1, 2}},
                    {{1, 0}, {1, 1}, {2, 1}},
                    {{1, 0}, {1, -1}, {2, -1}}},
                    {{{1, 0}, {2, 0}, {1, 1}},//ㅗ
                    {{1, 0}, {2, 0}, {1, -1}},
                    {{0, 1}, {0, 2}, {1, 1}},
                    {{0, 1}, {0, 2}, {-1, 1}}}};
    static int max = 0, N, M;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                calcSum(i, j);
            }
        }
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }

    private static void calcSum(int row, int col) {
        for (int k = 0; k < dir.length; k++) {
            for (int l = 0; l < dir[k].length; l++) {
                int sum = map[row][col];
                for (int m = 0; m < dir[k][l].length; m++) {
                    int x = row + dir[k][l][m][0];
                    int y = col + dir[k][l][m][1];
                    if(x < 0 || x >= N || y < 0 || y >= M) {
                        sum = -1;
                        break;
                    }
                    sum += map[x][y];
                }
                max = Math.max(max, sum);
            }
        }
    }
}