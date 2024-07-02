import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, max = 0;
    static char[][] board;
    static char[][] tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        tmp = new char[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                tmp[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                calcCnt(i, j, board[i][j]);
                if (i - 1 >= 0 && board[i - 1][j] != board[i][j]) {
                    swap(i, j, i - 1 , j);
                    calcCnt(i, j, board[i][j]);
                    swap(i, j, i - 1 , j);
                }
                if (i + 1 < N && board[i + 1][j] != board[i][j]) {
                    swap(i, j, i + 1 , j);
                    calcCnt(i, j, board[i][j]);
                    swap(i, j, i + 1 , j);
                }
                if (j - 1 >= 0 && board[i][j - 1] != board[i][j]) {
                    swap(i, j, i, j - 1);
                    calcCnt(i, j, board[i][j]);
                    swap(i, j, i, j - 1);
                }
                if (j + 1 < N && board[i][j + 1] != board[i][j]) {
                    swap(i, j, i, j + 1);
                    calcCnt(i, j, board[i][j]);
                    swap(i, j, i, j + 1);
                }
            }
        }
        sb.append(max + 1);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void calcCnt(int row, int col, char color) {
        int cnt = 0;
        //열 확인
        for (int i = col - 1; i >= 0; i--) {
            if (board[row][i] != color) break;
            cnt++;
        }
        for (int i = col + 1; i < N; i++) {
            if(board[row][i] != color) break;
            cnt++;
        }
        max = Math.max(max, cnt);

        cnt = 0;
        //행 확인
        for (int i = row - 1; i >= 0; i--) {
            if(board[i][col] != color) break;
            cnt++;
        }
        for (int i = row + 1; i < N; i++) {
            if(board[i][col] != color) break;
            cnt++;
        }
        max = Math.max(max, cnt);
    }

    private static void swap(int row1, int col1, int row2, int col2) {
        char temp = board[row1][col1];
        board[row1][col1] = board[row2][col2];
        board[row2][col2] = temp;
    }
}