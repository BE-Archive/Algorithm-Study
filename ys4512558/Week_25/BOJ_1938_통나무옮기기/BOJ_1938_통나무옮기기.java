import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ1938 {
    static boolean[][][] isv;
    static char[][] map;
    static Tree start;
    static Tree dest;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        //N, N위치의 통나무 중앙이 위치할때, 가로, 세로
        isv = new boolean[N][N][2];
        map = new char[N][N];
        //통나무의 start, middle, end
        int idx1 = 0;
        //목적지의 start, middle, end
        int idx2 = 0;
        start = new Tree();
        dest = new Tree();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'B') {
                    //왼쪽 위부터 입력이 들어오기 때문에
                    //가장 먼저 만나는 통나무가 start
                    //다음이 middle, 마지막이 end (목적지 E도 마찬가지)
                    if (idx1 == 0) {
                        start.start = new int[]{i, j};
                    } else if (idx1 == 1) {
                        start.middle = new int[]{i, j};
                    } else {
                        start.end = new int[]{i, j};
                    }
                    idx1++;
                } else if (map[i][j] == 'E') {
                    if (idx2 == 0) {
                        dest.start = new int[]{i, j};
                    } else if (idx2 == 1) {
                        dest.middle = new int[]{i, j};
                    } else {
                        dest.end = new int[]{i, j};
                    }
                    idx2++;
                }
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        //시작과 중간의 행이 같으면 가로, 다르면 세로로 배치된 것
        int dir = start.start[0] == start.middle[0] ? 0 : 1;
        Queue<Tree> queue = new ArrayDeque<>();
        queue.offer(new Tree(start.start, start.middle, start.end, dir));
        isv[start.middle[0]][start.middle[1]][dir] = true;
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Tree tree = queue.poll();

                if(isDestination(tree, dest)) return result;

                //4방향 이동
                for (int i = 0; i < 4; i++) {
                    int nxS = tree.start[0] + dx[i];
                    int nyS = tree.start[1] + dy[i];
                    int nxM = tree.middle[0] + dx[i];
                    int nyM = tree.middle[1] + dy[i];
                    int nxE = tree.end[0] + dx[i];
                    int nyE = tree.end[1] + dy[i];

                    if (notValid(nxS, nyS) || notValid(nxM, nyM) || notValid(nxE, nyE)) continue;
                    if (isv[nxM][nyM][tree.dir]) continue;
                    int[] nStart = new int[]{nxS, nyS};
                    int[] nMiddle = new int[]{nxM, nyM};
                    int[] nEnd = new int[]{nxE, nyE};
                    isv[nxM][nyM][tree.dir] = true;
                    queue.offer(new Tree(nStart, nMiddle, nEnd, tree.dir));
                }
                int middleX = tree.middle[0];
                int middleY = tree.middle[1];

                //회전이 가능한지 판단
                if (notValid(middleX - 1, middleY) || notValid(middleX + 1, middleY)
                        || notValid(middleX, middleY - 1) || notValid(middleX, middleY + 1)
                        || notValid(middleX - 1, middleY - 1) || notValid(middleX - 1, middleY + 1)
                        || notValid(middleX + 1, middleY - 1) || notValid(middleX + 1, middleY + 1)
                ) {
                    continue;
                }

                //회전
                //가로일 때
                if (tree.dir == 0) {
                    //회전 시 start의 x, y
                    int nStartX = tree.middle[0] - 1; //행은 하나 위
                    int nStartY = tree.middle[1]; //열은 같음
                    if(notValid(nStartX, nStartY)) continue;

                    int nEndX = tree.middle[0] + 1; //행은 하나 아래
                    int nEndY = tree.middle[1]; //열은 같음
                    if(notValid(nEndX, nEndY)) continue;

                    if(isv[tree.middle[0]][tree.middle[1]][1]) continue;
                    //가능하면
                    int[] nStart = new int[]{nStartX, nStartY};
                    int[] nEnd = new int[]{nEndX, nEndY};
                    queue.offer(new Tree(nStart, tree.middle, nEnd, 1));
                    isv[tree.middle[0]][tree.middle[1]][1] = true;
                } else { //세로일 때
                    //회전 시 start의 x, y
                    int nStartX = tree.middle[0]; //행은 같음
                    int nStartY = tree.middle[1] - 1; //열은 하나 왼쪽
                    if(notValid(nStartX, nStartY)) continue;

                    int nEndX = tree.middle[0]; //행은 같음
                    int nEndY = tree.middle[1] + 1; //열은 하나 오른쪽
                    if(notValid(nEndX, nEndY)) continue;

                    if(isv[tree.middle[0]][tree.middle[1]][0]) continue;
                    //가능하면
                    int[] nStart = new int[]{nStartX, nStartY};
                    int[] nEnd = new int[]{nEndX, nEndY};
                    queue.offer(new Tree(nStart, tree.middle, nEnd, 0));
                    isv[tree.middle[0]][tree.middle[1]][0] = true;
                }
            }
            result++;
        }
        return 0;
    }

    private static boolean notValid(int x, int y) {
        if(x < 0 || y < 0 || x >= N || y >= N || map[x][y] == '1') return true;
        return false;
    }
    public static boolean isDestination(Tree current, Tree dest) {
        if (current.start[0] == dest.start[0] && current.start[1] == dest.start[1] &&
                current.middle[0] == dest.middle[0] && current.middle[1] == dest.middle[1] &&
                current.end[0] == dest.end[0] && current.end[1] == dest.end[1]) {
            return true;
            //시작과 끝이 뒤바뀐 경우가 있을 수 있음
        } else if (current.start[0] == dest.end[0] && current.start[1] == dest.end[1] &&
                current.middle[0] == dest.middle[0] && current.middle[1] == dest.middle[1] &&
                current.end[0] == dest.start[0] && current.end[1] == dest.start[1]) {
            return true;
        }
        return false;
    }
}

class Tree {
    int[] start;
    int[] middle;
    int[] end;
    int dir;

    public Tree() {
    }

    public Tree(int[] start, int[] middle, int[] end, int dir) {
        this.start = start;
        this.middle = middle;
        this.end = end;
        this.dir = dir;
    }
}