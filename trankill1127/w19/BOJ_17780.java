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
        ArrayList<Piece> pieces;

        public Cell(int x, int y, int id, int  dir) {
            this.x = x;
            this.y = y;
            this.pieces=new ArrayList<>();
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

        int n = Integer.parseInt(st.nextToken()); //보드 한 변의 길이
        int k = Integer.parseInt(st.nextToken()); //기물의 개수

        //보드를 입력받는다.
        //0: 흰      1: 빨      2: 파
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //기물의 위치와 방향을 입력받는다.
        LinkedList<Cell> cells = new LinkedList<>();
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine().trim());
            cells.add(
                    new Cell(Integer.parseInt(st.nextToken()) - 1,
                            Integer.parseInt(st.nextToken()) -1,
                            i,
                            Integer.parseInt(st.nextToken()) - 1));
        }

        int turnCnt = 0; //몇번째 턴인지
        Cell curCell = null; //현재 검사할 그룹
        int[] nextPos = new int[2]; //이동할 위치

        outer:
        while (turnCnt<=1000) {
            turnCnt++;

            //모든 그룹을 검사한다.
            for (int i = 0; i < cells.size(); i++) {
                curCell = cells.get(i);
                nextPos[0]=curCell.x + dir[curCell.pieces.get(0).direction][0];
                nextPos[1]=curCell.y + dir[curCell.pieces.get(0).direction][1];

                //다음 위치가 파란색 타일인 경우
                if (nextPos[0] < 0 || nextPos[0] >= n || nextPos[1] < 0 || nextPos[1] >= n || board[nextPos[0]][nextPos[1]] == 2) {

                    //이동 방향을 바꿔준다.
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
                    //방향을 바꾼 후의 다음 위치가 파란색이 아니라면 이동을 해야 하니 다시 한번 그룹을 검사할 수 있게 i를 줄여준다.
                    if ( !(nextPos[0] < 0 || nextPos[0] >= n || nextPos[1] < 0 || nextPos[1] >= n || board[nextPos[0]][nextPos[1]] == 2) )  {
                        i--;
                    }
                }
                else if (board[nextPos[0]][nextPos[1]] == 0) { //다음 위치가 흰색 타일인 경우

                    boolean isThere = false;
                    for (int j = 0; j < cells.size(); j++) {

                        //다른 그룹이 이동할 위치에 존재하는지 확인한다.
                        if (cells.get(j).x == nextPos[0] && cells.get(j).y == nextPos[1]) {

                            //만약 그룹이 합쳐졌을 기물의 개수가 4개 이상이라면 게임을 종료한다.
                            if ((curCell.pieces.size() + cells.get(j).pieces.size()) >= 4) {
                                break outer;
                            }

                            isThere = true;
                            cells.get(j).pieces.addAll(curCell.pieces); //그룹을 합친다.
                            cells.remove(i); //현재 검사 중이던 그룹은 다른 그룹에 흡수되었으니 삭제한다.
                            i--;
                            break;
                        }
                    }
                    if (!isThere) { //이동할 위치에 기물 그룹이 없는 경우
                        cells.get(i).x = nextPos[0];
                        cells.get(i).y = nextPos[1];
                    }

                } else if (board[nextPos[0]][nextPos[1]] == 1) { //빨
                    boolean isThere = false;
                    for (int j = 0; j < cells.size(); j++) {
                        if (cells.get(j).x == nextPos[0] && cells.get(j).y == nextPos[1]) {
                            if ((curCell.pieces.size() + cells.get(j).pieces.size()) >= 4) {
                                break outer;
                            }

                            isThere = true;
                            Collections.reverse(curCell.pieces); //검사 중이던 그룹의 기물 순서를 뒤집어준다.
                            cells.get(j).pieces.addAll(curCell.pieces); //그룹을 합친다.
                            cells.remove(i);
                            i--;
                            break;
                        }
                    }
                    if (!isThere) {
                        int prev = curCell.pieces.get(0).id;

                        cells.get(i).x=nextPos[0];
                        cells.get(i).y = nextPos[1];
                        Collections.reverse(curCell.pieces);
                        cells.get(i).pieces = curCell.pieces;

                        //기물들의 순서를 뒤집으면 가장 아래에 위치한 기물이 바뀌므로 정렬을 수행한다.
                        Collections.sort(cells);

                        //정렬을 다시 했으니 검사하던 그룹의 맨 아래 기물보다 크지만 차이가 작은 애부터 검사를 이어갈 수 있게 한다.
                        int j=0;
                        while (j<cells.size() && cells.get(j).pieces.get(0).id<=prev) {
                            j++;
                        }
                        i=j-1;
                    }
                }
            }
        }

        if (turnCnt>1000) System.out.println(-1);
        else System.out.println(turnCnt);
    }
}