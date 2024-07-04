package Week_07.BOJ_17779;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17779 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[][] arr = new int[n + 1][n + 1];

        int tmp;
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            tmp = 0;
            for (int j = 1; j < n + 1; j++) {
                tmp += Integer.parseInt(st.nextToken());
                arr[i][j] = tmp;
            }
        }

        int[] p = new int[6];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, ans = Integer.MAX_VALUE;
        for (int x = 1; x < n + 1; x++) {
            for (int y = 1; y < n + 1; y++) {
                for (int d1 = 1; d1 < n; d1++) {
                    for (int d2 = 1; d2 < n; d2++) {
                        if (x + d1 + d2 > n || y - d1 < 1 || y + d2 > n) continue;
                        for (int i = 0; i < 6; i++) {
                            p[i] = 0;
                        }
                        min = Integer.MAX_VALUE;
                        max = Integer.MIN_VALUE;


                        // 영역 5보다 위쪽 영역
                        for (int i = 1; i < x; i++) {
                            p[1] += arr[i][y];
                            p[2] += arr[i][n] - arr[i][y];
                        }

                        p[2] += arr[x][n] - arr[x][y];
                        p[3] += arr[x + d1][y - d1 - 1];
                        p[5] += arr[x][y] - arr[x][y - 1];

                        // d1으로 갱신되는 영역
                        for (int i = 1; i <= d1; i++) {
                            p[1] += arr[x + i - 1][y - i];
                            p[4] += arr[x + d2 + i][n] - arr[x + d2 + i][y + d2 - i];
                            p[5] += arr[x + d2 + i][y + d2 - i] - arr[x + i][y - i - 1];
                        }


                        // d2로 결정되는 영역
                        for (int i = 1; i <= d2; i++) {
                            p[2] += arr[x + i][n] - arr[x + i][y + i];
                            p[3] += arr[x + d1 + i][y - d1 + i - 1];
                            p[5] += arr[x + i][y + i] - arr[x + d1 + i][y - d1 + i - 1];
                        }

                        // 영역 5보다 남쪽에 있는 영역
                        for (int i = x + d1 + d2 + 1; i <= n; i++) {
                            p[3] += arr[i][y - d1 + d2 - 1];
                            p[4] += arr[i][n] - arr[i][y - d1 + d2 - 1];
                        }

                        // 최대인구/최소인구 구하기 및 답 갱신
                        for (int i = 1; i < 6; i++) {
                            min = Math.min(p[i], min);
                            max = Math.max(p[i], max);
                        }
                        ans = (max - min < ans) ? max - min : ans;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}

