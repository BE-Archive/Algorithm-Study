import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[][] map = new int[H][W];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            int height = Integer.parseInt(st.nextToken());
            for (int j = 0; j < height; j++) {
                map[j][i] = 1;
            }
        }
        sb.append(rain(H, W, map));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int rain(int h, int w, int[][] map) {
        int sum = 0;
        for (int i = 0; i < h; i++) {
            int left = -1;
            int right = -1;
            for (int j = 0; j < w; j++) {
                boolean check = map[i][j] == 1;
                if (check) {
                    right = j;
                }
                if (check && left == -1) {
                    left = j;
                }
            }

            for (int j = left; j < right; j++) {
                if (map[i][j] == 0) {
                    sum++;
                }
            }
        }
        return sum;
    }