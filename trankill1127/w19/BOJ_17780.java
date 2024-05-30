import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_17780 {

    public static class Piece {
        int id;
        int direction;

        public Piece(int id, int direction) {
            this.id = id;
            this.direction = direction;
        }
    }

    public static class Cell implements Comparable<Cell>{
        int x;
        int y;
        ArrayList<Piece> pieces = new ArrayList<>();

        public Cell(int x, int y, int id, int dir) {
            this.x = x;
            this.y = y;
            this.pieces.add(new Piece(id, dir));
        }

        @Override
        public int compareTo(Cell o) {
            return this.pieces.get(0).id - o.pieces.get(0).id;
        }
    }

    public static int[][] dir = {
            {0, 1}, //오른쪽
            {0, -1}, //왼쪽
            {-1, 0}, //위쪽
            {1, 0} //아래쪽
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //0: 흰      1: 빨      2: 파

        LinkedList<Cell> cells = new LinkedList<>();
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine().trim());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            cells.add(new Cell(x, y, i, d));
        }

        int turnCnt = 0;

        outer:
        while (true) {

            turnCnt++;

            //모든 그룹을 가장 아래에 있는 기물의 id 기준 오름차순으로 정렬한다.
            Collections.sort(cells);

            //모든 그룹을 검사한다.
            for (int i = 0; i < cells.size(); i++) {

                Cell curCell = cells.get(i);

                int[] nextPos = new int[]{
                        curCell.x + dir[curCell.pieces.get(0).direction][0],
                        curCell.y + dir[curCell.pieces.get(0).direction][1]
                };

                if (nextPos[0] < 0 || nextPos[0] >= n || nextPos[1] < 0 || nextPos[1] >= n || board[nextPos[0]][nextPos[1]] == 2) { //파

                    if (curCell.pieces.get(0).direction == 0) {
                        curCell.pieces.get(0).direction = 1;
                    } else if (curCell.pieces.get(0).direction == 1) {
                        curCell.pieces.get(0).direction = 0;
                    } else if (curCell.pieces.get(0).direction == 2) {
                        curCell.pieces.get(0).direction = 3;
                    } else {
                        curCell.pieces.get(0).direction = 2;
                    }

                    nextPos[0] = curCell.x + dir[curCell.pieces.get(0).direction][0];
                    nextPos[1] = curCell.y + dir[curCell.pieces.get(0).direction][1];
                    if (nextPos[0] < 0 || nextPos[0] >= n || nextPos[1] < 0 || nextPos[1] >= n || board[nextPos[0]][nextPos[1]] == 2) {
                        continue;
                    } else {
                        i--;
                        continue;
                    }

                }

                //이동할 칸의 색상을 확인한다.
                else if (board[nextPos[0]][nextPos[1]] == 0) { //흰

                    boolean isThere = false;
                    for (int j = 0; j < cells.size(); j++) {
                        if (cells.get(j).x == nextPos[0] &&
                                cells.get(j).y == nextPos[1]) {
                            isThere = true;

                            if ((curCell.pieces.size() + cells.get(j).pieces.size()) >= 4) {
                                break outer;
                            }

                            cells.get(j).pieces.addAll(curCell.pieces);
                            curCell.pieces.clear();
                            break;
                        }
                    }
                    if (!isThere) {
                        curCell.x = nextPos[0];
                        curCell.y = nextPos[1];
                    }

                } else if (board[nextPos[0]][nextPos[1]] == 1) { //빨

                    boolean isThere = false;
                    for (int j = 0; j < cells.size(); j++) {
                        if (cells.get(j).x == nextPos[0] &&
                                cells.get(j).y == nextPos[1]) {
                            isThere = true;

                            if ((curCell.pieces.size() + cells.get(j).pieces.size()) >= 4) {
                                break outer;
                            }

                            for (int cIdx = curCell.pieces.size() - 1; cIdx >= 0; cIdx--) {
                                cells.get(j).pieces.add(curCell.pieces.get(cIdx));
                            }
                            curCell.pieces.clear();
                            break;
                        }
                    }
                    if (!isThere) {
                        curCell.x = nextPos[0];
                        curCell.y = nextPos[1];
                        ArrayList<Piece> reversedPieces = new ArrayList<>(curCell.pieces);
                        Collections.reverse(reversedPieces);
                        curCell.pieces = reversedPieces;
                    }

                }

            }

        }

        System.out.println(turnCnt);

    }
}