import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int N, L, R;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int day = 0;
        while (true){
            if(checkMove()) break;
            day++;
        }
        bw.write(String.valueOf(day));
        bw.flush();
        bw.close();
    }
    //4-connectivity
    private static boolean checkMove() {
        int idx = 1;
        int[][] mark = new int[N][N];
        Map<Integer, ArrayList<int[]>> markMap = new TreeMap<>();
        for (int i = 0, top = -1; i < N; i++, top++) {
            for (int j = 0, left = -1; j < N; j++, left++) {
                //현재 위치 기준 top의 mark를 확인
                int topMark = -1;
                if (top >= 0) {
                    int diff = Math.abs(map[top][j] - map[i][j]);
                    if(diff >= L && diff <= R) {
                        topMark = mark[top][j];
                    }
                }
                int leftMark = -1;
                if (left >= 0) {
                    int diff = Math.abs(map[i][left] - map[i][j]);
                    if(diff >= L && diff <= R) {
                        leftMark = mark[i][left];
                    }
                }
                //위 왼쪽 모두 없거나 둘 다 조건 만족 X
                if (leftMark == -1 && topMark == -1) {
                    //새롭게 인덱싱
                    mark[i][j] = idx++;
                    markMap.put(mark[i][j], new ArrayList<>());
                    markMap.get(mark[i][j]).add(new int[]{i, j});
                } else if (leftMark != -1 && topMark != -1) {
                    //둘다 있을 때 (둘다 가능 -> 하나의 지역이 된다.)
                    //왼쪽 인덱싱으로 추가하고
                    //위쪽 인덱싱의 List를 추가하고
                    //맵에서 키 삭제
                    mark[i][j] = leftMark;
                    if(leftMark == topMark){
                        markMap.get(mark[i][j]).add(new int[]{i, j});
                        continue;
                    }
                    for (int k = 0; k < markMap.get(topMark).size(); k++) {
                        int[] point = markMap.get(topMark).get(k);
                        markMap.get(mark[i][j]).add(point);
                        mark[point[0]][point[1]] = mark[i][j];
                    }
                    markMap.get(mark[i][j]).add(new int[]{i, j});
                    markMap.remove(topMark);
                } else {
                    //둘 중 하나만 연결되는 경우
                    mark[i][j] = leftMark != -1 ? leftMark : topMark;
                    markMap.get(mark[i][j]).add(new int[]{i, j});
                }
            }
        }
        //각각 원소가 하나의 집합을 이루면 더 이상 합쳐지지 않는 것을 의미함.
        if (mark[N-1][N-1] == N * N) return true;
        for (Integer key : markMap.keySet()) {
            List<int[]> list = markMap.get(key);
            int sum = 0;
            for (int i = 0; i < list.size(); i++) {
                int[] point = list.get(i);
                sum += map[point[0]][point[1]];
            }
            int res = sum / list.size();
            for (int i = 0; i < list.size(); i++) {
                int[] point = list.get(i);
                map[point[0]][point[1]] = res;
            }
        }
        return false;
    }
}