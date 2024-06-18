import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_1767 {

    public static int n;
    public static int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return x + " " + y;
        }
    }

    public static class Set implements Comparable<Set> {
        int len;
        int cell;

        Set(int l, int c) {
            len = l;
            cell = c;
        }

        @Override
        public int compareTo(Set o) {
            if (this.cell == o.cell) {
                return this.len - o.len;
            }
            return o.cell - this.cell;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            l = new ArrayList<>();
            area = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    area[i][j] = Integer.parseInt(st.nextToken());
                    if (area[i][j] == 1 && i != 0 && i != n - 1 && j != 0 && j != n - 1) l.add(new Point(i, j));
                }
            }

//            for (int i = 0; i < l.size(); i++) {
//                System.out.println(l.get(i).toString());
//            }

            len = 0;
            result = new ArrayList<>();
            selectCnt = 0;
            minLen = Integer.MAX_VALUE;
            recur(0);

            Collections.sort(result);
            sb.append("#" + tc + " ").append(result.get(0).len).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static List<Point> l;
    public static int[][] area;
    public static int len;
    public static int selectCnt;
    public static int minLen;
    public static ArrayList<Set> result;

    public static void recur(int idx) { //이 위치에 있는 애를 결정해볼게

        if ((idx == l.size() || selectCnt == 12)) {

//			for (int i=0; i<n; i++) {
//				for (int j=0; j<n; j++) {
//					System.out.print(area[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
            //System.out.println(selectCnt + " " + len);

            result.add(new Set(len, selectCnt));
            return;
        }

        for (int i = 0; i < 4; i++) {

            if (!isEmpty(l.get(idx).x, l.get(idx).y, i)) continue;
            paint(l.get(idx).x, l.get(idx).y, i);
            selectCnt++;
            recur(idx + 1);
            selectCnt--;
            erase(l.get(idx).x, l.get(idx).y, i);

        }

        recur(idx + 1);

        return;
    }

    public static void paint(int x, int y, int dIdx) {
        while (x + direction[dIdx][0] < n && x + direction[dIdx][0] >= 0 && y + direction[dIdx][1] < n && y + direction[dIdx][1] >= 0) {
            x += direction[dIdx][0];
            y += direction[dIdx][1];
            area[x][y] += 2;
            len++;
        }
    }

    public static void erase(int x, int y, int dIdx) {
        while (x + direction[dIdx][0] < n && x + direction[dIdx][0] >= 0 && y + direction[dIdx][1] < n && y + direction[dIdx][1] >= 0) {
            x += direction[dIdx][0];
            y += direction[dIdx][1];
            area[x][y] -= 2;
            len--;
        }
    }

    public static boolean isEmpty(int x, int y, int dIdx) {
        while (x + direction[dIdx][0] < n && x + direction[dIdx][0] >= 0 && y + direction[dIdx][1] < n && y + direction[dIdx][1] >= 0) {

            x += direction[dIdx][0];
            y += direction[dIdx][1];
            if (area[x][y] != 0) {
                //System.out.println(dIdx + " 불가능");
                return false;
            }
        }

        return true;
    }

}