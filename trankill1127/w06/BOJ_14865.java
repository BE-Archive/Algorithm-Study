import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14865 {

    public static class Square implements Comparable<Square> {
        int left;
        int right;
        Square(int v1, int v2) {
            left = Math.min(v1, v2);
            right = Math.max(v1, v2);
        }
        public String toString() {
            return "left= " + left + " right= " + right;
        }
        @Override
        public int compareTo(Square o) {
            return this.left - o.left;
        }
    }

    public static class Line {
        int val;
        int dir;
        Line(int v, int d) {
            val = v;
            dir = d;
        }
        public String toString() {
            return "val= " + val + " dir= " + dir;
        }
    }

    public static int isNotIncluding;
    public static int isNotIncluded;
    public static List<Square> sqList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //좌표 개수

        sqList = new ArrayList<>();
        Stack<Line> s = new Stack<>();

        int startX=0; int startY=0;
        int befX=0; int befY=0;
        for (int i = 0; i < n; i++) { //좌표
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int nowX = Integer.parseInt(st.nextToken());
            int nowY = Integer.parseInt(st.nextToken());

            if (i==0) {
                startX=nowX;
                startY=nowY;
            }
            else if (befX == nowX) {
                if (befY < 0 && 0 < nowY) { //올라가는 방향
                    s.add(new Line(nowX, 1));
                }
                if (nowY < 0 && 0 < befY) { //내려가는 방향
                    if (s.empty()) {
                        s.add(new Line(nowX, -1));
                    } else {
                        sqList.add(new Square(s.pop().val, nowX));
                    }
                }
            }

            befX = nowX;
            befY = nowY;
        }

        if (s.size() == 1) {
            sqList.add(new Square(s.pop().val, startX));
        }
        if (s.size() == 2) {
            sqList.add(new Square(s.pop().val, s.pop().val));
        }
        Collections.sort(sqList); //좌측 좌표 기준 오름차순 정렬

        isNotIncluded = sqList.size();
        isNotIncluding = 0;
        if (sqList.size() == 1) {
            isNotIncluded = 1;
            isNotIncluding = 1;
        } else {
            for (int i = 0; i < sqList.size(); ) {
                i = mountainCheck(i);
            }
        }

        System.out.println(isNotIncluded + " " + isNotIncluding);
    }

    public static int mountainCheck(int idx) { //idx번째 사각형을 검사합니다.

        int inside = 0; //idx의 내부에 있는 사각형의 개수

        int i;
        for (i = idx + 1; i < sqList.size(); ) { //정렬했으니까 idx+1부터 검사
            if (sqList.get(i).right < sqList.get(idx).right) { //포함된다면

                //System.out.println(sqList.get(idx).toString()+"안에 "+sqList.get(i).toString());

                isNotIncluded--; //i번째 사각형은 다른 사각형에 의해 포함되는 것이니까 isNotIncluded--
                inside++;
                i = mountainCheck(i);
            } else break;
        }
        if (inside == 0) { //이 사각형에 포함되는 사각형이 없다면
            isNotIncluding++;
        }

        return i;
    }
}