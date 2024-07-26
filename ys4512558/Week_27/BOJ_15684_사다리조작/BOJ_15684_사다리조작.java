import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684 {

    static final int LEFT = -1;
    static final int RIGHT = 1;
    static int[][] map;
    static int N, M, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            map[a][b] = RIGHT;
            map[a][b + 1] = LEFT;
        }

        int result = -1;
        for (int i = 0; i <= 3; i++) {
            if (select(i, 0, 0, 0, false)) {
                result = i;
                break;
            }
        }
        System.out.println(result);

    }

    public static boolean simulation(int num) {
        int col = num;
        for (int i = 0; i < H; i++) {
            col += map[i][col];
        }
        return col == num;
    }

    //추가할 사다리 뽑기
    public static boolean select(int n, int startX, int startY, int depth, boolean flag) {
        if (flag) {
            return true;
        }
        if (n == depth) {
            for (int i = 0; i < N; i++) {
                if (!simulation(i)) {
                    return false;
                }
            }
            return true;
        }

        for (int i = startX; i < H; i++) {
            for (int j = startY; j < N - 1; j++) {
                if (map[i][j] != 0 || map[i][j + 1] != 0 || isNext(i, j)) {
                    continue;
                }
                map[i][j] = RIGHT;
                map[i][j + 1] = LEFT;
                flag = select(n, i, j + 1, depth + 1, flag);
                map[i][j] = 0;
                map[i][j + 1] = 0;
            }
            //j가 startY부터 시작하므로 startY = 6, N = 8이면
            //다음 줄도 j = startY = 6에서 시작되어 j = 0 ~ 5를 확인하지 않는다.
            //따라서 줄이 바뀌면 0 ~ N-1까지 확인하도록 초기화해줘야함.
            startY = 0;
        }
        return flag;
    }

    private static boolean isNext(int i, int j) {
        if (map[i][j] != 0 || map[i][j + 1] != 0) {
            return true;
        }
        return false;
    }

}