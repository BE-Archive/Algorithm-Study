package Week_08.BOJ_14500;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {

    static int n, m;

    static boolean isvalid(int y, int x) {
        return (0 <= y && y < n && 0 <= x && x < m);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][][] block = {
                {{0, 1}, {0, 2}, {0, 3}}, {{1, 0}, {2, 0}, {3, 0}},
                {{0, 1}, {1, 0}, {1, 1}},
                {{1, 0}, {2, 0}, {2, 1}}, {{1, 0}, {0, 1}, {0, 2}}, {{0, 1}, {1, 1}, {2, 1}}, {{0, 1}, {0, 2}, {-1, 2}},
                {{1, 0}, {2, 0}, {2, -1}}, {{-1, 0}, {0, 1}, {0, 2}}, {{0, 1}, {1, 0}, {2, 0}}, {{0, -1}, {0, -2}, {1, 0}},
                {{1, 0}, {1, 1}, {2, 1}}, {{0, 1}, {-1, 1}, {-1, 2}},
                {{1, 0}, {1, -1}, {2, -1}}, {{0, 1}, {1, 1}, {1, 2}},
                {{1, 0}, {0, 1}, {-1, 0}}, {{1, 0}, {0, -1}, {-1, 0}}, {{0, -1}, {0, 1}, {-1, 0}}, {{0, -1}, {0, 1}, {1, 0}}
        };

        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int ny, nx, sum = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < block.length; k++) {
                    sum = arr[i][j];
                    for (int l = 0; l < 3; l++) {
                        ny = i + block[k][l][0];
                        nx = j + block[k][l][1];
                        if (!isvalid(ny, nx)) break;
                        sum += arr[ny][nx];
                        if (l == 2) ans = Math.max(sum, ans);
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
